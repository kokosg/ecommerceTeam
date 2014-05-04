/**
 * 
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Choices;

/**
 * @author Master Team 10
 *
 */
public class ReviewerManagementModel {

	/**
	 * 
	 */
	public ReviewerManagementModel() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Choices> getChoices() throws SQLException {
		ArrayList<Choices> arrayResults = new ArrayList<Choices>();
		String queryAuthor = "SELECT Article.articleID, Article.title, Choice.dateChosen, Choice.choiceID, AuthorReviewer.authorReviewerID, Author.authorID, Author.name, Author.surname, Author.email FROM Article INNER JOIN Choice ON Article.articleID = Choice.articleID INNER JOIN AuthorReviewer ON Choice.authorReviewerID = AuthorReviewer.authorReviewerID INNER JOIN Author ON AuthorReviewer.authorID = Author.authorID";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int articleID = rs.getInt("Article.articleID");
				String title = rs.getString("Article.title");
				int assignID = rs.getInt("Choice.choiceID");
				Date dateChosen = (Date) rs.getObject("Choice.dateChosen");
				int authorReviewerID = rs.getInt("AuthorReviewer.authorReviewerID");
				int authorID = rs.getInt("Author.authorID");
				String name = rs.getString("Author.name");
				String surname = rs.getString("Author.surname");
				String email = rs.getString("Author.email");
				
				Choices response = new Choices(articleID, title, assignID, dateChosen, authorReviewerID, authorID, name, surname, email);
				arrayResults.add(response);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
	
	public boolean deleteChoice(String assignID) throws SQLException {
		
		boolean status = false;
		
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String updateQuery = "DELETE FROM Choice WHERE assignID = '" + assignID + "'";
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
