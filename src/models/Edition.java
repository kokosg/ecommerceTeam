/**
 * 
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import objects.EditionObject;
import objects.EmailMessage;

/**
 * @author Master Team 10
 *
 */
public class Edition {

	/**
	 * 
	 */
	public Edition() {
		// TODO Auto-generated constructor stub
	}
	
	public EditionObject getEdition() throws SQLException {
		String queryAuthor = "SELECT editionID, volumeID, editionNo, title, current, dateAdded, published FROM Edition ORDER BY EditionID DESC LIMIT 1";
		EditionObject edition = new EditionObject();
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int editionID = rs.getInt("editionID");
				int volumeID = rs.getInt("volumeID");
				int editionNo = rs.getInt("editionNo");
				String title = (String) rs.getObject("title");
				boolean current = (Boolean) rs.getObject("current");
				Date dateAdded = (Date) rs.getObject("dateAdded");
				boolean published = (Boolean) rs.getObject("published");
				edition = new EditionObject(editionID, volumeID, editionNo, title, current, dateAdded, published);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return edition;
	}
	
	public boolean createEdition(String editionID, int volumeID, int editionNo, String title, String dateAdded) throws SQLException {
		
		boolean status = false;
		
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String insertQuery = "INSERT INTO Edition (volumeID, editionNo, title, dateAdded) VALUES ('" + volumeID + "','" + editionNo + "','" + title + "', '" + dateAdded + "')";
			st.executeUpdate(insertQuery);
			
			String updateQuery = "UPDATE Edition SET current = 0, published = 1 WHERE editionID = '" + editionID + "'";
			st.executeUpdate(updateQuery);
			
			/////////////
			
			System.out.print("mpeni1 ");

			
			//DISTINCT
			String selectEdition = "select Subscriber.email, Article.title from Edition INNER JOIN Published ON Edition.editionID = Published.editionID INNER JOIN Article ON Published.articleID = Article.articleID  INNER JOIN ArticleKeyword ON Article.articleID = ArticleKeyword.articleID INNER JOIN Keyword ON Keyword.keywordID = ArticleKeyword.keywordID INNER JOIN SubKeyword ON SubKeyword.keywordID = Keyword.keywordID INNER JOIN Subscriber ON Subscriber.subscriberID = SubKeyword.subscriberID WHERE Article.published = 1 AND Edition.editionID = '" + editionID + "'";
			ResultSet rs = st.executeQuery(selectEdition);

			Multimap<String, String> map = HashMultimap.create();
			String subscriberEmail = null;
			
			while (rs.next()) {
				subscriberEmail = (String) rs.getObject("Subscriber.email");
				String articleTitle = (String) rs.getObject("Article.title");
				map.put(subscriberEmail, articleTitle);
			}
				
				 Set<String> emails = map.keySet();
				
				for (String email : emails) {
					Collection<String> articles = map.get(email);
					
					ContactModel contactModel = new ContactModel();
					
					System.out.println("Subscriber " + " Subscriber Email " + " " + email + " " + articles + " telos");
					
					String messageText = "The following article(s) match with your keywords: " + articles;
					
					//create EmailMessage object by passing values
			    	EmailMessage emailMessage = new EmailMessage("Subscriber", "Subscriber Email", email, messageText);
			    	  
			    	//call the method sendEmail from contactModel object and passing values in order to trigger the email function
					contactModel.sendEmail(emailMessage.getName(), emailMessage.getEmail(), emailMessage.getTitle(), emailMessage.getMessage());
			}
			
			////////////
			String articleQuery = "select DISTINCT Article.title from Edition INNER JOIN Published ON Edition.editionID = Published.editionID INNER JOIN Article ON Published.articleID = Article.articleID WHERE Article.published = 1 AND Edition.editionID = '" + editionID + "'";
			ResultSet rs2 = st.executeQuery(articleQuery);

			ArrayList<String> articles = new ArrayList<String>();
			
			while (rs2.next()) {
				String articleTitle = (String) rs2.getObject("Article.title");
				articles.add(articleTitle);
			}
			
			String subscriberQuery = "select DISTINCT Subscriber.email from Subscriber INNER JOIN SubKeyword ON Subscriber.editionSubscriber = 1";
			ResultSet rs3 = st.executeQuery(subscriberQuery);

			while (rs3.next()) {
				String subscribtionEmail = (String) rs3.getObject("Subscriber.email");
				
				
				ContactModel contactModel = new ContactModel();
				
				System.out.println("Subscriber " + " Subscriber Email " + " " + subscribtionEmail + " " + articles.toString() + " telos");
				
				String messageText = "The following article(s) has been published: " + articles.toString();
				
				//create EmailMessage object by passing values
		    	EmailMessage emailMessage = new EmailMessage("Subscriber", "Subscriber Email", subscribtionEmail, messageText);
		    	  
		    	//call the method sendEmail from contactModel object and passing values in order to trigger the email function
				contactModel.sendEmail(emailMessage.getName(), emailMessage.getEmail(), emailMessage.getTitle(), emailMessage.getMessage());
			
				
				/////////
				
			}
			
			
			st.close();
			conn.close();
			rs2.close();
			rs3.close();
			status = true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return status;
	}

}
