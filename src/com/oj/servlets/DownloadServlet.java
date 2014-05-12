package com.oj.servlets;
import java.io.DataInputStream; 
import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.UploadModel;

public class DownloadServlet extends VelocityViewServlet {
	static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;
	Template template = null;

	public Template handleRequest( HttpServletRequest request, 
			HttpServletResponse response, Context context ) {

		int length = 0;
		context.put("apptitle", "E-com Journal");
		String TemplateTitle = new String();
		TemplateTitle=request.getParameter("downloadTitle");

		System.out.println("Template title :"+ TemplateTitle);

		ServletOutputStream outStream;
		try{
			UploadModel up = new UploadModel();
			String filePath = up.getFileName(TemplateTitle);
			File file = new File(filePath);
			outStream = response.getOutputStream();

			//response.setContentType("text/html");
			response.setContentLength((int) file.length());
			String[] filePathSplit=filePath.split("/");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\""+ filePathSplit[(filePathSplit.length)-1] + "\"");
//			response.setHeader("Content-Disposition", "attachment; filename=\""+ TemplateTitle + "\"");
			byte[] byteBuffer = new byte[BUFSIZE];
			DataInputStream in = new DataInputStream(new FileInputStream(file));

			while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
				outStream.write(byteBuffer, 0, length);
			}

			in.close();
			outStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return template;
	}
}
