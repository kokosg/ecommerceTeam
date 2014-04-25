import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ConnectionManager;
import objects.Keyword;
import objects.Subscriber;
import objects.User;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;
/** Velocity View Servlet implementation class Subscribe 
 * @author Team:Master10
 */
public class Subscribe extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		
		String flag = request.getParameter("flag"); // get the value of a hidden field in a form to determine if we have posted data

		context.put("apptitle", "E-com Journal - Subscribe");
		Template template = null;
		response.setContentType("text/html");
        
		//if we haven't post any data then we have just to load the template
		if (flag == null) {
			try {
	            ConnectionManager conn = new ConnectionManager();	
	            ArrayList<Keyword> keywords = new ArrayList<Keyword>(); 
	            //return all keywords in the Keywords table
				String selectQuery = "SELECT * from Keyword"; 
				Statement st = conn.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					int keywordID = rs.getInt("Keyword.keywordID");
					String keywordText = (String)rs.getObject("Keyword.text");
		            //create keyword object and add it to an ArrayList
		        	Keyword keyword = new Keyword(keywordID, keywordText); 
		        	keywords.add(keyword);
				}
				context.put("keywords", keywords);
				template = getTemplate("/forms/subscribe.vm"); 
				//release resources
				rs.close();
				st.close();
				conn.close();	
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		//if we have posted data from a form do the following
		} else {
			//get form attributes' values
			String email = request.getParameter("email");
			String[] keywords = request.getParameterValues("keywords");
			String futureEditions = request.getParameter("futureEditions");
			//create a subscriber object
			Subscriber subs= new Subscriber();
			subs.setEmail(email);
			if (keywords.length > 0) {
				subs.setKeywordSubscriber(true);
			}
			if (futureEditions != null) {
				subs.setEditionSubscriber(true);
			}
			try {
				//-------------------->>> check if email exist if so update the row else create a new row  <<<----------------------
				
				//insert new data into Subscriber table
				ConnectionManager conn = new ConnectionManager();			
				String insertQuery = "INSERT INTO Subscriber (email, editionSubscriber, keywordSubscriber) VALUES ('" + subs.getEmail() + "'," + subs.getEditionSubscriber() + "," + subs.getKeywordSubscriber() + ")";
				Statement st = conn.getInstance().getConnection().createStatement();
				st.executeUpdate(insertQuery);
				//query Subscriber table to get subscriberID	
				String selectQuery = "SELECT subscriberID from Subscriber where email ='" + subs.getEmail() + " ' ";
				//st = conn.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(selectQuery);
				if (rs.next()) {
					int subscriberID = rs.getInt("subscriberID");
					subs.setSubscriberID(subscriberID);
				}				
				
				//System.out.println("email - " + email + ", future editions? - " + futureEditions);
				//insert new data into SubKeyword table
				for (int i = 0; i < keywords.length; i++) {
				    System.out.println(keywords[i]); 
					insertQuery = "INSERT INTO SubKeyword (keywordID, subscriberID) VALUES ('" + keywords[i] + "'," + subs.getSubscriberID() + ")";
					//st = conn.getInstance().getConnection().createStatement();
					st.executeUpdate(insertQuery);
				}
				template = getTemplate("/forms/home.vm"); 
				//release resources
				rs.close();
				st.close();
				conn.close();
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
}