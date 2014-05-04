

/**
 * Servlet implementation class SelectedArticlesForReview
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.AbstractModel;
import objects.Article;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

public class SelectedArticlesForReview extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {
		Template template=null;
		HttpSession session = request.getSession();
		int authorID =(Integer) session.getAttribute("userID");
		AbstractModel absModel=new AbstractModel();
		ArrayList<Article> checkTitle =absModel.getChoiceTitle(authorID);
		for(Article result :checkTitle ){
			System.out.println("Title :"+ result.getTitle() +" Summary: "+result.getSummary()+ " Chosen  :" + result.isChosen() );
		}
		context.put("artCkeckId", checkTitle);
		if(checkTitle.isEmpty()){
			context.put("message", "You haven't selected any articles to Review. Go to unpublished articles to select articles to review.");
		}
		
		String unselect = request.getParameter("unselect");
		
		if(unselect!=null){
			System.out.println("String: selected servlet="+unselect);
			absModel.deleteChoice(authorID, Integer.parseInt(unselect));
		}
		template = getTemplate("/forms/selectedArticlesToReview.vm");
		return template;
	
	}
   }
