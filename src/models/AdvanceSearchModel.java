/**
 * 
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Article;

/**
 * @author Master Team 10
 *
 */
public class AdvanceSearchModel {

	/**
	 * 
	 */
	public AdvanceSearchModel() {
		// TODO Auto-generated constructor stub
	}
	
	public Article getArticleObject(String queryName) throws SQLException{

		Article article = new Article();
		String query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Author INNER JOIN ArticleAuthor ON Author.authorID = ArticleAuthor.authorID INNER JOIN Article ON ArticleAuthor.articleID = Article.articleID where Author.name ='" + queryName + "' AND published = 1"; 
		System.out.print(query);
		try {
		      ConnectionManager conn = new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					int articleID = rs.getInt("Article.articleID");
					String title = (String)rs.getObject("Article.title");
			        String summary = (String)rs.getObject("Article.summary");
			        Boolean published = (Boolean)rs.getObject("Article.published");
			        Boolean reviewed = (Boolean)rs.getObject("Article.reviewed");
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

	public ArrayList<Article> getArticle(String queryName, String categoryType) throws SQLException{
		
    	ArrayList<Article> arrayResults = new ArrayList<Article>(); 
		
		String query = null;
		
		if (categoryType.equals("article")) {
		
			query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article where title LIKE '%" + queryName + "%'";
		

		} else if (categoryType.equals("keywords")) {
		
			query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Keyword INNER JOIN ArticleKeyword ON Keyword.keywordID = ArticleKeyword.keywordID INNER JOIN Article ON ArticleKeyword.articleID = Article.articleID where Keyword.text ='" + queryName + "' AND published = 1";

		}
		
		try {
			
		      ConnectionManager conn = new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				
				while (rs.next()) {
					int articleID = rs.getInt("Article.articleID");
					String title = (String)rs.getObject("Article.title");
			        String summary = (String)rs.getObject("Article.summary");
			        Boolean published = (Boolean)rs.getObject("Article.published");
			        Boolean reviewed = (Boolean)rs.getObject("Article.reviewed");
			        int pageNo = rs.getInt("Article.pageNo");
					
			        Article article = new Article(articleID, title, summary, published, reviewed, pageNo);
			        arrayResults.add(article);
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

	public ArrayList<Article> getAuthorsArticle(String datepickerFrom, String datepickerTo, String categoryType) throws SQLException{
		
    	ArrayList<Article> arrayResults = new ArrayList<Article>(); 
		
		String query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article INNER JOIN ArticleRevision ON Article.articleID = ArticleRevision.articleID WHERE ArticleRevision.dateSubmitted BETWEEN '" + datepickerFrom + "' AND '" + datepickerTo + "' AND published = 1";

		try {
			
		      ConnectionManager conn = new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				
				while (rs.next()) {
					
					int articleID = rs.getInt("Article.articleID");
					String title = (String)rs.getObject("Article.title");
			        String summary = (String)rs.getObject("Article.summary");
			        Boolean published = (Boolean)rs.getObject("Article.published");
			        Boolean reviewed = (Boolean)rs.getObject("Article.reviewed");
			        int pageNo = rs.getInt("Article.pageNo");
			        
			        Article article = new Article(articleID, title, summary, published, reviewed, pageNo);
			        arrayResults.add(article);
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
