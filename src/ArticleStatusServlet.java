
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.AbstractModel;
import objects.Article;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class ArticleStatusServlet
 */
public class ArticleStatusServlet extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {
		Template template=null;
		context.put("apptitle", "Ecom Journal - Home");
		HttpSession session = request.getSession();
		int authorID =(Integer) session.getAttribute("userID");
		AbstractModel absModel=new AbstractModel();
		ArrayList<Article> checkTitle =absModel.getChoiceTitle(authorID);
		System.out.println("checkTitle: "+ checkTitle);
		for(Article result :checkTitle ){
			System.out.println("Title :"+ result.getTitle()+ "Chosen  :" + result.isChosen() );
		}
	    context.put("artCkeckId", checkTitle);
		template = getTemplate("/pages/articleStatus.vm");
		return template;
	
		
		
		
		
	}
   }
