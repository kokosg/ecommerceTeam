import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ConnectionManager;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class AdvanceSearchServlet
 */
public class AdvanceSearch extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	public Template handleRequest( HttpServletRequest request, 
			HttpServletResponse response, Context context )
			{ 
			/* get the template */
			Template template = null;
			context.put("apptitle", "E-com Journal");
			Statement statement;
			try {
			ConnectionManager conn = new ConnectionManager();
			ArrayList twitterList = null;
			ArrayList tweetList = new ArrayList();
			String query = "";
			
//			String categoryType = request.getParameter("item");
//
//
//			System.out.println(categoryType);
//			
//			// validates before select from the table
////			if (pid.isEmpty() || (pid.equals("*"))) {
//			if (categoryType.equals("article")) {
//				query = "select * from User";
//			} else {
//				query = "select * from AuthorReviewer";
//			}
//
//			statement = conn.getInstance().getConnection().createStatement();
//			ResultSet result = statement.executeQuery(query);
//
//			while (result.next()) {
//				
//				System.out.println("11");
//				
//				/*twitterList = new ArrayList();
//				twitterList.add(result.getString(1));
//				twitterList.add(result.getString(2));
//				tweetList.add(twitterList);*/
//				
//				System.out.println(result.getString(2));
//				
//				response.getWriter().println("<tr><td>" + result.getString(1) + 
//						"<td>" + result.getString(2) + "</td></tr>");
//				
//				//System.out.println(tweetList.toString());
//
//			}
//			//request.setAttribute("tweetList", tweetList);
//			//request.getRequestDispatcher("forms/search.jsp").forward(request, response);
//			conn.close();
			template = getTemplate("/forms/advanceSearch.vm"); 
			} catch(Exception e ) {
			System.out.println("Error " + e);
			}
			return template;
			}
	

}
