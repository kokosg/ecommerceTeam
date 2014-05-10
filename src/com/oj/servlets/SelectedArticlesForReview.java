package com.oj.servlets;
/**
 * Servlet implementation class SelectedArticlesForReview
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.AbstractModel;
import com.oj.models.ReviewForm;
import com.oj.objects.Article;

public class SelectedArticlesForReview extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {
		Template template=null;
		context.put("apptitle", "E-Com Journal");
		HttpSession session = request.getSession();
		if(session.getAttribute("userID")!=null){
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
			System.out.println("Title :"+ result.getTitle() +" Summary: "+result.getSummary()+ " Chosen  :" + result.isChosen()+"\nResponse Avalable is here: "+result.isResponsAvailable() );
		}

		if(checkTitle.isEmpty()){
			context.put("message", "You haven't selected any articles to Review. Go to unpublished articles to select articles to review.");
		}
		context.put("artCkeckId", checkTitle);
		}
		template = getTemplate("/forms/selectedArticlesToReview.vm");
		return template;
	}
}