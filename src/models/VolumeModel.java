/**
 * 
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import objects.VolumeObject;

/**
 * @author Master Team 10
 *
 */
public class VolumeModel {

	/**
	 * 
	 */
	public VolumeModel() {
		// TODO Auto-generated constructor stub
	}
	
	public VolumeObject getVolume() throws SQLException {
		String queryAuthor = "SELECT volumeID, journalID, volumeNo, date, current FROM Volume ORDER BY volumeID DESC LIMIT 1;";
		VolumeObject volume = new VolumeObject();
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int volumeID = rs.getInt("volumeID");
				int journalID = rs.getInt("journalID");
				int volumeNo = rs.getInt("volumeNo");
				Date date1 = (Date) rs.getObject("date");
				boolean current = (boolean) rs.getObject("current");
				volume = new VolumeObject(volumeID, journalID, volumeNo, date1, current);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return volume;
	}
	
	public boolean createVolume(String journalID, int volumeNo, String date) throws SQLException {
		
		boolean status = false;
		
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String updateQuery = "INSERT INTO Volume (journalID, volumeNo, date) VALUES ('" + journalID + "','" + volumeNo + "','" + date + "')";
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
