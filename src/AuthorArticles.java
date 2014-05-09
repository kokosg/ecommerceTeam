
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.LoginModel;
import models.SubmitArticleModel;
import objects.Article;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class AuthorArticlesServlet
 */
public class AuthorArticles extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {
		Template template=null;
		Boolean haveReviews = false;
		context.put("apptitle", "Ecom Journal - My articles");
		HttpSession session = request.getSession();
		int authorID = 0;
		if (session.getAttribute("user") != null){
			authorID = (Integer) session.getAttribute("userID");
		} 
		SubmitArticleModel model=new SubmitArticleModel();
		LoginModel loginModel = new LoginModel();
		
		ArrayList<Object> arrayResults = new ArrayList<Object>();
		ArrayList<Article> articleResults = new ArrayList<Article>();
		SubmitArticleModel sam = new SubmitArticleModel();

		Hashtable allArticleReviews = new Hashtable();
		//ArrayList<Integer> myArticle = new ArrayList<Integer>();
		//ArrayList<Boolean> articleHasReviews = new ArrayList<Boolean>();
		//ArrayList<ArticleRevision> revisions = new ArrayList<ArticleRevision>();
		System.out.println("authorArticles");
		try {
			arrayResults = model.getAuthorArticles(authorID);
			articleResults = (ArrayList<Article>) arrayResults.get(0);
			//revisions = (ArrayList<ArticleRevision>) arrayResults.get(1);
	        //check if there are any reviews for my articles 
	        for (Article art : articleResults) {
	        	haveReviews = loginModel.retrieveArticlesReviews(art.getArticleID());
	        	allArticleReviews.put(art.getArticleID(), haveReviews);
	        	//myArticle.add(art.getArticleID());
	        	//articleHasReviews.add(haveReviews);
	        	System.out.println(haveReviews);
	        }
			
			haveReviews = loginModel.haveReviews(authorID);
			context.put("allArticles", allArticleReviews);
			//context.put("allArticles", myArticle);
        	//context.put("hasReviews", articleHasReviews);
			//System.out.println("test1: " + arrayResults.get(1));
			//System.out.println("test2: " + arrayResults.get(0));
		} catch(Exception e ) {
			System.out.println("Error " + e);
		} 
		
		
	    context.put("myArticles", articleResults);
	    //context.put("articleRevisions", revisions);
		template = getTemplate("/pages/myArticles.vm");
		return template;	
	}
}
