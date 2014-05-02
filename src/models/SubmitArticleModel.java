package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import objects.Article;
import objects.Keyword;
import objects.User;

/** Submit Article model - methods for ................ 
 * @author Team:Master10
 */
public class SubmitArticleModel {

	/**
	 * Empty Constructor
	 */
	public SubmitArticleModel() {
		
	}
	
	//Keyword - insert a new keyword in the database
	public void insertKeyword(ArrayList<Keyword> keywords) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			
			for (Keyword keyword : keywords) {
				String keywordText = keyword.getText();
				String updateQuery = "INSERT INTO Keyword (text) VALUES ('" + keywordText + "')";
				st.executeUpdate(updateQuery);
			} 
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
	}
	
	//Article - insert a new article in the database
	public void insertArticle(Article article) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			
			String articleTitle = article.getTitle();
			String articleSummary = article.getSummary();
			int pageNum = article.getPageNo();
			
			String updateQuery = "INSERT INTO Article (title, summary, pageNo) VALUES ('" + articleTitle + "', '" + articleSummary + "', '" + pageNum + "')";
			st.executeUpdate(updateQuery);
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
	}
	
	//ArticleKeyword - insert article's keywords in the database
	public void updateArticleKeyword(Article article, ArrayList<Keyword> keywords) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			article = getArticleWithID(article);
			//find the ID for each keyword
			for (Keyword keyword : keywords) {
				String getKeywordID = "SELECT keywordID FROM Keyword WHERE text ='" + keyword.getText()+ "' "; 
				ResultSet keywordResult=st.executeQuery(getKeywordID);
				if (keywordResult.next()) {
					int keywordID = keywordResult.getInt("keywordID");
					keyword.setKeywordID(keywordID);
				}
				keywordResult.close();
				//insert article's keyword in database
				String insertArticleKeyword = "INSERT INTO ArticleKeyword (articleID, keywordID) VALUES ('" + article.getArticleID() + "', '" + keyword.getKeywordID() + "')";
				st.executeUpdate(insertArticleKeyword);
			}
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
	}
	
	//ArticleRevision - insert new article revision in database (insert article's path)
	public void insertArticleRevision(Article article, String path) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String insertQuery;
			//String titleExists= getFileName(title);
			//if(titleExists==null){
				System.out.println("INSERT");
				Date now = new Date();
				Date subDate = new java.sql.Date(now.getTime());
				insertQuery = "INSERT INTO ArticleRevision (articleID, filePath, dateSubmitted) VALUES ('" + article.getArticleID() + "', '" + path + "', '" + subDate +"')";
			//} else {
			//	System.out.println("already exist");
			//	insertQuery = "UPDATE Template SET filePath ='"+path+"' WHERE title='"+title+"'";
			//}
			st.executeUpdate(insertQuery);
			st.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//return article object with its ID
	public Article getArticleWithID(Article article) {
		try {
			ConnectionManager connection = new ConnectionManager();
			Statement statement = connection.getInstance().getConnection().createStatement();
			//get the articles' ID
			String getArticleID = "SELECT articleID FROM Article WHERE title ='" + article.getTitle() + "' AND summary ='" + article.getSummary() + "' "; 
			ResultSet articleResult=statement.executeQuery(getArticleID);
			if (articleResult.next()) {
				int articleID = articleResult.getInt("articleID");
				article.setArticleID(articleID);
			}
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}
	
	//insert article's authors in the database
	public void insertAuthors(Article article, ArrayList<User> authors) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			article.getArticleID();
			for(User author : authors) {
				System.out.println("in insert authors - name: " + author.getName() + ", surname: " + author.getSurname() + ", email: " + author.getEmail() + ", aff: " + author.getAffiliations());
				//insert authors to database
				int isMain = author.getIsMain()?1:0;
				String insertAuthor = "INSERT INTO Author (name, surname, email, affiliations, hasAccount) VALUES ('" + author.getName() + "', '" + author.getSurname() + "', '" + author.getEmail() + "', '" + author.getAffiliations() + "', '" + isMain + "')";
				st.executeUpdate(insertAuthor);
				//find the ID for each author
				String getKeywordID = "SELECT authorID FROM Author WHERE email ='" + author.getEmail()+ "' "; 
				ResultSet authorResult = st.executeQuery(getKeywordID);
				if (authorResult.next()) {
					int authorID = authorResult.getInt("authorID");
					author.setAuthorID(authorID);
				}
				authorResult.close();
				//insert article's authors in database
				String insertArticleAuthor = "INSERT INTO ArticleAuthor (articleID, authorID, isMainContact) VALUES ('" + article.getArticleID() + "', '" + author.getAuthorID() + "', '" + isMain + "')";
				st.executeUpdate(insertArticleAuthor);
				
			}
			
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
	}
	
}
