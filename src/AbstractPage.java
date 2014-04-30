import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AbstractModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class abstructPage
 */
public class AbstractPage extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		// get the value of a hidden field in a form to determine if we have posted data
		String flag = request.getParameter("flag");
		
		context.put("apptitle", "E-com Journal - Abstract Page");
		Template template = null;
		response.setContentType("text/html");
		
		//create a AbstractModel object
		AbstractModel abstractModel = new AbstractModel();

		//if we haven't post any data then we have just to load the template
		if (flag == null) {
			try {

				int article_ID = Integer.parseInt(request.getParameter("id"));

				//return all article, keywords, authors from database and put them in contexts
				context.put("article", abstractModel.getArticle(article_ID));
				context.put("keywords", abstractModel.getKeywords(article_ID));
				context.put("authors", abstractModel.getAuthor(article_ID));

				template = getTemplate("/forms/abstractPage.vm");

			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
			
		} else {
			try {
				template = getTemplate("/forms/abstractPage.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
}