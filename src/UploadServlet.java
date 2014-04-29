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

import models.UploadModel;


public class UploadServlet extends VelocityViewServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "/Users/shreebha/Downloads/apache-tomcat-6.0.39/webapps/data/";
	// private String filePath;

	@Override
	public Template handleRequest( HttpServletRequest request, 
			HttpServletResponse response, Context context )
	{ 
		
		//process only if its multipart content
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
						}
						UploadModel uc = new UploadModel();
						String title = "Article_Review";
						uc.setFilePath(UPLOAD_DIRECTORY+name, title);
						//File uploaded successfully
						context.put("message", "File Uploaded Successfully");

					} catch (Exception ex) {
						context.put("message", "File Upload Failed due to " + ex);
					}          
				}
				else{
					context.put("message",
							"Sorry this Servlet only handles file upload request");
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

