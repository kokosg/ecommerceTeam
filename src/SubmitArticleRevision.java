import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ReviewForm;
import objects.Review;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class SubmitArticleRevision
 */
public class SubmitArticleRevision extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;


	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {
		Template template=null;
		context.put("apptitle", "Ecom Journal - My articles");
		HttpSession session = request.getSession();
		//int authorID = (Integer) session.getAttribute("userID");
		
		ReviewForm model=new ReviewForm();

		if ((session.getAttribute("userID"))!=null){
			int articleID = Integer.parseInt(request.getParameter("id"));
			ArrayList<Review> reviewsArray = new ArrayList<Review>();
			//ArrayList<Article> articleResults = new ArrayList<Article>();
			//ArrayList<ArticleRevision> revisions = new ArrayList<ArticleRevision>();
			try {
				reviewsArray = model.haveReviews(articleID);
				System.out.println("submitting article servlet");
				//articleResults = (ArrayList<Article>) arrayResults.get(0);
				//revisions = (ArrayList<ArticleRevision>) arrayResults.get(1);


				//System.out.println("test1: " + arrayResults.get(1));
				//System.out.println("test2: " + arrayResults.get(0));
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}

			context.put("myArticles", reviewsArray);
			//context.put("articleRevisions", revisions);
			template = getTemplate("/forms/submitArticleRevision.vm");
			
		}else{
			template = getTemplate("/forms/submitArticleRevision.vm");
		}
		return template;	
	}
}
