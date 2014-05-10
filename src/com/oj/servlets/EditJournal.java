package com.oj.servlets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.JournalModel;

/**
 * Servlet implementation class EditJournal
 */
public class EditJournal extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - EditJournal");
		Template template = null;
		response.setContentType("text/html");

		//create JournalModel object
		JournalModel model = new JournalModel();

		//get the parameters from the form
		String aimsGoals = request.getParameter("aimsGoals");
		
		//Initialised boolean to false
		boolean status = false;
		
		try {
			//if the aimsGoals is not null continue to update the request
			if (aimsGoals != null) {
				
				//page has only one journal, in case will have more than one this will remove
				int journalID = 1;
				
				//get the parameter from form
				String title = request.getParameter("title");
				
				//update the status and update the journal details by passing the parameters from the form
				status = model.setJournal(journalID, title, aimsGoals);
				
				if (status) {
					context.put("successfully", "Journal has been updated successfully");
				} else {
					context.put("error", "Problem occurred on Journal");
				}
				
			}
				//return journal details from Journal Model and put them in contexts
				context.put("searchResults", model.getJournal());
				
				template = getTemplate("/pages/journal.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
