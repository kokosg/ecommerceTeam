package com.oj.servlets;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.AbstractModel;
import com.oj.objects.Article;

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
			
			System.out.println("UnpublisheArticle unpubArticle :"+unpubArticle);
			
			String[] articleId= request.getParameterValues("article");
			
			if (articleId != null){
				System.out.println("not null");
				absModel.setReviewChoice(articleId,authorID);
				}
			unpubArticle=absModel.getUnpublishedArticle(authorID);
			for(Article a : unpubArticle){
				 if(absModel.checkData(a.getArticleID(), authorID)){
					 a.setSelected(true);
				 }else{
					 a.setSelected(false);
				 }
				}
			context.put("unpubArticle", unpubArticle);
			if (request.getParameter("select")!=null){
				if (request.getParameter("select").equals("Select")) {
					context.put("message", "You have selected unpublished Articles to review.All the best!!");
				}
			}

			template = getTemplate("/forms/unpublishedArticles.vm");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return template;
		
	}

}
