import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ContactModel;
import models.SystemManagementmModel;
import objects.EmailMessage;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class RecentReviews
 */
public class RecentReviews extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - RecentReviews");
		Template template = null;
		response.setContentType("text/html");

		//create JournalModel object
		//ReviewerManagementModel model = new ReviewerManagementModel();

		
		//create JournalModel object
		SystemManagementmModel model = new SystemManagementmModel();

		
		try {

			String reviewID = request.getParameter("reviewID");
			String authorReviewerID = request.getParameter("authorReviewerID");

			
			if ((reviewID != null) && (authorReviewerID != null)) {
				boolean deleteStatus = model.deleteReview(reviewID, authorReviewerID);
				
				if (deleteStatus == true) {
					System.out.print("Delete done2");

				// get the value of a hidden field in a form to determine if we have posted data
		    	  String name = request.getParameter("name");
				  String title = request.getParameter("title");
				  String email = request.getParameter("email");
				  String messageText = "Your review has been rejected";

				  //create EmailMessage object by passing values
		    	  EmailMessage emailMessage = new EmailMessage(name, title, email, messageText);
		    	  
			  	  //create ContactModel object
			  	  ContactModel contactModel = new ContactModel();
		    	  
			  	  boolean emailStatus = false;
			  	  
		    	  //call the method sendEmail from contactModel object and passing values in order to trigger the email function
				  emailStatus = contactModel.sendEmail(emailMessage.getName(), emailMessage.getTitle(), emailMessage.getEmail(), emailMessage.getMessage());
		    	  
				  //check if the emailStatus status is true to display the message and insert into the database the details of the email
				  //or if is false to display an error message
		    	  if (emailStatus) {
		  			context.put("successfully", "message was send");
		  			contactModel.insertEmail(emailMessage.getName(), emailMessage.getTitle(), emailMessage.getEmail(), emailMessage.getMessage());
		    	  } else {
		  			context.put("error", "message was not send");
		    	  }
			} else {
	  			context.put("error", "error on the request");
			}

			}

				//return getUsers from UserManagementModel and put them in contexts
				context.put("Articles", model.getArticle());
				context.put("Reviews", model.getAllReviews());
				context.put("AuthorReviewers", model.getAuthorReviewer());
				
				template = getTemplate("/pages/recentReviews.vm");
				
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
