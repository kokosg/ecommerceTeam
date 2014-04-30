import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

      try {
         template = getTemplate("/forms/home.vm"); 
      } catch(Exception e ) {
         System.out.println("Error " + e);
      }
      return template;
   }
}