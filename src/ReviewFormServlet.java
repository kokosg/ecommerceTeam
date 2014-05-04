
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ReviewForm;
import objects.Article;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class ReviewFormServlet
 */
public class ReviewFormServlet extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	Article article=new Article();
	public boolean LOOP =true;
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		Template template=null;
		context.put("apptitle", "Ecom Journal - Home");
		HttpSession session = request.getSession();
		int authorID =(Integer) session.getAttribute("userID");
		String UserRole=(String) session.getAttribute("userRole");

		if (UserRole == "AuthorReviewer"){
			if(LOOP){
				String articleID= request.getParameter("reviewArt");
				article.setArticleID(Integer.parseInt(articleID));
				LOOP=false;
			}
			else{

				int formart=article.getArticleID();
				System.out.println("formart :"+formart);
				String judge =request.getParameter("judge");
				String expertise =request.getParameter("level");
				String reviewSummary =request.getParameter("summary");
				String criticism =request.getParameter("criticism");
				String errors =request.getParameter("errors");
				String comments=request.getParameter("comments");
				ReviewForm form =new ReviewForm();
				form.insertReviewForm(authorID, article.getArticleID(), judge, expertise, reviewSummary,comments,criticism, errors);
			}
		}
		System.out.println("DVFEFVSFF^^^^^^^^^ "+request.getParameter("redirectAck"));
		if(request.getParameter("redirectAck")!=null){
			if(request.getParameter("redirectAck").equalsIgnoreCase("Submit")){
				template = getTemplate("/pages/acknowledgementReview.vm");
			}
		}
		else{
			template = getTemplate("/forms/reviewForm.vm");
		}
		return template;

	}
}
