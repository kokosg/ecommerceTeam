/**
 * 
 */
package com.oj.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.oj.objects.BrowseObject;
import com.oj.objects.EditionObject;
import com.oj.objects.MessageObject;
import com.oj.objects.VolumeObject;

/**
 * @author Master Team 10
 *
 */
public class BrowseModel {

	/**
	 * 
	 */
	public BrowseModel() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<VolumeObject> getVolume() throws SQLException{
		ConnectionManager conn=null;
		ArrayList<VolumeObject> arrayResults = new ArrayList<VolumeObject>(); 
		  String query = "SELECT volumeID, journalID, volumeNo, date, current from Volume";
		try {
		       conn= new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					int volumeID = rs.getInt("volumeID");
					int journalID = rs.getInt("journalID");
			        int volumeNo = rs.getInt("volumeNo");
			        Date date = (Date)rs.getObject("date");
			        Boolean datePublished = (Boolean)rs.getObject("current");

			        VolumeObject volumeObject = new VolumeObject(volumeID, journalID, volumeNo, date, datePublished);
			        arrayResults.add(volumeObject);
				}
				rs.close();
				st.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (conn!=null){
					conn.close();
				}
			}
		return arrayResults;
	}
	
	public ArrayList<EditionObject> getEdition() throws SQLException{
		 ConnectionManager conn=null;
		ArrayList<EditionObject> arrayResults = new ArrayList<EditionObject>(); 
		  String query = "SELECT editionID, volumeID, editionNo, title, current, dateAdded, published from Edition WHERE published = 1";
		try {
		     conn = new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					int editionID = rs.getInt("editionID");
					int volumeID = rs.getInt("volumeID");
					int editionNo = rs.getInt("editionNo");
			        String title = (String)rs.getObject("title");
			        Boolean current = (Boolean)rs.getObject("current");
					Date dateAdded = (Date)rs.getObject("dateAdded");
					Boolean published = (Boolean)rs.getObject("published");

			        EditionObject editionObject = new EditionObject(editionID, volumeID, editionNo, title, current, dateAdded, published);
			        arrayResults.add(editionObject);
				}
				rs.close();
				st.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if (conn!=null){
					conn.close();
				}
			}
		return arrayResults;
	}
	
	public ArrayList<BrowseObject> getEdition(String editionID) throws SQLException {
		ConnectionManager conn=null;
		ArrayList<BrowseObject> arrayResults = new ArrayList<BrowseObject>(); 
		  String query = "SELECT Edition.editionNo, Published.startPageNo, Published.datePublished, Article.articleID, Article.title, Article.published, Article.reviewed, Article.needsRevision, Article.summary, Article.pageNo FROM Edition INNER JOIN Published ON Edition.editionID = Published.editionID INNER JOIN Article ON Published.articleID = Article.articleID WHERE Edition.editionID ='" + editionID + "' AND Edition.published = 1 AND Article.published = 1";
		try {
		      conn = new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
			        int editionNo = rs.getInt("Edition.editionNo");
			        int startPageNo = rs.getInt("Published.startPageNo");
			        Date datePublished = (Date)rs.getObject("Published.datePublished");
					int articleID = rs.getInt("Article.articleID");
			        String title = (String)rs.getObject("Article.title");
			        int published = rs.getInt("Article.published");
			        int reviewed = rs.getInt("Article.reviewed");
			        int needsRevision = rs.getInt("Article.needsRevision");
			        String summary = (String)rs.getObject("Article.summary");
			        int pageNo = rs.getInt("Article.pageNo");
			        BrowseObject browseObject = new BrowseObject(editionNo, startPageNo, datePublished, articleID, title, published, reviewed, needsRevision, summary, pageNo);
			        arrayResults.add(browseObject);
				}
				rs.close();
				st.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (conn!=null){
					conn.close();
				}
			}
		return arrayResults;
	}

	public ArrayList<MessageObject> getMessages(String editionID) throws SQLException {
		ConnectionManager conn=null;
		ArrayList<MessageObject> arrayResults = new ArrayList<MessageObject>(); 
		  String query = "SELECT DISTINCT Message.title, Message.message, Message.answer FROM Edition INNER JOIN Published ON Edition.editionID = Published.editionID INNER JOIN Message ON Message.editionID = Edition.editionID  WHERE Edition.editionID = '" + editionID + "' AND Edition.published = 1 AND Message.published = 1";
		try {
		       conn= new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
			        String title = (String)rs.getObject("Message.title");
			        String message = (String)rs.getObject("Message.message");
			        String answer = (String)rs.getObject("Message.answer");
			        MessageObject messageObject = new MessageObject(title, message, answer);
			        arrayResults.add(messageObject);
				}
				rs.close();
				st.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (conn!=null){
					conn.close();
				}
			}
		return arrayResults;
	}
	
}
