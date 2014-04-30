
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ConnectionManager;
import objects.Article;
import objects.BrowseObject;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

public class Browse extends VelocityViewServlet {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
      /* get the template */
      Template template = null;
      context.put("apptitle", "Ecom Journal - Browse");
	  response.setContentType("text/html");
      
      try {
			
    	  ConnectionManager conn = new ConnectionManager();			

		ArrayList<BrowseObject> arrayResults = new ArrayList<BrowseObject>(); 
    	  
		  String query = "SELECT Volume.volumeID, Volume.volumeNo, Edition.editionNo, Edition.endDate, Published.startPageNo, Published.datePublished, Article.articleID, Article.title, Article.summary, Article.pageNo, ArticleRevision.filePath from Volume INNER JOIN Edition ON Volume.volumeID = Edition.volumeID INNER JOIN Published ON Edition.editionID = Published.editionID INNER JOIN Article ON Published.articleID = Article.articleID INNER JOIN ArticleRevision ON Article.articleID = ArticleRevision.articleID ";
		
    	  Statement	st = conn.getInstance().getConnection().createStatement();
		  ResultSet rs = st.executeQuery(query);

 		while (rs.next()) {
			int volumeID = (int)rs.getObject("Volume.volumeID");
			int volumeNo = (int)rs.getObject("Volume.volumeNo");
	        int editionNo = (int)rs.getObject("Edition.editionNo");
	        Date endDate = (Date)rs.getObject("Edition.endDate");
	        int startPageNo = rs.getInt("Published.startPageNo");
	        Date datePublished = (Date)rs.getObject("Published.datePublished");
			int articleID = (int)rs.getObject("Article.articleID");
	        String title = (String)rs.getObject("Article.title");
	        String summary = (String)rs.getObject("Article.summary");
	        int pageNo = (int)rs.getObject("Article.pageNo");
	        String filePath = (String)rs.getObject("ArticleRevision.filePath");
	        
	        BrowseObject browseObject = new BrowseObject(volumeID, volumeNo, editionNo, endDate, startPageNo, datePublished, articleID, title, summary, pageNo, filePath);
	        arrayResults.add(browseObject);
	        
	        System.out.print(browseObject);
	        
		}
		  
		context.put("searchResults", arrayResults);
         template = getTemplate("/pages/browse.vm"); 
      
      } catch(Exception e ) {
         System.out.println("Error " + e);
      }
      return template;
   }
}
