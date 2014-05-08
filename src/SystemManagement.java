import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SystemManagementmModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class SystemManagement
 */
public class SystemManagement extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - UserManagement");
		Template template = null;
		response.setContentType("text/html");

		//create JournalModel object
		SystemManagementmModel model = new SystemManagementmModel();

		
		try {
				//return getUsers from UserManagementModel and put them in contexts
				context.put("Articles", model.getArticle());
				context.put("ArticleRevisions", model.getArticleRevision());
				
				template = getTemplate("/pages/systemManagement.vm");
				
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
