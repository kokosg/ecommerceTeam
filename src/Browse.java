
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BrowseModel;

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
	  
	  //create BrowseModel object
      BrowseModel browseModel = new BrowseModel();

      //get the parameters from the form
      String edition = request.getParameter("editionNo");

      try {
			
		context.put("searchResults", browseModel.getBrowse());
		
         template = getTemplate("/pages/browse.vm"); 
        
         //if edition is not null call the method which is in the model browseModel by passing a value
         if (edition != null) {

        	 System.out.print("oo " + browseModel.getEdition(edition));
        	
     		context.put("editionResults", browseModel.getEdition(edition));
        	 

         }
      
      } catch(Exception e ) {
         System.out.println("Error " + e);
      }
      return template;
   }
}
