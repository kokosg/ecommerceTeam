import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.UserManagementModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class abstructPage
 */
public class UserManagement extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - UserManagement");
		Template template = null;
		response.setContentType("text/html");

		//create UserManagementModel object
		UserManagementModel model = new UserManagementModel();

		//get the parameters from the form
		String userID = request.getParameter("authorReviewerID");
		
		boolean status = false;
		
		try {

			if (userID != null) {
				int isEditor = Integer.parseInt(request.getParameter("isEditor"));
				status = model.setUserRole(isEditor, userID);

				if (status) {
					context.put("successfully", "User role has been updated");
				} else {
					context.put("error", "Problem occurred on User role");
				}
				
			}
				//return getUsers from UserManagementModel and put them in contexts
				context.put("searchResults", model.getUsers());
				
				template = getTemplate("/pages/userManagement.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
