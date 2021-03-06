package com.oj.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.oj.objects.User;

/**
 * @author Master Team 10
 * 
 */
public class UserManagementModel {

	/**
	 * 
	 */
	public UserManagementModel() {
		
	}

	public ArrayList<User> getUsers() throws SQLException {
		ArrayList<User> arrayResults = new ArrayList<User>();
		ConnectionManager conn =null;
		String queryAuthor = "select Author.authorID, Author.name, Author.surname, Author.email, AuthorReviewer.isEditor, AuthorReviewer.authorReviewerID from Author INNER JOIN AuthorReviewer ON Author.authorID = AuthorReviewer.authorID where Author.hasAccount = 1";
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int userID = rs.getInt("Author.authorID");
				String userName = (String) rs.getObject("Author.name");
				String userSurname = (String) rs.getObject("Author.surname");
				String userEmail = (String) rs.getObject("Author.email");
				boolean isEditor = rs.getBoolean("AuthorReviewer.isEditor");
				int authorReviewerID = rs.getInt("AuthorReviewer.authorReviewerID");

				User user = new User(userID, userName, userSurname, userEmail, isEditor, authorReviewerID);
				arrayResults.add(user);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return arrayResults;
	}

	public boolean setUserRole(int isEditor, String userID) throws SQLException {
		
		boolean status = false;
		ConnectionManager conn =null;
		
		try {
			conn= new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String updateQuery = "UPDATE AuthorReviewer SET isEditor ='" + isEditor + "' WHERE authorReviewerID = '" + userID + "'";
			st.executeUpdate(updateQuery);
			st.close();
			conn.close();
			
			status = true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		
		return status;
		
	}

}

