package models;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Keyword;
import objects.Subscriber;

/** Subscription model - methods for Keyword, Subscriber, SubKeyword tables 
 * @author Team:Master10
 */
public class SubscriptionModel {

	/**
	 * 
	 */
	public SubscriptionModel() {
		
	}
	
	//Keyword - Select * Query
	public ArrayList<Keyword> getAllKeywords() {
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String query = "SELECT * from Keyword";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int keywordID = rs.getInt("Keyword.keywordID");
				String keywordText = (String)rs.getObject("Keyword.text");
	            //create keyword object and add it to an ArrayList
	        	Keyword keyword = new Keyword(keywordID, keywordText); 
	        	keywords.add(keyword);
			}
			st.close();
			rs.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
		return keywords;
	}
	
	//Subscriber - get subscriberID from database
	public int getSubscriberID(String query) {
		int subscriberID = 0;
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				subscriberID = rs.getInt("subscriberID");
			}
			st.close();
			rs.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
		return subscriberID;
	}
	
	//Subscriber - add a new subscriber in the database
	public void insertSubscriber(Subscriber subs) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String insertQuery = "INSERT INTO Subscriber (email, editionSubscriber, keywordSubscriber) VALUES ('" + subs.getEmail() + "'," + subs.getEditionSubscriber() + "," + subs.getKeywordSubscriber() + ")";
			st.executeUpdate(insertQuery);
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
	}
	
	//Subscriber - update subscriber in database
	public void updateSubscriber(Subscriber subs) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String updateQuery = "UPDATE Subscriber SET editionSubscriber = " + subs.getEditionSubscriber() + ", keywordSubscriber = " + subs.getKeywordSubscriber() + " WHERE email = '" + subs.getEmail() + "'";
			st.executeUpdate(updateQuery);
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
	}
	
	//SubKeyword - update subKeyword in database
	public void updateSubKeyword(Subscriber subs, String[] keywords) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String deleteQuery = "DELETE FROM SubKeyword WHERE subscriberID = " + subs.getSubscriberID();
			st.executeUpdate(deleteQuery);
			if (keywords != null) {
				insertSubKeyword(subs, keywords);
			}
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
	}
	
	//SubKeyword - update subscriber in database
	public void insertSubKeyword(Subscriber subs, String[] keywords) {
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			for (int i = 0; i < keywords.length; i++) {
				String updateQuery = "INSERT INTO SubKeyword (keywordID, subscriberID) VALUES ('" + keywords[i] + "'," + subs.getSubscriberID() + ")";
				st.executeUpdate(updateQuery);
			}
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}
	}
	
}
