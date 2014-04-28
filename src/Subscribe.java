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
			if (keywords != null) {
				subs.setKeywordSubscriber(true);
			} else {
				subs.setKeywordSubscriber(false);
			}
			if (futureEditions != null) {
				subs.setEditionSubscriber(true);
			} else {
				subs.setEditionSubscriber(false);
			}
			try {				
				ConnectionManager conn = new ConnectionManager();			
				String selectQuery = "SELECT subscriberID from Subscriber where email = '" + subs.getEmail() + "' ";
				Statement st = conn.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(selectQuery);
				//check if email does not exists, if so create a new row else update the row
				System.out.println(rs.next());
				if (!rs.next()) {
					//insert new data into Subscriber table		
					String insertQuery = "INSERT INTO Subscriber (email, editionSubscriber, keywordSubscriber) VALUES ('" + subs.getEmail() + "'," + subs.getEditionSubscriber() + "," + subs.getKeywordSubscriber() + ")";
					st.executeUpdate(insertQuery);
					//query Subscriber table to get subscriberID	
					//selectQuery = "SELECT subscriberID from Subscriber where email ='" + subs.getEmail() + "' ";
					rs = st.executeQuery(selectQuery);
					if (rs.next()) {
						int subscriberID = rs.getInt("subscriberID");
						subs.setSubscriberID(subscriberID);
					}				
					//insert new data into SubKeyword table
					if (keywords != null) {
						for (int i = 0; i < keywords.length; i++) {
							insertQuery = "INSERT INTO SubKeyword (keywordID, subscriberID) VALUES ('" + keywords[i] + "'," + subs.getSubscriberID() + ")";
							st.executeUpdate(insertQuery);
						}
					}
					template = getTemplate("/forms/home.vm"); 
				} else {
					System.out.println("else");
					if (rs.next()) {
						int subscriberID = rs.getInt("subscriberID");
						subs.setSubscriberID(subscriberID);
					}
					//update the subscribers' data in Subscriber table		
					String updateQuery = "UPDATE Subscriber SET editionSubscriber = " + subs.getEditionSubscriber() + ", keywordSubscriber = " + subs.getKeywordSubscriber() + " WHERE email = '" + subs.getEmail() + "'";
					st.executeUpdate(updateQuery);
					//update keywords data in SubKeyword table
					if (keywords != null) {
						String deleteQuery = "DELETE FROM SubKeyword WHERE subscriberID = " + subs.getSubscriberID();
						st.executeUpdate(deleteQuery);
						for (int i = 0; i < keywords.length; i++) {
							updateQuery = "INSERT INTO SubKeyword (keywordID, subscriberID) VALUES ('" + keywords[i] + "'," + subs.getSubscriberID() + ")";
							st.executeUpdate(updateQuery);
						}
					}
					template = getTemplate("/forms/home.vm"); 
				}
				System.out.println("end");
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