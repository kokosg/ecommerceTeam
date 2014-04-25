import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ConnectionManager;
import objects.Article;
import objects.Keyword;
import objects.User;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class abstructPage
 */
public class abstractPage extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
	     
		System.out.println("in");
		
		String flag = request.getParameter("flag");
		context.put("apptitle", "E-com Journal - Abstruct Page");
		Template template=null;
		response.setContentType("text/html");
		
		if (flag == null) {
			System.out.println("if");
			try {
				
				ConnectionManager conn = new ConnectionManager();			
				
			    int articleID = Integer.parseInt(request.getParameter("id"));

			    
				//Article Query
			    
				String queryArticle = "";
				queryArticle = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article where articleID LIKE '%" + articleID + "%'";
				
				Statement st = conn.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(queryArticle);
												
				while (rs.next()) {
					
					int articleID2 = rs.getInt("Article.articleID");
					String title = (String)rs.getObject("Article.title");
			        String summary = (String)rs.getObject("Article.summary");
			        Boolean published = (Boolean)rs.getObject("Article.published");
			        Boolean reviewed = (Boolean)rs.getObject("Article.reviewed");
			        int pageNo = rs.getInt("Article.pageNo");
					
			        Article article = new Article(articleID2, title, summary, published, reviewed, pageNo);
					context.put("article", article);

				}
				
				//Keyword Query
			    
				String queryKeywords = "";
				queryKeywords = "select Keyword.keywordID, Keyword.text from Keyword INNER JOIN ArticleKeyword ON Keyword.keywordID = ArticleKeyword.keywordID INNER JOIN Article ON ArticleKeyword.articleID = Article.articleID where Article.articleID ='" + articleID + "'";
				
				rs = st.executeQuery(queryKeywords);
				
	            ArrayList<Keyword> arrayResults3 = new ArrayList<Keyword>(); 

				while (rs.next()) {

					System.out.println("a" + (String)rs.getObject("Keyword.text"));
					
					int keywordID = rs.getInt("Keyword.keywordID");
					String keywordText = (String)rs.getObject("Keyword.text");
		            
		        	Keyword keyword = new Keyword(keywordID, keywordText);
		        	arrayResults3.add(keyword);
		        	
				}
				
				//Authors Query
				
				String queryAuthor = "";
				queryAuthor = "select Author.name, Author.surname, Author.email from Author INNER JOIN ArticleAuthor ON Author.authorID = ArticleAuthor.authorID INNER JOIN Article ON ArticleAuthor.articleID = Article.articleID where Article.articleID = '" + articleID + "'"; 
								
				rs = st.executeQuery(queryAuthor);

				ArrayList<User> arrayResults2 = new ArrayList<User>(); 
								
				while (rs.next()) {
					
					String userName = (String)rs.getObject("Author.name");
			        String userSurname = (String)rs.getObject("Author.surname");
			        String userEmail = (String)rs.getObject("Author.email");
					
		        	User user = new User(userName, userSurname, userEmail);
			        arrayResults2.add(user);
					
				}
				
				context.put("authors", arrayResults2);
				context.put("keywords", arrayResults3);
				template = getTemplate("/forms/abstractPage.vm");
				
				rs.close();
				st.close();
				conn.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}

			return template; 
			   
		} else {
			System.out.println("else");

			try {
		          template = getTemplate("/forms/abstractPage.vm"); 
		       } catch(Exception e ) {
		          System.out.println("Error " + e);
		       }
		       return template;

	}
	}
}