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

	//get the article based on the ID and return an object with all the details of the article
	public Article getArticle(int article_ID) throws SQLException {
		String queryArticle = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article where articleID LIKE '%" + article_ID + "%'";
		Article article = null ;
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


	//return an ArrayList of keywords objects based on the article_ID
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


	//return an ArrayList of Author objects based on the article_ID
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

	//return an ArrayList of Article objects where articles are not published yet
	public ArrayList<Article> getUnpublishedArticle(int authorID) throws SQLException {
		Article article;

		ArrayList<Integer> intArtID = new ArrayList<Integer>();
		ArrayList<Integer> intselectedAuthorID = new  ArrayList<Integer>();
		ArrayList<Article> unpubArticle = new ArrayList<Article>();


		String queryArticle = "select ArticleAuthor.articleID from ArticleAuthor where ArticleAuthor.authorID =" + authorID;
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryArticle);
			while (rs.next()) {
				int  articleId=rs.getInt("ArticleAuthor.articleID");
				System.out.println("articleIDs aayi:"+articleId);
				intArtID.add(articleId);
			}
			System.out.println(intArtID);
			rs.close();
			for(int a: intArtID){
				String queryAuthorsArticle = "select ArticleAuthor.authorID from ArticleAuthor where ArticleAuthor.articleID =" + a;
				ResultSet rs1 = st.executeQuery(queryAuthorsArticle);
				while(rs1.next()){
					System.out.println("authorIDs aayi:"+rs1.getInt("ArticleAuthor.authorID"));
					intselectedAuthorID.add(rs1.getInt("ArticleAuthor.authorID"));
				}
				rs1.close();
			}
			
			for(int autID : intselectedAuthorID  ){
				for(int arID :intArtID){
					String q = "select Article.articleID, Article.title, Article.summary from Article,ArticleAuthor where Article.published= 0 and Article.articleID=ArticleAuthor.articleID and Article.articleID!="+arID+" and ArticleAuthor.authorID !="+autID+" GROUP BY ArticleAuthor.articleID";
					ResultSet rs2 = st.executeQuery(q);
					while (rs2.next()) {
						String unpublishedTitle = (String) rs2.getObject("Article.title");
						String unpublishedSummary = (String) rs2.getObject("Article.summary");
						int  articeId=rs2.getInt("Article.articleID");
						article = new Article( articeId,unpublishedTitle, unpublishedSummary);
						System.out.println("unpub article select huye articleIDs:"+articeId);
						unpubArticle.add(article);
					}
					rs2.close();
					System.out.println(unpubArticle);
				}
			}
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

	public boolean checkData(int articleId,int authorID){
		ConnectionManager conn;
		try {
			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery ="Select * from Choice where authorReviewerID = '"+ authorID +"'";
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next()) {
				if( articleId == rs.getInt("Choice.articleID")){
					return true;
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
		return false;
	}
	public ArrayList<Article> getChoiceTitle(int authorID){

		//ArrayList<String> selectedArcticleID = new ArrayList<String>();
		ArrayList<Article> resultID=new ArrayList<Article>();
		ConnectionManager conn;
		try {
			String aTitle; 
			String aSummary;
			int aID;
			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery ="Select Article.articleID,Article.title,Article.summary from Article,Choice where Choice.authorReviewerID = '"+ authorID +"' and  Article.articleID =Choice.articleID";
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next()) {
				aTitle= (String) rs.getObject("Article.title");
				System.out.println("aTitle "+aTitle);
				aSummary= (String) rs.getObject("Article.summary");
				System.out.println("aSummary "+aSummary);
				aID = (int) rs.getInt("Article.articleID");
				System.out.println("aID "+aID);
				Article title = new Article(aID,aTitle,aSummary,true);
				resultID.add(title);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultID;

	}

	public void deleteChoice(int authorID, int articleID){

		ConnectionManager conn;
		try {

			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery ="DELETE FROM Choice WHERE Choice.articleID = "+articleID+" and "+"Choice.authorReviewerID="+authorID;
			st.executeUpdate(selectQuery);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getDownloaded(int authorID){
		ConnectionManager conn;
		ArrayList<Integer> downloadedReview = new ArrayList<Integer>();
		try {

			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery ="SELECT Review.articleID FROM REVIEW WHERE isDownloaded =1 and authorReviewerID ='"+authorID +"'";
			ResultSet rs= st.executeQuery(selectQuery);
			while (rs.next()){
				int r = (Integer) rs.getObject("articleID");
				downloadedReview.add(r);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return downloadedReview;

	}

}
