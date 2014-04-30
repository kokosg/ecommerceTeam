import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/** Velocity View Servlet implementation class SubmitArticle 
 * @author Team:Master10
 */
public class SubmitArticle extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		
		String flag = request.getParameter("flag"); // get the value of a hidden field in a form to determine if we have posted data
		
		context.put("apptitle", "E-com Journal - Submit an Article");
		Template template = null;
		response.setContentType("text/html");
		//if we haven't post any data then we have just to load the template
		if (flag == null) {
			try {
				template = getTemplate("/forms/submitArticle.vm");
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		//if we have posted data from a form do the following
		} else {
			try {
				//print request
				Enumeration params = request.getParameterNames(); 
				while(params.hasMoreElements()){
				 String paramName = (String)params.nextElement();
				 System.out.println("Attribute Name - " + paramName + ", Value - " + request.getParameter("authorNames"));
				} 
				template = getTemplate("/forms/submitArticle.vm");
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
}