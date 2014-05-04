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

	public ArrayList<BrowseObject> getBrowse() throws SQLException{
		ArrayList<BrowseObject> arrayResults = new ArrayList<BrowseObject>(); 
		  String query = "SELECT Volume.volumeID, Volume.volumeNo, Edition.editionNo, Published.startPageNo, Published.datePublished, Article.articleID, Article.title, Article.summary, Article.pageNo, ArticleRevision.filePath from Volume INNER JOIN Edition ON Volume.volumeID = Edition.volumeID INNER JOIN Published ON Edition.editionID = Published.editionID INNER JOIN Article ON Published.articleID = Article.articleID INNER JOIN ArticleRevision ON Article.articleID = ArticleRevision.articleID ";
		try {
		      ConnectionManager conn = new ConnectionManager();
	    	  Statement st = conn.getInstance().getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					int volumeID = rs.getInt("Volume.volumeID");
					int volumeNo = rs.getInt("Volume.volumeNo");
			        int editionNo = rs.getInt("Edition.editionNo");
			        BrowseObject browseObject = new BrowseObject(volumeID, volumeNo, editionNo);
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
	
	
	public ArrayList<BrowseObject> getEdition(String edition) throws SQLException {
		ArrayList<BrowseObject> arrayResults = new ArrayList<BrowseObject>(); 
		  String query = "SELECT Edition.editionNo, Published.startPageNo, Published.datePublished, Article.articleID, Article.title, Article.summary, Article.pageNo, ArticleRevision.filePath FROM Edition INNER JOIN Published ON Edition.editionID = Published.editionID INNER JOIN Article ON Published.articleID = Article.articleID INNER JOIN ArticleRevision ON Article.articleID = ArticleRevision.articleID WHERE Edition.editionNo ='" + edition + "'";
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
			        String summary = (String)rs.getObject("Article.summary");
			        int pageNo = rs.getInt("Article.pageNo");
			        String filePath = (String)rs.getObject("ArticleRevision.filePath");
			        BrowseObject browseObject = new BrowseObject(editionNo, startPageNo, datePublished, articleID, title, summary, pageNo, filePath);
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
