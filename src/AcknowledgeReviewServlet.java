import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class AcknowledgeReviewServlet
 */
public class AcknowledgeReviewServlet extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
       
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		Template template=null;
		context.put("apptitle", "Ecom Journal - Home");
		
		
		
		
		template=getTemplate("/forms/profile.vm");
		return template;
		
	}
}
