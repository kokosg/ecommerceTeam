import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SystemManagementmModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class Reviews
 */
public class Reviews extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - Reviews");
		Template template = null;
		response.setContentType("text/html");

		//create JournalModel object
		SystemManagementmModel model = new SystemManagementmModel();

		String article_ID = request.getParameter("id");
		
		try {
			
			if (article_ID != null){

				//return getUsers from UserManagementModel and put them in contexts
				context.put("Reviews", model.getReviews(article_ID));
				context.put("Criticisms", model.getCriticisms());
				context.put("Responses", model.getResponses());

			}
			
				template = getTemplate("/pages/reviews.vm");
				
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
