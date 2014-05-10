package com.oj.servlets;
import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.UploadModel;


public class UploadServlet extends VelocityViewServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "/share/student/stucat004/fileupload/";//getServletContext().getRealPath("/fileupload");

	@Override
	public Template handleRequest( HttpServletRequest request, 
			HttpServletResponse response, Context context )
	{ 
		
		//process only if its multipart content
		context.put("apptitle", "E-com Journal");
		String TemplateTitle = null;

		System.out.println("UPLOAD SERVLET Template title :"+ TemplateTitle);
		Template template = null;
		try {
			
			HttpSession session = request.getSession();
			String UserRole=(String) session.getAttribute("userRole");
			System.out.println(UserRole);
			String name = null;
			if (UserRole == "Editor"){
				
				if(ServletFileUpload.isMultipartContent(request)){
					try {
						List<FileItem> multiparts = new ServletFileUpload(
								new DiskFileItemFactory()).parseRequest(request);

						for(FileItem item : multiparts){
							if(!item.isFormField()){
								name = new File(item.getName()).getName();
								item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
								System.out.println("name: of file:" + name);
							}
							else{
								TemplateTitle = item.getString();
							}
						}
						UploadModel uc = new UploadModel();
						uc.setFilePath(UPLOAD_DIRECTORY+name, TemplateTitle);
						//File uploaded successfully
						context.put("message", "File Uploaded Successfully");
						System.out.println("File Uploaded Successfully");

					} catch (Exception ex) {
						context.put("message", "File Upload Failed due to " + ex);
						System.out.println("File Upload Failed due to ");
					}          
				}
	
			}
			template = getTemplate("/forms/upload.vm"); 
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return template;
	}
}

