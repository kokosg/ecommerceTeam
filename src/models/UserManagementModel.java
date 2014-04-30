/**
 * 
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.User;

/**
 * @author acp13gg
 *
 */
public class UserManagementModel {

	/**
	 * 
	 */
	public UserManagementModel() {
		// TODO Auto-generated constructor stub
	}

	
	public ArrayList<User> getUsers() throws SQLException {
		
		ArrayList<User> arrayResults = new ArrayList<User>(); 
		String queryAuthor = "select Author.authorID, Author.name, Author.surname, Author.email, AuthorReviewer.isEditor from Author INNER JOIN AuthorReviewer ON Author.authorID = AuthorReviewer.authorID where Author.hasAccount = 1"; 
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int authorID = rs.getInt("Author.authorID");
				String userName = (String)rs.getObject("Author.name");
		        String userSurname = (String)rs.getObject("Author.surname");
		        String userEmail = (String)rs.getObject("Author.email");
		        boolean isEditor = rs.getBoolean("AuthorReviewer.isEditor");
				
	        	User user = new User(authorID, userName, userSurname, userEmail, isEditor);
		        arrayResults.add(user);
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
