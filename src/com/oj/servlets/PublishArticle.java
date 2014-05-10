package com.oj.servlets;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.Edition;
import com.oj.models.SystemManagementmModel;
import com.oj.objects.EditionObject;

/**
 * Servlet implementation class PublishArticles
 */
public class PublishArticle extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - Publish Articles");
		Template template = null;
		response.setContentType("text/html");
		Edition model = new Edition();
		SystemManagementmModel sysModel = new SystemManagementmModel();

	    String articleID = request.getParameter("id");
	    
		try {
			EditionObject edition = model.getEdition();
			int editionID = edition.getEditionID();
			Date now = new Date();
			Date subDate = new java.sql.Date(now.getTime());
			sysModel.publishArticle(Integer.parseInt(articleID.replaceAll("\\D", "")), editionID, subDate);
			context.put("successfully", "Article published succesfully");
			template = getTemplate("/forms/home.vm");
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return template;
	}
}