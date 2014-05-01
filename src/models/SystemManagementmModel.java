/**
 * 
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Article;
import objects.ArticleRevision;
import objects.Criticism;
import objects.Response;
import objects.Review;

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
		String queryAuthor = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article";
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
		String queryAuthor = "select ArticleRevision.articleRevisionID, ArticleRevision.articleID, ArticleRevision.filePath, ArticleRevision.dateSubmitted from ArticleRevision";
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
		String queryAuthor = "select Review.reviewID, Review.authorReviewerID, Review.articleID, Review.judgement, Review.expertise, Review.summary, Review.criticismID, Review.smallErrors, Review.editorComments, Review.isAccepted, Review.dateSubmitted from Review WHERE Review.articleID = '" + articleI_D + "'";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int reviewID = rs.getInt("Review.reviewID");
				int authorReviewerID = rs.getInt("Review.authorReviewerID");
				int articleID = rs.getInt("Review.articleID");
				int judgement = rs.getInt("Review.judgement");
				int expertise = rs.getInt("Review.expertise");
				String summary = (String) rs.getObject("Review.summary");
				int criticismID = rs.getInt("Review.criticismID");
				String smallErrors = (String) rs.getObject("Review.smallErrors");
				String editorComments = (String) rs.getObject("Review.editorComments");
				boolean isAccepted =(boolean) rs.getObject("Review.isAccepted");
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
				boolean isCorrected = (boolean) rs.getObject("Criticism.isCorrected");
				boolean isAccepted = (boolean) rs.getObject("Criticism.isAccepted");
				
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
	
}
