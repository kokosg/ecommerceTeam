/**
 * 
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import objects.EditionObject;
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
		String queryAuthor = "SELECT volumeID, journalID, volumeNo, date, current FROM Volume ORDER BY volumeID DESC LIMIT 1";
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
				boolean current = (Boolean) rs.getObject("current");
				volume = new VolumeObject(volumeID, journalID, volumeNo, date1,
						current);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return volume;
	}

	public boolean createVolume(String volumeID, String journalID,
			int volumeNo, String date) throws SQLException {

		boolean status = false;

		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String insertQuery = "INSERT INTO Volume (journalID, volumeNo, date) VALUES ('"	+ journalID + "','" + volumeNo + "','" + date + "')";
			st.executeUpdate(insertQuery);

			String updateQuery = "UPDATE Volume SET current = 0 WHERE volumeID = '"	+ volumeID + "'";
			st.executeUpdate(updateQuery);

			Edition editionModel = new Edition();

			EditionObject edition = new EditionObject();
			edition = editionModel.getEdition();

			String editionID = String.valueOf(edition.getEditionID());
			int editionNo = edition.geteditionNo();
			String title = edition.getTitle();
			int volume_ID = getVolume().getVolumeID();

			System.out.println("volume_ID " + volume_ID);
			
			java.util.Date newDate = new java.util.Date();
			Date subDate = new java.sql.Date(newDate.getTime());
			
			// changed the status of the "status" value based on the results
			// from the crate edition
			editionModel.createEdition(editionID, volume_ID, editionNo, title, String.valueOf(subDate));

			st.close();
			conn.close();

			status = true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return status;
	}

}
