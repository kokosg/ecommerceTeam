import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ConnectionManager;
import objects.User;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

public class Subscribe extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
		
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		
		String flag = request.getParameter("flag");
		System.out.println(flag);
		context.put("apptitle", "E-com Journal - Subscribe");
		Template template=null;
		response.setContentType("text/html");
		
		if (flag == null) {
			System.out.println("if");
			String eml = request.getParameter("email");
			String pwd = request.getParameter("password");
			
			try {
				ConnectionManager conn = new ConnectionManager();			
				
				String selectQuery = "SELECT Author.authorID, Author.name, Author.surname, Author.affiliations, Author.email, AuthorReviewer.password, AuthorReviewer.isEditor"
						+ " from Author INNER JOIN AuthorReviewer ON Author.authorID = AuthorReviewer.authorID"
						+ " where Author.email ='" + eml + "'and AuthorReviewer.password ='" + pwd + " ' ";
				Statement st = conn.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(selectQuery);
	
				if (rs.next()) {
					int userID = rs.getInt("Author.authorID");;
					String userName = (String)rs.getObject("Author.name");
			        String userSurname = (String)rs.getObject("Author.surname");
			        String userAffiliations = (String)rs.getObject("Author.affiliations");
			        String userEmail = (String)rs.getObject("Author.email");
			        String userPassword = (String)rs.getObject("AuthorReviewer.password");
			        Boolean isEditor = (Boolean)rs.getObject("AuthorReviewer.isEditor");
			        
			        HttpSession session = request.getSession();
		            userEmail = (String)session.getAttribute("email");
		            
		           if (request.getParameter("email") != null) {
		        	   User user = new User(userID, userName, userSurname, userAffiliations, request.getParameter("email"), userPassword, isEditor); //see email again
		        	   
		        	   session.setAttribute("user", user);
		        	   
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
					//System.out.println("error");
				}
				rs.close();
				st.close();
				conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				template = getTemplate("/forms/subscribe.vm"); 
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		} else {
			System.out.println("else");
			return template;
		}
	}
}

