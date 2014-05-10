package com.oj.servlets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.Edition;
import com.oj.models.VolumeModel;

/**
 * Servlet implementation class EditEdition
 */
public class EditEdition extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - EditEdition");
		Template template = null;
		response.setContentType("text/html");

		//create model object
		Edition model = new Edition();
		VolumeModel VolumeModel = new VolumeModel();
		
		DateFormat currentDate = new SimpleDateFormat("yyyy/MM/dd");
		Date newDate = new Date();

		//get the parameters from the form
		String editionID = request.getParameter("editionID");
		
		//Initialised boolean to false
		boolean status = false;
			
		try {

			//if edition is not null
			if (editionID != null) {
				
				//get the parameters from the form
				int editionNo = Integer.parseInt(request.getParameter("editionNo"));
				int volumeID = Integer.parseInt(request.getParameter("volumeID"));
				String title = request.getParameter("title");

				//changed the status of the "status" value based on the results from the crate edition
				status = model.createEdition(editionID, volumeID, editionNo, title, currentDate.format(newDate));
				
				if (status) {
					context.put("successfully", "Edition has been created successfully");
				} else {
					context.put("error", "Problem occurred on Edition");
				}
				
			}
				//return Edition from Edition Model and put them in contexts
				context.put("Volume", VolumeModel.getVolume());
				context.put("Edition", model.getEdition());
				context.put("currentDate", currentDate.format(newDate));

				
				template = getTemplate("/pages/editEdition.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
