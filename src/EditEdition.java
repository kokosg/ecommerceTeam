import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Edition;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class EditEdition
 */
public class EditEdition extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - EditEdition");
		Template template = null;
		response.setContentType("text/html");

		//create JournalModel object
		Edition model = new Edition();
		
		DateFormat currentDate = new SimpleDateFormat("yyyy/MM/dd");
		Date newDate = new Date();

		//get the parameters from the form
		String editionID = request.getParameter("editionID");
		
		boolean status = false;
			
		try {

			if (editionID != null) {
				
				int editionNo = Integer.parseInt(request.getParameter("editionNo"));
				int volumeID = Integer.parseInt(request.getParameter("volumeID"));
				String title = request.getParameter("title");

				System.out.print("dasdas  " + editionID + " " + volumeID + " " + editionNo + " " + title + " " + currentDate.format(newDate));
				
				status = model.createEdition(editionID, volumeID, editionNo, title, currentDate.format(newDate));
				
				if (status) {
					context.put("successfully", "Edition has been created successfully");
				} else {
					context.put("error", "Problem occurred on Edition");
				}
				
			}
				//return getUsers from UserManagementModel and put them in contexts
			
				context.put("Edition", model.getEdition());
				context.put("currentDate", currentDate.format(newDate));

				
				template = getTemplate("/pages/editEdition.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
