import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ConnectionManager;
import objects.Keyword;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

public class Subscribe extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
		
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		
		String flag = request.getParameter("flag");
		//System.out.println(flag);
		context.put("apptitle", "E-com Journal - Subscribe");
		Template template=null;
		response.setContentType("text/html");
        
		//HttpSession session = request.getSession();
        //String userRole = (String)session.getAttribute("userRole");
        //if ((userRole == "AuthorReviewer") || (userRole == "Editor")) {
		if (flag == null) {
			//System.out.println("if");
			try {
	            ConnectionManager conn = new ConnectionManager();	
	            ArrayList<Keyword> keywords = new ArrayList<Keyword>(); 
				String selectQuery = "SELECT * from Keyword";
				Statement st = conn.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					int keywordID = rs.getInt("Keyword.keywordID");
					String keywordText = (String)rs.getObject("Keyword.text");
		            
		        	Keyword keyword = new Keyword(keywordID, keywordText);
		        	keywords.add(keyword);
		        	
				}
				//if {
				//}else {
				//	System.out.println("no results");
				//}
				context.put("keywords", keywords);
				template = getTemplate("/forms/subscribe.vm"); 
				rs.close();
				st.close();
				conn.close();	
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		} else {
			//System.out.println("else");
			return template;
		}
		//} else {
		//	render template show warning "you don't have permission to access this page"
		//}
	}
}

