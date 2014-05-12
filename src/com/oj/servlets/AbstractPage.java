package com.oj.servlets;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.AbstractModel;
import com.oj.models.SubmitArticleModel;

/**
 * Servlet implementation class abstructPage
 */
public class AbstractPage extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		// get the value of a hidden field in a form to determine if we have posted data
		String flag = request.getParameter("flag");

		context.put("apptitle", "E-com Journal - Abstract Page");
		Template template = null;
		response.setContentType("text/html");

		//create AbstractModel object
		AbstractModel abstractModel = new AbstractModel();
		SubmitArticleModel sam = new SubmitArticleModel();

		//if we haven't post any data then we have just to load the template
		if (flag == null) {
			try {

				//get the parameters from the form
				String article_ID = request.getParameter("id");
				String download = request.getParameter("download");

				System.out.print(article_ID + " aaa " + download);
				
				if (download != null) {
					
				System.out.print("empika " + download);

				ServletOutputStream outStream;
				int length = 0;

				String filePath = sam.getArticleRevision(article_ID);
				System.out.println("filePAth: "+filePath);
				File file = new File(filePath);
				outStream = response.getOutputStream();

				response.setContentType("application/octet-stream");
				response.setContentLength((int) file.length());
				String[] filePathSplit=filePath.split("/");
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ filePathSplit[(filePathSplit.length)-1] + "\"");

				byte[] byteBuffer = new byte[BUFSIZE];
				DataInputStream in = new DataInputStream(new FileInputStream(file));

				while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
					outStream.write(byteBuffer, 0, length);
				}

				in.close();
				outStream.close();
				}
				///////
				
				//return article, keywords and authors from abstractModel and put them in contexts
				context.put("article", abstractModel.getArticle(article_ID));
				context.put("keywords", abstractModel.getKeywords(article_ID));
				context.put("authors", abstractModel.getAuthor(article_ID));
				context.put("article_ID", article_ID);

				
				template = getTemplate("/forms/abstractPage.vm");

			} catch (Exception e) {
				System.out.println("Error " + e);
			}

			return template;

		} else {

			try {
				template = getTemplate("/forms/abstractPage.vm");
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;

		}
	}
}