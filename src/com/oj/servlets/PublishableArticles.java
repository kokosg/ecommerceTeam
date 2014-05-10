package com.oj.servlets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.SystemManagementmModel;
import com.oj.objects.Article;
import com.oj.objects.Review;

/**
 * Servlet implementation class PublishableArticles
 */
public class PublishableArticles extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - Publishable Articles");
		Template template = null;
		response.setContentType("text/html");
		
		ArrayList<Article> allArticles = new ArrayList<Article>();
		ArrayList<Article> publishableArticles = new ArrayList<Article>();
		ArrayList<Review> articlesReviews = new ArrayList<Review>();
		ArrayList<Review> authorReviewsList = new ArrayList<Review>();
		SystemManagementmModel model = new SystemManagementmModel();
		
		try {				
			allArticles = model.getArticle();
			//context.put("ArticleRevisions", model.getArticleRevision());
			for (Article art: allArticles) {
				System.out.println("inside articles loop");
				if (art.isPublished() == false) {
					//move methods here
					Boolean articleHasMoreReviews = false, authorHasMoreReviews = false; 
					//get reviews for each article
					articlesReviews = model.retrieveArticlesReviewsAndCriticism(art.getArticleID());
					int reviewCount = 0;
					Set<Integer> uniqueReviewID = new HashSet<Integer>();
					for (Review rev: articlesReviews) {
						//System.out.println("revID: "+rev.getReviewID() + " judg: "+rev.getJudgement() + " critID: "+rev.getCriticismID() + " crit: "+rev.getCriticism());
						
						if (uniqueReviewID.add(rev.getReviewID())) {
							reviewCount++;
							//System.out.println("rev count: "+reviewCount);
						}
					}
					if (reviewCount > 2) {
						//System.out.println("articles' reviews are more than 2");
						articleHasMoreReviews = true;
					} else {
						//System.out.println("articles' reviews are less than 3");
					}
					
					int authorReviews = 0;
					authorReviewsList = model.getAuthorReviews(art.getArticleID());
					Set<Integer> uniqueReviewerID = new HashSet<Integer>();
					for (Review rev: authorReviewsList) {
						if (uniqueReviewerID.add(rev.getReviewID())) {
							authorReviews++;
							//System.out.println("author's rev count: "+ authorReviews);
						}
					}
					if (authorReviews > 2) {
						System.out.println("authors' reviews are more than 2");
						authorHasMoreReviews = true;
					} else {
						System.out.println("authors' reviews are less than 3");
					}
					
					if (articleHasMoreReviews && authorHasMoreReviews){
						System.out.println("good proceed");
						
						//get reviews for each article
						articlesReviews = model.retrieveArticlesReviewsAndCriticism(art.getArticleID());
						int champions = 0, detractors = 0, favourables = 0, indifferents = 0;
						Set<Integer> uniqueRevID = new HashSet<Integer>();
						for (Review rev: articlesReviews) {
							System.out.println("revID: "+rev.getReviewID() + " judg: "+rev.getJudgement() + " critID: "+rev.getCriticismID() + " crit: "+rev.getCriticism());
							if (uniqueRevID.add(rev.getReviewID())) {
								System.out.println("judgement =========== " + rev.getJudgement());
								if (rev.getJudgement().equals("Champion")) {
									champions ++;
								} else if (rev.getJudgement().equals("detractor")) {
									detractors ++;
								} else if (rev.getJudgement().equals("favourable")) {
									favourables ++;
								} else if (rev.getJudgement().equals("indifferent")) {
									indifferents ++;
								}
							}
						}
						if ((champions > 1) && (detractors == 0)) {
							//pass
							System.out.println("pass");
							publishableArticles.add(art);
						} else if ((detractors > 1) && (champions == 0)) {
							//reject
							System.out.println("reject");
						} else if ((champions > 0) && (favourables > 0)) {
							//pass
							System.out.println("pass");
							publishableArticles.add(art);
						} else if ((detractors > 0) && (indifferents > 0)) {
							//reject
							System.out.println("reject");
						} else if ((champions > 0) && (detractors > 0)) {
							//may be published interesting
							System.out.println("pass but interesting");
							publishableArticles.add(art);
						} else {
							//rejected??
							System.out.println("reject");
						}
											
					} else {
						System.out.println("not good");
					}
				} else {
					System.out.println("already published");
				}
			}
			
			context.put("myArticles", publishableArticles);
			template = getTemplate("/pages/publishableArticles.vm");
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return template;
	}
}