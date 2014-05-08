import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SystemManagementmModel;
import objects.Article;
import objects.Review;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class PublishableArticles
 */
public class PublishArticle extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - Publish Articles");
		Template template = null;
		response.setContentType("text/html");
		

		try {

			
			//context.put("myArticles", publishableArticles);
			template = getTemplate("/forms/home.vm");
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return template;
	}
}