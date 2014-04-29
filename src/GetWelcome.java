
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ConnectionManager;
import objects.Article;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

public class GetWelcome extends VelocityViewServlet {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
      /* get the template */
      Template template = null;
      context.put("apptitle", "Ecom Journal - Home");
	  response.setContentType("text/html");
      
      try {
			
    	  ConnectionManager conn = new ConnectionManager();			

		ArrayList<Article> arrayResults = new ArrayList<Article>(); 
    	  
		  String query = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article";
		
    	  Statement	st = conn.getInstance().getConnection().createStatement();
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
		  
		context.put("searchResults", arrayResults);
         template = getTemplate("/forms/home.vm"); 
      
      } catch(Exception e ) {
         System.out.println("Error " + e);
      }
      return template;
   }
}
