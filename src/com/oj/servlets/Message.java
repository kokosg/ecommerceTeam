package com.oj.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.ContactModel;

public class Message extends VelocityViewServlet {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
     
	/* get the template */
      Template template = null;
      context.put("apptitle", "Ecom Journal - Browse");
	  response.setContentType("text/html");
	  
	  //create ContactModel object
      ContactModel contactModel = new ContactModel();

      try {
		
  		String action = request.getParameter("action");
  		String messageID = request.getParameter("messageID");
  		String answer = request.getParameter("answer");


		if (messageID != null) {
			
			String alerMessage = null;
	        boolean status = false;
			
	    	if (action.contains("reply")) {
	    		status = contactModel.setAnswer(messageID, answer);
	    		alerMessage = "Answer has been updated";

	    	} else if (action.contains("remove")) { 
	    		status = contactModel.deleteMessage(messageID);
	    		alerMessage = "Letter has been deleted";
	    		
	    	} else if (action.contains("accept")) {
	    		status = contactModel.updateMessage(messageID);
				alerMessage = "Letter has been published";
	    	}
	    	
	    	if (status) {
	    		context.put("successfully", alerMessage);
	    	} else {
				context.put("error", "Something wrong with your request");
	    	}
	    	
		}
		
		context.put("Messages", contactModel.getUnpublishMessages());
		
         
         template = getTemplate("/pages/message.vm"); 
         
      } catch(Exception e ) {
         System.out.println("Error " + e);
      }
      return template;
   }
}
