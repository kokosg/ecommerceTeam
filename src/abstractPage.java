
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class abstructPage
 */
public class abstractPage extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
       
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
	      /* get the template */
	      Template template = null;
	      context.put("apptitle", "Ecom Journal - abstractPage");
	      
	      int articleID = Integer.parseInt(request.getParameter("id"));
	      context.put("articleID", articleID);
	      try {
	         template = getTemplate("/forms/abstractPage.vm"); 
	      } catch(Exception e ) {
	         System.out.println("Error " + e);
	      }
	      return template;
	   }

}
