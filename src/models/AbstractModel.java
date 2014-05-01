package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import objects.Article;
import objects.Choice;
import objects.Keyword;
import objects.User;


public class AbstractModel {


	public AbstractModel() {
		// TODO Auto-generated constructor stub
	}

	public Article getArticle(int article_ID) throws SQLException {
		String queryArticle = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article where articleID LIKE '%" + article_ID + "%'";
		Article article = new Article();
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryArticle);
			while (rs.next()) {
				int articleID = rs.getInt("Article.articleID");
				String title = (String) rs.getObject("Article.title");
				String summary = (String) rs.getObject("Article.summary");
				Boolean published = (Boolean) rs.getObject("Article.published");
				Boolean reviewed = (Boolean) rs.getObject("Article.reviewed");
				int pageNo = rs.getInt("Article.pageNo");
				article = new Article(articleID, title, summary, published, reviewed, pageNo);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}
	
	public ArrayList<Keyword> getKeywords(int article_ID) throws SQLException {
		
		ArrayList<Keyword> arrayResults = new ArrayList<Keyword>(); 
		String queryKeywords = "select Keyword.keywordID, Keyword.text from Keyword INNER JOIN ArticleKeyword ON Keyword.keywordID = ArticleKeyword.keywordID INNER JOIN Article ON ArticleKeyword.articleID = Article.articleID where Article.articleID ='" + article_ID + "'";
		Keyword keyword = new Keyword();
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryKeywords);
			while (rs.next()) {
				System.out.println("a" + (String)rs.getObject("Keyword.text"));
				int keywordID = rs.getInt("Keyword.keywordID");
				String keywordText = (String)rs.getObject("Keyword.text");
				
	        	keyword = new Keyword(keywordID, keywordText);
		        arrayResults.add(keyword);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(keyword.getKeywordID());
		return arrayResults;
	}
	
	
	public ArrayList<User> getAuthor(int article_ID) throws SQLException {
		
		ArrayList<User> arrayResults = new ArrayList<User>(); 
		String queryAuthor = "select Author.name, Author.surname, Author.email from Author INNER JOIN ArticleAuthor ON Author.authorID = ArticleAuthor.authorID INNER JOIN Article ON ArticleAuthor.articleID = Article.articleID where Article.articleID = '" + article_ID + "'"; 
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				String userName = (String)rs.getObject("Author.name");
		        String userSurname = (String)rs.getObject("Author.surname");
		        String userEmail = (String)rs.getObject("Author.email");
				
	        	User user = new User(userName, userSurname, userEmail);
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

	public ArrayList<Article> getUnpublishedArticle() throws SQLException {
		Article article;

		ArrayList<Article> unpubArticle = new ArrayList<Article>();

		String queryArticle = "select Article.articleID, Article.title, Article.summary from Article where published= 0";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryArticle);
			while (rs.next()) {
				String unpublishedTitle = (String) rs.getObject("Article.title");
				String unpublishedSummary = (String) rs.getObject("Article.summary");
				int  articleId= (Integer) rs.getInt("Article.articleID");
				article = new Article( articleId,unpublishedTitle, unpublishedSummary);
				unpubArticle.add(article);
			}
			System.out.println(unpubArticle);
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unpubArticle;

	}

	public void setReviewChoice(String[] articleID,int authorID){
		ArrayList<Choice> resultID=new ArrayList<Choice>();
		//get current date time with Date()
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		ConnectionManager conn;
		try {
			int aID; 
			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery ="Select * from Choice where authorReviewerID = '"+ authorID +"'";
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next()) {
				aID= rs.getInt("Choice.articleID");
				Choice ch = new Choice(aID);
				resultID.add(ch);

			}
			if (resultID.isEmpty()){
				System.out.println("aid is null");
				for(int i = 0; i<articleID.length; i++){
					String queryReview = "INSERT INTO Choice (articleID, authorReviewerID, dateChosen) VALUE ('" + articleID[i] + "', '" + authorID + "', '" + dateFormat.format(date) + "')";
					st.executeUpdate(queryReview);
				}
			}else{
				for (int i=0 ;i< articleID.length;i++){
					boolean flag = false;
						for(Choice result :resultID ){
							int id=result.getArticleID();
							int viewID = Integer.parseInt(articleID[i]);
							if (id== viewID ){
								flag = true;
							}
						}
						if(flag==false){
							String queryReview = "INSERT INTO Choice (articleID, authorReviewerID, dateChosen) VALUE ('" + articleID[i] + "', '" + authorID + "', '" + dateFormat.format(date) + "')";
							st.executeUpdate(queryReview);
						}
				}
			}
			st.close();
			conn.close();

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
