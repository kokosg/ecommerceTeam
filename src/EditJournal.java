import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.JournalModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class EditJournal
 */
public class EditJournal extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - EditJournal");
		Template template = null;
		response.setContentType("text/html");

		//create JournalModel object
		JournalModel model = new JournalModel();

		//get the parameters from the form
		String aimsGoals = request.getParameter("aimsGoals");
		
		boolean status = false;
		
		try {

			if (aimsGoals != null) {
				int journalID = 1;
				String title = request.getParameter("title");
				
				status = model.setJournal(journalID, title, aimsGoals);
				
				if (status) {
					context.put("successfully", "Journal has been updated successfully");
				} else {
					context.put("error", "Problem occurred on Journal");
				}
				
			}
				//return getUsers from UserManagementModel and put them in contexts
				context.put("searchResults", model.getJournal());
				
				template = getTemplate("/pages/editJournal.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
