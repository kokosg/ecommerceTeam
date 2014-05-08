/**
 * Servlet implementation class SelectedArticlesForReview
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.AbstractModel;
import models.ReviewForm;
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
		ReviewForm form = new ReviewForm();

		ArrayList<Integer> downloadedReview = absModel.getDownloaded(authorID);
		//context.put("downloadedReview", downloadedReview);
		//System.out.println("Downloaded ^^^^^^^^ "+downloadedReview);

		String unselect = request.getParameter("unselect");
		if(unselect!=null){
			System.out.println("String: selected servlet="+unselect);
			absModel.deleteChoice(authorID, Integer.parseInt(unselect));
		}
		ArrayList<Article> checkTitle =absModel.getChoiceTitle(authorID);
		for(Article result :checkTitle ){
			for(int reviewDwnld : downloadedReview){
				if(reviewDwnld==result.getArticleID()){
					result.setDownloaded(true);
					result.setReviewCount(form.getReviewCount(authorID, reviewDwnld));
				}
			}
			System.out.println("Title :"+ result.getTitle() +" Summary: "+result.getSummary()+ " Chosen  :" + result.isChosen()+"Revision:"+result.isResponsAvailable() );
		}

		if(checkTitle.isEmpty()){
			context.put("message", "You haven't selected any articles to Review. Go to unpublished articles to select articles to review.");
		}

		context.put("artCkeckId", checkTitle);
		template = getTemplate("/forms/selectedArticlesToReview.vm");
		return template;
	}
}