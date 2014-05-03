import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ReviewerManagementModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class ReviewerManagement
 */
public class ReviewerManagement extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - ReviewerManagement");
		Template template = null;
		response.setContentType("text/html");

		//create JournalModel object
		ReviewerManagementModel model = new ReviewerManagementModel();

		boolean status = false;
		
		try {

			String assignID = request.getParameter("choiceID");

			if (assignID != null){
				status = model.deleteChoice(assignID);

				if (status) {
					context.put("successfully", "has been created successfully Rejected");
				} else {
					context.put("error", "Problem occurred");
				}
				
			}
			
				//return getUsers from UserManagementModel and put them in contexts
				context.put("Choices", model.getChoices());
				
				template = getTemplate("/pages/reviewerManagement.vm");
				
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
