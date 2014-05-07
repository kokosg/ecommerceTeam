/**
 * 
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.BrowseObject;
import objects.EditionObject;
import objects.VolumeObject;

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
		ArrayList<VolumeObject> arrayResults = new ArrayList<VolumeObject>(); 
		  String query = "SELECT volumeID, journalID, volumeNo, date, current from Volume";
		try {
		      ConnectionManager conn = new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					int volumeID = rs.getInt("volumeID");
					int journalID = rs.getInt("journalID");
			        int volumeNo = rs.getInt("volumeNo");
			        Date date = (Date)rs.getObject("date");
			        Boolean datePublished = (boolean)rs.getObject("current");

			        VolumeObject volumeObject = new VolumeObject(volumeID, journalID, volumeNo, date, datePublished);
			        arrayResults.add(volumeObject);
				}
				rs.close();
				st.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return arrayResults;
	}
	
	public ArrayList<EditionObject> getEdition() throws SQLException{
		ArrayList<EditionObject> arrayResults = new ArrayList<EditionObject>(); 
		  String query = "SELECT editionID, volumeID, editionNo, title, current, dateAdded, published from Edition";
		try {
		      ConnectionManager conn = new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					int editionID = rs.getInt("editionID");
					int volumeID = rs.getInt("volumeID");
					int editionNo = rs.getInt("editionNo");
			        String title = (String)rs.getObject("title");
			        Boolean current = (boolean)rs.getObject("current");
					Date dateAdded = (Date)rs.getObject("dateAdded");
					Boolean published = (boolean)rs.getObject("published");

			        EditionObject editionObject = new EditionObject(editionID, volumeID, editionNo, title, current, dateAdded, published);
			        arrayResults.add(editionObject);
				}
				rs.close();
				st.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return arrayResults;
	}
	
	public ArrayList<BrowseObject> getEdition(String editionID) throws SQLException {
		ArrayList<BrowseObject> arrayResults = new ArrayList<BrowseObject>(); 
		  String query = "SELECT Edition.editionNo, Published.startPageNo, Published.datePublished, Article.articleID, Article.title, Article.published, Article.reviewed, Article.needsRevision, Article.summary, Article.pageNo, ArticleRevision.filePath FROM Edition INNER JOIN Published ON Edition.editionID = Published.editionID INNER JOIN Article ON Published.articleID = Article.articleID INNER JOIN ArticleRevision ON Article.articleID = ArticleRevision.articleID WHERE Edition.editionID ='" + editionID + "' AND Article.reviewed = 1";
		try {
		      ConnectionManager conn = new ConnectionManager();
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
			        String filePath = (String)rs.getObject("ArticleRevision.filePath");
			        BrowseObject browseObject = new BrowseObject(editionNo, startPageNo, datePublished, articleID, title, published, reviewed, needsRevision, summary, pageNo, filePath);
			        arrayResults.add(browseObject);
				}
				rs.close();
				st.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return arrayResults;
	}

}
