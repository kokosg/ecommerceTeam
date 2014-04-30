import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ConnectionManager;
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
		try {
			ConnectionManager conn = new ConnectionManager();			
			//query Author and AuthorReviewer tables to retrieve data regarding the current user (given email and password)
			String selectQuery = "SELECT Author.authorID, Author.name, Author.surname, Author.affiliations, Author.email, AuthorReviewer.password, AuthorReviewer.isEditor"
					+ " from Author INNER JOIN AuthorReviewer ON Author.authorID = AuthorReviewer.authorID"
					+ " where Author.email ='" + eml + "'and AuthorReviewer.password ='" + pwd + " ' ";
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(selectQuery);

			if (rs.next()) {
				//get all forms' attribute values
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
	        	   //create a user object
	        	   User user = new User(userID, userName, userSurname, userAffiliations, request.getParameter("email"), userPassword, isEditor); //see email again
	        	   //add user object to session
	        	   session.setAttribute("user", user);
	        	   //add userRole to session
	        	   if (isEditor == false) {
	        		   session.setAttribute("userRole", "AuthorReviewer");    		
	        	   } else {
	        		   session.setAttribute("userRole", "Editor"); //think again - change that??		
	        	   }
	           } 
	           if (userEmail != null) {
	        	   // This user already has a session; show the name and show the list of
	        	   // items entered.
	        	   System.out.println("already has a session" + userName + " " + userSurname + "!");
	           }
	           //context.put("session", (User)session.getAttribute("user"));
			} else {
				//msg = "<font size='8'color = red>Your login Failed</font>";
				//request.setAttribute("message123", msg);
				//response.getWriter().println("<font size='8'color = red>1" + msg);
				//request.getRequestDispatcher("index.jsp").forward(request,response);
				//System.out.println("error");
			}
			template = getTemplate("/forms/home.vm"); 
			//release resources
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
		return template;
	}
}
