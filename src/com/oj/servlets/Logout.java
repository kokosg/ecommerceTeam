package com.oj.servlets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/** Velocity View Servlet implementation class Logout
 * @author Team:Master10
 */
public class Logout extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
       
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
	      /* get the template */
	      Template template = null;
	      context.put("apptitle", "Ecom Journal - Home - Logout");

	      try {
	    	  //get the session object and invalidate() it
	  		HttpSession session = request.getSession();
			session.invalidate();
			
				context.put("successfully", "Your have been logout successfully");
			
	         template = getTemplate("/forms/home.vm"); 
	      } catch(Exception e ) {
	         System.out.println("Error " + e);
				context.put("error", "Problem occurred during logout process");
	      }
	      return template;
	   }

}
