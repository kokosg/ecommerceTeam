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

		System.out.println("in");

		String flag = request.getParameter("flag");
		context.put("apptitle", "E-com Journal - Abstract Page");
		Template template = null;
		response.setContentType("text/html");

		AbstractModel abstractModel = new AbstractModel();

		if (flag == null) {
			System.out.println("if");
			try {

				int article_ID = Integer.parseInt(request.getParameter("id"));

				context.put("article", abstractModel.getArticle(article_ID));

				context.put("keywords", abstractModel.getKeywords(article_ID));

				context.put("authors", abstractModel.getAuthor(article_ID));

				template = getTemplate("/forms/abstractPage.vm");

			} catch (Exception e) {
				System.out.println("Error " + e);
			}

			return template;

		} else {
			System.out.println("else");

			try {
				template = getTemplate("/forms/abstractPage.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;

		}
	}
}