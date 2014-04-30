
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AdvanceSearchModel;
import objects.Article;

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
				AdvanceSearchModel model = new AdvanceSearchModel();
		    	ArrayList<Article> arrayResults = new ArrayList<Article>(); 

					String categoryType = request.getParameter("item");
					String queryName = request.getParameter("queryName");
					String datepickerFrom = request.getParameter("datepickerFrom");
					String datepickerTo = request.getParameter("datepickerTo");
					
					if (categoryType.equals("article")) {
						
						arrayResults = model.getArticle(queryName, categoryType);
					
					} else if (categoryType.equals("author")) {
						String[] parts = queryName.split(",");
						for( int i = 0; i <= parts.length - 1; i++)
						{
							queryName = parts[i]; 
							arrayResults.add(model.getArticleObject(queryName));
						}
					} else if (categoryType.equals("interval")) {
						
				    	System.out.print("aa1 " + datepickerFrom + " " +  datepickerTo);
						
						arrayResults = model.getAuthorsArticle(datepickerFrom, datepickerTo, categoryType);
						
					} else if (categoryType.equals("keywords")) {
						arrayResults = model.getArticle(queryName, categoryType);
					}

			        System.out.print(arrayResults.toString());
					
					if (arrayResults.isEmpty()) {
						context.put("empty", "empty");
					} else {
					context.put("searchResults", arrayResults);
					}
					
					template = getTemplate("/forms/advanceSearch.vm");
					System.out.println(arrayResults.toString());
					
					//rs.close();
					//st.close();
					//conn.close();
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template; 
		}
	}
	
}
