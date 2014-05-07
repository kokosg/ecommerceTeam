import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ConnectionManager;
import models.LoginModel;
import objects.Choices;
import objects.User;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/** Velocity View Servlet implementation class Login 
 * @author Team:Master10
 */
public class Login extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		String eml = request.getParameter("email");
		String pwd = request.getParameter("password");
		context.put("apptitle", "E-com Journal");
		Template template=null;
		response.setContentType("text/html");
		LoginModel model = new LoginModel();
		Boolean haveReviews = false;
		// get the value of a hidden field in a form to determine if we have posted data
		String flag = request.getParameter("flag"); 
		
		//if we haven't post any data then we have just to load the template
		if (flag == null) {
			template = getTemplate("/forms/profile.vm"); 
		} else {
			try { 
				ConnectionManager conn = new ConnectionManager();	
				
				//md5 operations - change password to equivalent md5 format
		    	MessageDigest digest;
				digest = MessageDigest.getInstance("MD5");
		        digest.update(pwd.getBytes());
		        byte byteData[] = digest.digest();
		        //convert the byte to hex format
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < byteData.length; i++) {
		        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		        }				
				
				//query Author and AuthorReviewer tables to retrieve data regarding the current user (given email and password)
				String selectQuery = "SELECT Author.authorID, Author.name, Author.surname, Author.affiliations, Author.email, AuthorReviewer.password, AuthorReviewer.isEditor"
						+ " from Author INNER JOIN AuthorReviewer ON Author.authorID = AuthorReviewer.authorID"
						+ " where Author.email ='" + eml + "'and AuthorReviewer.password ='" + sb.toString() + " ' ";
				Statement st = conn.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(selectQuery);
	
				if (rs.next()) {
					//get all attribute values
					int userID = rs.getInt("Author.authorID");
					String userName = (String)rs.getObject("Author.name");
			        String userSurname = (String)rs.getObject("Author.surname");
			        String userAffiliations = (String)rs.getObject("Author.affiliations");
			        String userEmail = (String)rs.getObject("Author.email");
			        String userPassword = (String)rs.getObject("AuthorReviewer.password");
			        Boolean isEditor = (Boolean)rs.getObject("AuthorReviewer.isEditor");
			        //get session object
			        HttpSession session = request.getSession();
		            userEmail = (String)session.getAttribute("email");
		            
		           if (request.getParameter("email") != null) {
		        	   	//alert message
						context.put("successfully", "Successfully Login!");
		        	   //create a user object
		        	   User user = new User(userID, userName, userSurname, userAffiliations, request.getParameter("email"), userPassword, isEditor); //see email again
		        	   //add user object to session
		        	   session.setAttribute("user", user);
		        	   session.setAttribute("userID", userID);
		        	   //add userRole to session
		        	   if (isEditor == false) {
		        		   session.setAttribute("userRole", "AuthorReviewer");
		        	   } else {
		        		   session.setAttribute("userRole", "Editor");		
		        	   } 
			           //check if there are any reviews for my articles 
			           haveReviews = model.haveReviews(user.getAuthorID());
			           if (haveReviews) {
			        	   context.put("haveReviews", "You have pending criticism on at least one of your articles, go to Actions - My Articles to access them!");
			           }
		           } 
		           if (userEmail != null) {
		        	   // This user already has a session; show the name and show the list of
		        	   // items entered.
		        	   System.out.println("already has a session" + userName + " " + userSurname + "!");
		           }
		           System.out.println(haveReviews);
				} else {
					context.put("error", "Problem occurred on Login check your password or username/email!");
				}
				template = getTemplate("/forms/profile.vm"); 
				//release resources
				rs.close();
				st.close();
				conn.close();
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
		}
		return template;
	}
}
