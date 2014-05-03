
import java.sql.SQLException;
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
 * Servlet implementation class UnpublishedArticles
 */
public class UnpublishedArticles extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
       
	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {
		Template template = null;
		AbstractModel absModel=new AbstractModel();
		ArrayList<Article> unpubArticle=new ArrayList<Article>();
		HttpSession session = request.getSession();
		int authorID =(Integer) session.getAttribute("userID");
		
		try {
			
//			ArrayList<Integer> artCkeckId = absModel.getReviewChoice(authorID);
//			System.out.println("CHEEEEEEEEEEEEECK: "+artCkeckId);
//			context.put("artCheck",artCkeckId );
			unpubArticle=absModel.getUnpublishedArticle();
			for(Article a : unpubArticle){
				 if(absModel.checkData(a.getArticleID(), authorID)){
					 a.setSelected(true);
				 }else{
					 a.setSelected(false);
				 }
				}
			System.out.println("UnpublisheArticle unpubArticle :"+unpubArticle);
			context.put("unpubArticle", unpubArticle);
			for(Article a : unpubArticle){
				 System.out.print("article id" + a.getArticleID() + "     ");
				 System.out.print(a.isSelected());
				}
			String[] articleId= request.getParameterValues("article");
			
			if (articleId != null){
				System.out.println("not null");
				absModel.setReviewChoice(articleId,authorID);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		template = getTemplate("/forms/unpublishedArticles.vm");
		return template;
		
	}

}
