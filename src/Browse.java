
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BrowseModel;
import objects.BrowseObject;
import objects.EditionObject;

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
      String editionID = request.getParameter("editionID");

      try {
			
		context.put("searchResults", browseModel.getEdition());
		context.put("Editions", browseModel.getEdition());
		context.put("Volumes", browseModel.getVolume());
		
		ArrayList<BrowseObject> arrayResults = new ArrayList<BrowseObject>(); 
		
         //if edition is not null call the method which is in the model browseModel by passing a value
         if (editionID != null) {
        
        	 arrayResults = browseModel.getEdition(editionID);
        	 
        	 if (arrayResults.isEmpty()) {
				context.put("empty", "empty");
			} else {
		    	context.put("editionResults", arrayResults);
		    	context.put("Letters", browseModel.getMessages(editionID));
		    }
         }

         template = getTemplate("/pages/browse.vm"); 
         
      } catch(Exception e ) {
         System.out.println("Error " + e);
      }
      return template;
   }
}
