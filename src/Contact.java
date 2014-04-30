

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.EmailMessage;
import models.ContactModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

public class Contact extends VelocityViewServlet {
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
	      /* get the template */
		
		String flag = request.getParameter("flag"); // get the value of a hidden field in a form to determine if we have posted data
		ContactModel contactModel = new ContactModel();
		
	      Template template = null;
	      context.put("apptitle", "Ecom Journal - Contact");
		  response.setContentType("text/html");
	      boolean emailStatus = false;
		  
	      if (flag == null) {
		  
	    	  template = getTemplate("/forms/contact.vm");
		      return template;

	      } else {

	    	  try {
	    	  String name = request.getParameter("name");
			  String title = request.getParameter("title");
			  String email = request.getParameter("email");
			  String messageText = request.getParameter("messageText");

	    	  EmailMessage emailMessage = new EmailMessage(name, title, email, messageText); 
			  
			  emailStatus = contactModel.sendEmail(emailMessage.getName(), emailMessage.getTitle(), emailMessage.getEmail(), emailMessage.getMessage());
	    	  
	    	  if (emailStatus) {
	  			context.put("status", "message was send");
	  			contactModel.insertEmail(emailMessage.getName(), emailMessage.getTitle(), emailMessage.getEmail(), emailMessage.getMessage());
	    	  } else {
	  			context.put("status", "message was not send");
	    	  }
			  
	         template = getTemplate("/forms/contact.vm");
	      
	      } catch(Exception e ) {
	         System.out.println("Error " + e);
	      }
	      return template;
	      }
	   }
	
	
	
	}
