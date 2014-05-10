/**
 * 
 */
package com.oj.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.oj.objects.Article;
import com.oj.objects.ArticleRevision;
import com.oj.objects.Choices;
import com.oj.objects.Criticism;
import com.oj.objects.Response;
import com.oj.objects.Review;

/**
 * @author acp13gg
 *
 */
public class SystemManagementmModel {

	/**
	 * 
	 */
	public SystemManagementmModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Article> getArticle() throws SQLException {
		ArrayList<Article> arrayResults = new ArrayList<Article>();
		String queryAuthor = "select * from Article";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int articleID = rs.getInt("Article.articleID");
				String title = (String) rs.getObject("Article.title");
				String summary = (String) rs.getObject("Article.summary");
				boolean published = rs.getBoolean("Article.published");
				boolean reviewed = rs.getBoolean("Article.reviewed");
				int pageNo = rs.getInt("Article.pageNo");
				
				Article article = new Article(articleID, title, summary, published, reviewed, pageNo);
				arrayResults.add(article);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
	}

	
	public ArrayList<ArticleRevision> getArticleRevision() throws SQLException {
		ArrayList<ArticleRevision> arrayResults = new ArrayList<ArticleRevision>();
		String queryAuthor = "select ArticleRevision.articleRevisionID, ArticleRevision.articleID, ArticleRevision.filePath, ArticleRevision.dateSubmitted from ArticleRevision where dateSubmitted BETWEEN DATE_SUB(NOW(), INTERVAL 7 DAY) AND NOW()";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int articleRevisionID = rs.getInt("ArticleRevision.articleRevisionID");
				int articleID = rs.getInt("ArticleRevision.articleID");
				String filePath = (String) rs.getObject("ArticleRevision.filePath");
				Date dateSubmitted = (Date) rs.getObject("ArticleRevision.dateSubmitted");
				
				ArticleRevision articleRevision = new ArticleRevision(articleRevisionID, articleID, filePath, dateSubmitted);
				arrayResults.add(articleRevision);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
	
	
	public ArrayList<Review> getReviews(String articleI_D) throws SQLException {
		ArrayList<Review> arrayResults = new ArrayList<Review>();
		String queryAuthor = "select * from Review WHERE Review.articleID = '" + articleI_D + "'";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int reviewID = rs.getInt("Review.reviewID");
				int authorReviewerID = rs.getInt("Review.authorReviewerID");
				int articleID = rs.getInt("Review.articleID");
				String judgement = (String) rs.getObject("Review.judgement");
				String expertise = (String) rs.getObject("Review.expertise");
				String summary = (String) rs.getObject("Review.summary");
				int criticismID = rs.getInt("Review.criticismID");
				String smallErrors = (String) rs.getObject("Review.smallErrors");
				String editorComments = (String) rs.getObject("Review.editorComments");
				boolean isAccepted =(Boolean) rs.getObject("Review.isAccepted");
				Date dateSubmitted = (Date) rs.getObject("Review.dateSubmitted");
				
				Review review = new Review(reviewID, authorReviewerID, articleID, judgement, expertise, summary, criticismID, smallErrors, editorComments, isAccepted, dateSubmitted);
				arrayResults.add(review);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
	
	
	public ArrayList<Review> getAllReviews() throws SQLException {
		ArrayList<Review> arrayResults = new ArrayList<Review>();
		String queryAuthor = "select Review.reviewID, Review.authorReviewerID, Review.articleID, Review.judgement, Review.expertise, Review.summary, Review.criticismID, Review.smallErrors, Review.editorComments, Review.isAccepted, Review.dateSubmitted from Review where dateSubmitted BETWEEN DATE_SUB(NOW(), INTERVAL 7 DAY) AND NOW()";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int reviewID = rs.getInt("Review.reviewID");
				int authorReviewerID = rs.getInt("Review.authorReviewerID");
				int articleID = rs.getInt("Review.articleID");
				String judgement = (String)rs.getObject("Review.judgement");
				String expertise = (String)rs.getObject("Review.expertise");
				String summary = (String) rs.getObject("Review.summary");
				int criticismID = rs.getInt("Review.criticismID");
				String smallErrors = (String) rs.getObject("Review.smallErrors");
				String editorComments = (String) rs.getObject("Review.editorComments");
				boolean isAccepted =(Boolean) rs.getObject("Review.isAccepted");
				Date dateSubmitted = (Date) rs.getObject("Review.dateSubmitted");
				
				Review review = new Review(reviewID, authorReviewerID, articleID, judgement, expertise, summary, criticismID, smallErrors, editorComments, isAccepted, dateSubmitted);
				arrayResults.add(review);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
	
	public ArrayList<Choices> getAuthorReviewer() throws SQLException {
		ArrayList<Choices> arrayResults = new ArrayList<Choices>();
		String queryAuthor = "SELECT AuthorReviewer.authorReviewerID, Author.authorID, Author.name, Author.surname, Author.email FROM AuthorReviewer INNER JOIN Author ON AuthorReviewer.authorID = Author.authorID";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int authorReviewerID = rs.getInt("AuthorReviewer.authorReviewerID");
				int authorID = rs.getInt("Author.authorID");
				String name = rs.getString("Author.name");
				String surname = rs.getString("Author.surname");
				String email = rs.getString("Author.email");
				
				Choices response = new Choices(authorReviewerID, authorID, name, surname, email);
				arrayResults.add(response);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
	
	public ArrayList<Criticism> getCriticisms() throws SQLException {
		ArrayList<Criticism> arrayResults = new ArrayList<Criticism>();
		String queryAuthor = "SELECT Criticism.criticismID, Criticism.criticism, Criticism.reviewID, Criticism.isCorrected, Criticism.isAccepted FROM Criticism";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int criticismID = rs.getInt("Criticism.criticismID");
				String criticism = (String) rs.getObject("Criticism.criticism");
				int reviewID = rs.getInt("Criticism.reviewID");
				boolean isCorrected = (Boolean) rs.getObject("Criticism.isCorrected");
				boolean isAccepted = (Boolean) rs.getObject("Criticism.isAccepted");
				
				Criticism criticismObject = new Criticism(criticismID, criticism, reviewID, isCorrected, isAccepted);
				arrayResults.add(criticismObject);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
	
	public ArrayList<Response> getResponses() throws SQLException {
		ArrayList<Response> arrayResults = new ArrayList<Response>();
		String queryAuthor = "SELECT Response.responseID, Response.criticismID, Response.responseText FROM Response";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int responseID = rs.getInt("Response.responseID");
				int criticismID = rs.getInt("Response.criticismID");
				String responseText = rs.getString("Response.responseText");
				
				Response response = new Response(responseID, criticismID, responseText);
				arrayResults.add(response);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
	
	public boolean deleteReview(String reviewID, String authorReviewerID) throws SQLException {
		
		boolean deleteStatus = false;
		
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String updateQuery = "DELETE Review, Criticism FROM Review INNER JOIN Criticism ON Review.reviewID = Criticism.reviewID WHERE Review.reviewID = '" + reviewID + "' and Review.authorReviewerID = '" + authorReviewerID + "'";
			st.executeUpdate(updateQuery);
			st.close();
			conn.close();
			
			deleteStatus = true;
			System.out.print("Delete done");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return deleteStatus;
	
	}
	
	
	public ArrayList<Review> retrieveArticlesReviewsAndCriticism(int articleID) {
		ArrayList<Review> arrayResults = new ArrayList<Review>();
		String queryReviews = "SELECT * FROM Review INNER JOIN Criticism ON Review.reviewID = Criticism.reviewID WHERE articleID = '" + articleID + "'";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryReviews);
			while (rs.next()) {
				int reviewID = rs.getInt("Review.reviewID");
				int authorReviewerID = rs.getInt("Review.authorReviewerID");
				String judgement = (String) rs.getObject("Review.judgement");
				String expertise = (String) rs.getObject("Review.expertise");
				String summary = (String) rs.getObject("Review.summary");
				int criticismID = rs.getInt("Criticism.criticismID");
				String smallErrors = (String) rs.getObject("Review.smallErrors");
				String editorComments = (String) rs.getObject("Review.editorComments");
				boolean isAccepted =(Boolean) rs.getObject("Review.isAccepted");
				Date dateSubmitted = (Date) rs.getObject("Review.dateSubmitted");
				
				Review review = new Review(reviewID, authorReviewerID, articleID, judgement, expertise, summary, criticismID, smallErrors, editorComments, isAccepted, dateSubmitted);
				review.setCriticism((String)rs.getObject("Criticism.criticism"));
				arrayResults.add(review);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
	
	public ArrayList<Review> getAuthorReviews(int articleID) {
		ArrayList<Review> arrayResults = new ArrayList<Review>();
		String queryArticleAuthor = "SELECT * FROM ArticleAuthor INNER JOIN Author ON ArticleAuthor.authorID = Author.authorID INNER JOIN AuthorReviewer ON AuthorReviewer.authorID = Author.authorID WHERE articleID = '"+ articleID + "' AND isMainContact = 1";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			Statement st2 = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryArticleAuthor);

			while (rs.next()) {
				int authorReviewerID = rs.getInt("ArticleAuthor.authorID");
				//System.out.println("authorREviewerid: " + authorReviewerID);
				String queryReviews = "SELECT * FROM Review INNER JOIN Criticism ON Review.reviewID = Criticism.reviewID WHERE authorReviewerID = " + authorReviewerID;
				ResultSet rev = st2.executeQuery(queryReviews);				
				while (rev.next()) {
					int reviewID = rev.getInt("Review.reviewID");
					String judgement = (String) rev.getObject("Review.judgement");
					String expertise = (String) rev.getObject("Review.expertise");
					String summary = (String) rev.getObject("Review.summary");
					int criticismID = rev.getInt("Criticism.criticismID");
					String smallErrors = (String) rev.getObject("Review.smallErrors");
					String editorComments = (String) rev.getObject("Review.editorComments");
					boolean isAccepted =(Boolean) rev.getObject("Review.isAccepted");
					Date dateSubmitted = (Date) rev.getObject("Review.dateSubmitted");
					
					Review review = new Review(reviewID, authorReviewerID, articleID, judgement, expertise, summary, criticismID, smallErrors, editorComments, isAccepted, dateSubmitted);
					review.setCriticism((String)rev.getObject("Criticism.criticism"));
					arrayResults.add(review);
				}
				rev.close();
			}
			rs.close();
			st.close();
			st2.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
	
	
	
	//ArticleRevision - insert new article revision in database (insert article's path)
	public void publishArticle(int articleID, int editionID, java.util.Date date) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String insertQuery;
			System.out.println("publishing article");
			insertQuery = "INSERT INTO Published (articleID, editionID, startPageNo, datePublished) VALUES ('" + articleID + "', '" + editionID + "', 0 , '" + date +"')";
			st.executeUpdate(insertQuery);
			st.close();
			
			Statement st2 = conn.getInstance().getConnection().createStatement();
			String updateQuery = "UPDATE Article SET published =  1 WHERE articleID = " + articleID;
			st2.executeUpdate(updateQuery);
			st2.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
