package com.oj.models;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.oj.objects.Article;
import com.oj.objects.Choices;
import com.oj.objects.Keyword;
import com.oj.objects.User;

public class LoginModel {

	/**
	 * Empty Constructor
	 */
	public LoginModel() {

	}
	
	//check if it has reviews and needs to upload article revision
	public Boolean haveReviews(int authorID) {
		Boolean haveReviews = false;
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			Statement st2 = conn.getInstance().getConnection().createStatement();
	        //retrieve all articles 
	        String findArticles = "SELECT * FROM ArticleAuthor INNER JOIN Article ON ArticleAuthor.articleID = Article.articleID WHERE authorID = '" + authorID + " AND isMainContact = 1 '";
	        ResultSet rs = st.executeQuery(findArticles);
	        ResultSet revRes = null;
			while (rs.next()) {
				int criticismCounter = 0;
				int articleID = rs.getInt("articleID");
				boolean needsRevision = rs.getBoolean("needsRevision");
				System.out.println("going over articles");
				//check if there are any reviews for my articles
		        String queryReviews = "SELECT * FROM Review INNER JOIN Criticism ON Review.reviewID = Criticism.reviewID WHERE articleID = '" + articleID + "'";
		        revRes = st2.executeQuery(queryReviews);
				while (revRes.next()) {
					criticismCounter++;
					System.out.println("found review " + criticismCounter);
				}
				if ((criticismCounter > 2) && (needsRevision)) {
					haveReviews = true;
					System.out.println("needs revision");
				}
			}
			revRes.close();
			rs.close();
			st2.close();
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
		return haveReviews;
	}
	
	public Boolean retrieveArticlesReviews(int articleID) {
		Boolean haveReviews = false;
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			Statement st2 = conn.getInstance().getConnection().createStatement();
	        //retrieve all articles 
	        String findArticle = "SELECT * FROM Article WHERE articleID = '" + articleID + "'";
	        ResultSet rs = st.executeQuery(findArticle);
	        ResultSet revRes = null;
			while (rs.next()) {
				int criticismCounter = 0;
				boolean needsRevision = rs.getBoolean("needsRevision");
				System.out.println("finding articles criticism");
				//check if there are any reviews for my articles
		        String queryReviews = "SELECT * FROM Review INNER JOIN Criticism ON Review.reviewID = Criticism.reviewID WHERE articleID = '" + articleID + "'";
		        revRes = st2.executeQuery(queryReviews);
				while (revRes.next()) {
					criticismCounter++;
					System.out.println("found review " + criticismCounter);
				}
				if ((criticismCounter > 2) && (needsRevision)) {
					haveReviews = true;
					System.out.println("needs revision");
				}
			}
			revRes.close();
			rs.close();
			st.close();
			st2.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
		return haveReviews;
	}
}
