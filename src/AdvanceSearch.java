import java.sql.Date;
import java.sql.ResultSet;
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
		System.out.println("empika");

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

					System.out.println(categoryType);
					System.out.println(queryName);
					
					if (categoryType.equals("article")) {
						query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo, Article.dateSubmitted from Article where title ='" + queryName + "'";
					} else if (categoryType.equals("author")) {
						query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo, Article.dateSubmitted from Author INNER JOIN ArticleAuthor ON Author.authorID = ArticleAuthor.authorID INNER JOIN Article ON ArticleAuthor.articleID = Article.articleID where Author.name ='" + queryName + "'"; 
					} else if (categoryType.equals("interval")) {
						query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo, Article.dateSubmitted from Keyword INNER JOIN ArticleKeyword ON Keyword.keywordID = ArticleKeyword.keywordID INNER JOIN Article ON ArticleKeyword.articleID = Article.articleID where Keyword.text ='" + queryName + "'";
					} else if (categoryType.equals("keywords")) {
						query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo, Article.dateSubmitted from Keyword INNER JOIN ArticleKeyword ON Keyword.keywordID = ArticleKeyword.keywordID INNER JOIN Article ON ArticleKeyword.articleID = Article.articleID where Keyword.text ='" + queryName + "'";
					}

					Statement st = conn.getInstance().getConnection().createStatement();
					ResultSet rs = st.executeQuery(query);
					
					ArrayList<Article> arrayResults = new ArrayList<Article>(); 

					while (rs.next()) {

						int articleID = rs.getInt("Article.articleID");
						String title = (String)rs.getObject("Article.title");
				        String summary = (String)rs.getObject("Article.summary");
				        Boolean published = (Boolean)rs.getObject("Article.published");
				        Boolean reviewed = (Boolean)rs.getObject("Article.reviewed");
				        int pageNo = rs.getInt("Article.pageNo");
				        Date dateSubmitted = (Date)rs.getObject("Article.dateSubmitted");
						
				        Article Article = new Article(articleID, title, summary, published, reviewed, pageNo, dateSubmitted);
				        arrayResults.add(Article);
						
					}
					
					context.put("searchResults", arrayResults);
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
}
