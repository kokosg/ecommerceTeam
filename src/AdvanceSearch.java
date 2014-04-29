import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ConnectionManager;
import objects.Article;
import objects.Keyword;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class AdvanceSearchServlet
 */
public class AdvanceSearch extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		String flag = request.getParameter("flag");
		context.put("apptitle", "E-com Journal - Advance Search");
		Template template=null;
		response.setContentType("text/html");

		if (flag == null) {
			System.out.println("if");
			try {
		          template = getTemplate("/forms/advanceSearch.vm"); 
		       } catch(Exception e ) {
		          System.out.println("Error " + e);
		       }
		       return template;
		} else {
			System.out.println("else");
			try {
				ConnectionManager conn = new ConnectionManager();			
				
					String query = "";
					
					String categoryType = request.getParameter("item");
					String queryName = request.getParameter("queryName");
					String datepicker1 = request.getParameter("datepicker1");
					String datepicker2 = request.getParameter("datepicker2");

					System.out.println("o " + categoryType);
					
					System.out.println("a " + datepicker1 + " " + datepicker1);
					
					System.out.println(categoryType);
					System.out.println(queryName);
					
					Statement st = null;
					ResultSet rs = null;
					
					ArrayList<Article> arrayResults = new ArrayList<Article>(); 
					
					if (categoryType.equals("article")) {
					
						query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article where title LIKE '%" + queryName + "%'";
					
						queryMethod(st, rs, query, conn, arrayResults);

					
					} else if (categoryType.equals("author")) {
						
						String[] parts = queryName.split(",");
						
						for( int i = 0; i <= parts.length - 1; i++)
						{
							
							query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Author INNER JOIN ArticleAuthor ON Author.authorID = ArticleAuthor.authorID INNER JOIN Article ON ArticleAuthor.articleID = Article.articleID where Author.name ='" + parts[i] + "'"; 
							
							queryMethod(st, rs, query, conn, arrayResults);

							
						}
					
					} else if (categoryType.equals("interval")) {
						query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article INNER JOIN ArticleRevision ON Article.articleID = ArticleRevision.articleID WHERE ArticleRevision.dateSubmitted BETWEEN '" + datepicker1 + "' AND '" + datepicker2 + "'";
					
						queryMethod(st, rs, query, conn, arrayResults);
					
					} else if (categoryType.equals("keywords")) {
					
						query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Keyword INNER JOIN ArticleKeyword ON Keyword.keywordID = ArticleKeyword.keywordID INNER JOIN Article ON ArticleKeyword.articleID = Article.articleID where Keyword.text ='" + queryName + "'";
						
						queryMethod(st, rs, query, conn, arrayResults);
						
					}

					if (arrayResults.isEmpty()) {
						context.put("empty", "empty");
					} else {
					context.put("searchResults", arrayResults);
					}
					
					template = getTemplate("/forms/advanceSearch.vm");
					System.out.println(arrayResults.toString());
					
					rs.close();
					st.close();
					conn.close();
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template; 
		}
	}
	
	private void queryMethod(Statement st, ResultSet rs, String query, ConnectionManager conn, ArrayList<Article> arrayResults) throws SQLException, ClassNotFoundException {
		
		st = conn.getInstance().getConnection().createStatement();
		rs = st.executeQuery(query);
		
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
	}
	
}
