/**
 * 
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Article;
import objects.Keyword;
import objects.User;

/**
 * @author acp13gg
 * 
 */
public class AbstractModel {

	/**
	 * 
	 */
	public AbstractModel() {
		// TODO Auto-generated constructor stub
	}

	public Article getArticle(int article_ID) throws SQLException {
		String queryArticle = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article where articleID LIKE '%" + article_ID + "%'";
		Article article = new Article();
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryArticle);
			while (rs.next()) {
				int articleID = rs.getInt("Article.articleID");
				String title = (String) rs.getObject("Article.title");
				String summary = (String) rs.getObject("Article.summary");
				Boolean published = (Boolean) rs.getObject("Article.published");
				Boolean reviewed = (Boolean) rs.getObject("Article.reviewed");
				int pageNo = rs.getInt("Article.pageNo");
				article = new Article(articleID, title, summary, published, reviewed, pageNo);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}
	
	public ArrayList<Keyword> getKeywords(int article_ID) throws SQLException {
		
		ArrayList<Keyword> arrayResults = new ArrayList<Keyword>(); 
		String queryKeywords = "select Keyword.keywordID, Keyword.text from Keyword INNER JOIN ArticleKeyword ON Keyword.keywordID = ArticleKeyword.keywordID INNER JOIN Article ON ArticleKeyword.articleID = Article.articleID where Article.articleID ='" + article_ID + "'";
		Keyword keyword = new Keyword();
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryKeywords);
			while (rs.next()) {
				System.out.println("a" + (String)rs.getObject("Keyword.text"));
				int keywordID = rs.getInt("Keyword.keywordID");
				String keywordText = (String)rs.getObject("Keyword.text");
				
	        	keyword = new Keyword(keywordID, keywordText);
		        arrayResults.add(keyword);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(keyword.getKeywordID());
		return arrayResults;
	}
	
	
	public ArrayList<User> getAuthor(int article_ID) throws SQLException {
		
		ArrayList<User> arrayResults = new ArrayList<User>(); 
		String queryAuthor = "select Author.name, Author.surname, Author.email from Author INNER JOIN ArticleAuthor ON Author.authorID = ArticleAuthor.authorID INNER JOIN Article ON ArticleAuthor.articleID = Article.articleID where Article.articleID = '" + article_ID + "'"; 
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				String userName = (String)rs.getObject("Author.name");
		        String userSurname = (String)rs.getObject("Author.surname");
		        String userEmail = (String)rs.getObject("Author.email");
				
	        	User user = new User(userName, userSurname, userEmail);
		        arrayResults.add(user);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayResults;
	}

}
