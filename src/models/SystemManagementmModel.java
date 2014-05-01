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
public class SystemManagementmModel {

	/**
	 * 
	 */
	public SystemManagementmModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<User> getUsers(int articleID) throws SQLException {
		ArrayList<User> arrayResults = new ArrayList<User>();
		String queryAuthor = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo, ArticleRevision.articleRevisionID, ArticleRevision.filePath, ArticleRevision.dateSubmitted from Article INNER JOIN ArticleRevision ON Article.articleID = ArticleRevision.articleID WHERE Article.articleID = '" + articleID + "'";
		try {
			ConnectionManager conn = new ConnectionManager();
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
		}
		return arrayResults;
	}

}
