

import java.sql.SQLException;

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
		
		// get the value of a hidden field in a form to determine if we have posted data
		String flag = request.getParameter("flag"); 
		
		//create ContactModel object
		ContactModel contactModel = new ContactModel();
		
	      Template template = null;
	      context.put("apptitle", "Ecom Journal - Contact");
		  response.setContentType("text/html");
		  
		  //initialised value
	      boolean emailStatus = false;
		  
		  //if we haven't post any data then we have just to load the template
	      if (flag == null) {
		  
	    	  template = getTemplate("/forms/contact.vm");
		      return template;

	      } else {

	    	  try {
	    	  // get the value of a hidden field in a form to determine if we have posted data
	    	  String name = request.getParameter("name");
			  String title = request.getParameter("title");
			  String email = request.getParameter("email");
			  String messageText = request.getParameter("messageText");

			  //create EmailMessage object by passing values
	    	  EmailMessage emailMessage = new EmailMessage(name, title, email, messageText);
	    	  
	    	  String subject = "Subscribe email";
	    	  
	    	  //call the method sendEmail from contactModel object and passing values in order to trigger the email function
			  emailStatus = contactModel.sendEmail(emailMessage.getName(), emailMessage.getEmail(), subject, emailMessage.getMessage());
	    	  
			  //check if the emailStatus status is true to display the message and insert into the database the details of the email
			  //or if is false to display an error message
	    	  if (emailStatus) {
	  			context.put("status", "message has been send");
	  			contactModel.insertEmail(emailMessage.getName(), emailMessage.getTitle(), emailMessage.getEmail(), emailMessage.getMessage());
	    	  } else {
	  			context.put("status", "message has not been send");
	    	  }
			  
	         template = getTemplate("/forms/contact.vm");
	      
	      } catch(Exception e ) {
	         System.out.println("Error " + e);
	      }
	      return template;
	      }
	   }
	
	
	
	}
