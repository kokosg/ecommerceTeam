/**
 * 
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.JournalObject;

/**
 * @author Master Team 10
 *
 */
public class CriticismModel {

	/**
	 * 
	 */
	public CriticismModel() {
		// TODO Auto-generated constructor stub
	}
	
	public JournalObject getCriticism() throws SQLException {
		String queryAuthor = "SELECT journalID, title, aimsGoals FROM Criticism";
		JournalObject journal = new JournalObject();
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int journalID = rs.getInt("journalID");
				String title = (String) rs.getObject("title");
				String aimsGoals = (String) rs.getObject("aimsGoals");

				journal = new JournalObject(journalID, title, aimsGoals);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return journal;
	}

}
