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

		UserManagementModel model = new UserManagementModel();
		
		try {

  			context.put("searchResults", model.getUsers());
			
				template = getTemplate("/pages/userManagement.vm");

			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
