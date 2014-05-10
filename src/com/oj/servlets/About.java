package com.oj.servlets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class abstructPage
 */
public class About extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - Abstract Page");
		Template template = null;
		response.setContentType("text/html");

			try {
				template = getTemplate("/pages/about.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
}