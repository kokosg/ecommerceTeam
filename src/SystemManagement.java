import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SubmitArticleModel;
import models.SystemManagementmModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class SystemManagement
 */
public class SystemManagement extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;

	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {

		context.put("apptitle", "E-com Journal - UserManagement");
		Template template = null;
		response.setContentType("text/html");

		//create objects
		SystemManagementmModel model = new SystemManagementmModel();
		SubmitArticleModel sam = new SubmitArticleModel();
		
		try {
				//return getUsers from UserManagementModel and put them in contexts
				context.put("Articles", model.getArticle());
				context.put("ArticleRevisions", model.getArticleRevision());
				
				//get the parameters from the form
				String articleID = request.getParameter("articleID");
				String download = request.getParameter("download");
				String articleRevisionID = request.getParameter("articleRevisionID");
				
				if (download != null) {
					
				System.out.print("empika " + download);

				ServletOutputStream outStream;
				int length = 0;

				String filePath = sam.getDownloadPath(articleID, articleRevisionID);
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
				
				
				template = getTemplate("/pages/systemManagement.vm");
				
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
