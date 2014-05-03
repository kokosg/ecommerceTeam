import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.VolumeModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class EditVolume
 */
public class EditVolume extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - EditVolume");
		Template template = null;
		response.setContentType("text/html");

		//create JournalModel object
		VolumeModel model = new VolumeModel();

		//get the parameters from the form
		String volumeID = request.getParameter("volumeID");
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		DateFormat currentDate = new SimpleDateFormat("yyyy/MM/dd");
		Date newDate = new Date();
		
		boolean status = false;
		
		try {

			if (volumeID != null) {
				
				status = model.createVolume(volumeID, currentYear, currentDate.format(newDate));
				

				if (status) {
					context.put("successfully", "Edition has been created successfully");
				} else {
					context.put("error", "Problem occurred on Edition");
				}
				
			}
				//return getUsers from UserManagementModel and put them in contexts
			
			
				context.put("Volume", model.getVolume());
				context.put("currentDate", currentDate.format(newDate));
				context.put("currentYear", currentYear);

				template = getTemplate("/pages/editVolume.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}