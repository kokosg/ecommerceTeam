
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.SubmitArticleModel;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class DownloadArticleReviewer
 */
public class DownloadArticleReviewer extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;

	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {

		Template template=null;
		int length = 0;
		SubmitArticleModel sam = new SubmitArticleModel();
		context.put("apptitle", "E-com Journal");
		if(ServletFileUpload.isMultipartContent(request)){
			String articleID="";
			ServletOutputStream outStream;
			try {

				HttpSession session = request.getSession();
				String UserRole=(String) session.getAttribute("userRole");
				int authorID = (int) session.getAttribute("userID");
				System.out.println(UserRole);
				if (UserRole == "AuthorReviewer"){
					try{
						if(ServletFileUpload.isMultipartContent(request)){
							List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

							for(FileItem item : multiparts){
								if(item.isFormField()){
									articleID = item.getFieldName();

									System.out.println("articleID: "+articleID);
								}
							}

							String filePath = sam.getArticleRevision(articleID);
							sam.setArticleDownloaded(articleID, authorID);
							System.out.println("filePAth: "+filePath);
							File file = new File(filePath);
							outStream = response.getOutputStream();

							response.setContentType("text/html");
							response.setContentLength((int) file.length());
							String[] filePathSplit=filePath.split("/");
							response.setHeader("Content-Disposition", "attachment; filename=\""
									+ filePathSplit[filePathSplit.length-1] + "\"");

							byte[] byteBuffer = new byte[BUFSIZE];
							DataInputStream in = new DataInputStream(new FileInputStream(file));

							while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
								outStream.write(byteBuffer, 0, length);
							}

							in.close();
							outStream.close();
						}
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			}catch (Exception e) {
				e.printStackTrace();				}
		}
		return template;
	}
}

