/**
 * 
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.EditionObject;

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
		String queryAuthor = "SELECT editionID, volumeID, editionNo, title, current, dateAdded, published FROM Edition ORDER BY EditionID DESC LIMIT 1;";
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
			st.close();
			conn.close();
			
			status = true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return status;
	}

}
