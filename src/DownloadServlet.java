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
import models.UploadModel;

 public class DownloadServlet extends VelocityViewServlet {
        static final long serialVersionUID = 1L;
        private static final int BUFSIZE = 4096;
        private static final int BYTES_DOWNLOAD = 1024;
        private String filePath;
        Template template = null;
       

public void init() {
    // the file data.xls is under web application folder

    //filePath = getServletContext().getRealPath("")  + File.separator + "data.pdf";
	filePath="/Users/shreebha/Downloads/apache-tomcat-6.0.39/webapps/data/data.pdf";
}

public Template handleRequest( HttpServletRequest request, 
		HttpServletResponse response, Context context ) {
	 
    //filePath = getServletContext().getRealPath("") + File.separator + "abc.txt";
    File file = new File(filePath);
    int length = 0;
    ServletOutputStream outStream;
	try {
		outStream = response.getOutputStream();
		response.setContentType("text/html");
	    response.setContentLength((int) file.length());
	    UploadModel up = new UploadModel();
	    String filePath = up.getFileName("Article_Review");
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
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
	return template;

    // reads the file's bytes and writes them to the response stream
   
}
}