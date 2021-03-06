package com.oj.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.oj.objects.Article;
import com.oj.objects.Choice;
import com.oj.objects.Keyword;
import com.oj.objects.User;


public class AbstractModel {


	public AbstractModel() {
		// TODO Auto-generated constructor stub
	}

	//get the article based on the ID and return an object with all the details of the article
	public Article getArticle(String article_ID) throws SQLException {
		String queryArticle = "select Article.articleID, Article.title, Article.summary, Article.published, Article.reviewed, Article.pageNo from Article where articleID LIKE '%" + article_ID + "%'";
		Article article = null ;
		ConnectionManager conn = null;
		try {
			conn = new ConnectionManager();
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
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return article;
	}


	//return an ArrayList of keywords objects based on the article_ID
	public ArrayList<Keyword> getKeywords(String article_ID) throws SQLException {
		ConnectionManager conn=null;
		ArrayList<Keyword> arrayResults = new ArrayList<Keyword>(); 
		String queryKeywords = "select Keyword.keywordID, Keyword.text from Keyword INNER JOIN ArticleKeyword ON Keyword.keywordID = ArticleKeyword.keywordID INNER JOIN Article ON ArticleKeyword.articleID = Article.articleID where Article.articleID ='" + article_ID + "'";
		Keyword keyword = new Keyword();
		try {
			conn = new ConnectionManager();
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		System.out.print(keyword.getKeywordID());
		return arrayResults;
	}


	//return an ArrayList of Author objects based on the article_ID
	public ArrayList<User> getAuthor(String article_ID) throws SQLException {
		ConnectionManager conn=null;
		ArrayList<User> arrayResults = new ArrayList<User>(); 
		String queryAuthor = "select Author.name, Author.surname, Author.email from Author INNER JOIN ArticleAuthor ON Author.authorID = ArticleAuthor.authorID INNER JOIN Article ON ArticleAuthor.articleID = Article.articleID where Article.articleID = '" + article_ID + "'"; 
		try {
			conn = new ConnectionManager();
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return arrayResults;
	}

	//return an ArrayList of Article objects where articles are not published yet
	public ArrayList<Article> getUnpublishedArticle(int authorID) throws SQLException {
		ConnectionManager conn=null;
		ArrayList<Integer> intArtID = new ArrayList<Integer>();
		ArrayList<Integer> finalselectedArticleID = new  ArrayList<Integer>();
		ArrayList<Article> unpubArticle = new ArrayList<Article>();
		String queryArticle = "select ArticleAuthor.articleID from ArticleAuthor where ArticleAuthor.authorID =" + authorID;
		try {
			conn = new ConnectionManager();
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
				Statement st1 = conn.getInstance().getConnection().createStatement();
				String selectQuery2 = "Select articleID from Article where articleID!="+a;
				ResultSet rs1 = st1.executeQuery(selectQuery2);
				while(rs1.next()){
					int  articleId=rs1.getInt("articleID");
					if(!intArtID.contains(articleId)){
						if(!finalselectedArticleID.contains(articleId)){
							finalselectedArticleID.add(articleId);
						}
					}
				}
			}
			System.out.println("Final ids:"+finalselectedArticleID);
			for(int autID : finalselectedArticleID  ){
				Statement st2 = conn.getInstance().getConnection().createStatement();
				String q = "select articleID, title, summary from Article where articleID="+autID;
				ResultSet rs2 = st2.executeQuery(q);
				while (rs2.next()) {
					String unpublishedTitle = (String) rs2.getObject("title");
					String unpublishedSummary = (String) rs2.getObject("summary");
					int  articeIde=rs2.getInt("articleID");
					Article article2 = new Article(articeIde, unpublishedTitle, unpublishedSummary);
					unpubArticle.add(article2);
				}
				System.out.println(unpubArticle);
				rs2.close();
				st2.close();
			}
			st.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return unpubArticle;
	}

	public void setReviewChoice(String[] articleID,int authorID){
		ArrayList<Choice> resultID=new ArrayList<Choice>();
		//get current date time with Date()
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		ConnectionManager conn=null;
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

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
			if (conn!=null){
				conn.close();
			}
		}
	}

	public boolean checkData(int articleId,int authorID){
		ConnectionManager conn=null;
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
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return false;
	}
	public ArrayList<Article> getChoiceTitle(int authorID){

		//ArrayList<String> selectedArcticleID = new ArrayList<String>();
		ArrayList<Article> resultID=new ArrayList<Article>();
		ConnectionManager conn=null;
		try {
			String aTitle; 
			String aSummary;
			int aID;
			int flag =0;
			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery ="Select Article.articleID,Article.title,Article.summary from Article,Choice where Choice.authorReviewerID = '"+ authorID +"' and Article.articleID =Choice.articleID";
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next()) {
				Statement st1 = conn.getInstance().getConnection().createStatement();
				ResultSet rs1=null;
				aTitle= (String) rs.getObject("Article.title");
				//System.out.println("aTitle "+aTitle);
				aSummary= (String) rs.getObject("Article.summary");
				//System.out.println("aSummary "+aSummary);
				aID = (int) rs.getInt("Article.articleID");
				System.out.println("aID "+aID);
				String selectQuery2 = "Select responseText from Response where criticismID=(select criticismID from Review where articleID= "+aID+" and authorReviewerID=(select authorReviewerID from AuthorReviewer where authorID="+authorID+"))";
				rs1= st1.executeQuery(selectQuery2);
				String responseText = "";
				if(rs1.next()){
					responseText = (String) rs1.getObject("responseText");
					System.out.println("RESPONSE AVAILABLE: "+responseText);
				}
				if(responseText.isEmpty()){
					flag=0;
				}
				else{
					flag=1;
				}
				Article title = new Article(aID,aTitle,aSummary,true,flag);
				resultID.add(title);
				rs1.close();
			}

			st.close();
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return resultID;

	}
	public void deleteChoice(int authorID, int articleID){

		ConnectionManager conn=null;
		try {

			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery ="DELETE FROM Choice WHERE Choice.articleID = "+articleID+" and "+"Choice.authorReviewerID="+authorID;
			st.executeUpdate(selectQuery);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
	}

	public ArrayList<Integer> getDownloaded(int authorID){
		ConnectionManager conn=null;
		ArrayList<Integer> downloadedReview = new ArrayList<Integer>();
		try {

			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery ="SELECT Review.articleID FROM Review WHERE isDownloaded =1 and authorReviewerID ='"+authorID +"'";
			ResultSet rs= st.executeQuery(selectQuery);
			while (rs.next()){
				int r = (Integer) rs.getObject("articleID");
				downloadedReview.add(r);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return downloadedReview;

	}

	public String getResponse(String aID,int authorID){
		ConnectionManager conn=null;
		String responseText="";
		try {
			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery2 = "Select * from Response where criticismID=(select criticismID from Review where articleID="+aID+" and authorReviewerId=(select authorReviewerID from AuthorReviewer where authorID="+authorID+" and articleID="+aID+"))";
			ResultSet rs1 = st.executeQuery(selectQuery2);
			if(rs1.next()){
				responseText = (String) rs1.getObject("responseText");
				System.out.println("RESPONSE"+responseText);

			}
			rs1.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return responseText;
	}


	public void updateReviewCount(String articleID, int authorID) {
		ConnectionManager conn=null;
		try {

			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String updateQuery ="Update Review SET  reviewCount=0 where authorReviewerID="+authorID+" and articleID="+articleID;
			st.executeUpdate(updateQuery);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}

	}

	public void rejectedRevision(String articleID, int authorID,String rejectResponse){
		ConnectionManager conn=null;
		try {

			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();

			String selectQuery="Select rejectCount from Response where criticismID=( select criticismID from Review where reviewID=( select reviewID from Review where authorReviewerID="+authorID+" and articleID= "+articleID+" ))";
			ResultSet rs = st.executeQuery(selectQuery);
			int count=0;
			if(rs.next()){
				count = (Integer) rs.getObject("rejectCount");
			}
			rs.close();
			if(count<2){
				String updateQuery ="Update Response SET  rejectedResponse='"+rejectResponse+"' ,rejectCount='"+(count+1)+"' where criticismID=( select criticismID from Review where reviewID=( select reviewID from Review where authorReviewerID="+authorID+" and articleID= "+articleID+" ))";
				st.executeUpdate(updateQuery);
			}
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
	}

	public void setCriticismIsAcceptedbyReviwer(String articleID,int authorID){

		ConnectionManager conn=null;
		try {

			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String updateQuery ="Update Criticism SET  isAccepted=1 where reviewID=(select reviewID from Review where authorReviewerID="+authorID+" and articleID="+articleID+")";
			st.executeUpdate(updateQuery);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
	}
	
	public boolean isResponseAccepted(String articleID, int authorID){
		boolean accpeted = false;
		ConnectionManager conn=null;
		try{
			 conn= new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery="Select isAccepted from Criticism where criticismID=(select criticismID from Review where reviewID=(select reviewID from Review where authorReviewerID="+authorID+" and articleID= "+articleID+" ))";
			ResultSet rs = st.executeQuery(selectQuery);
			if(rs.next()){
				accpeted = (Boolean) rs.getObject("isAccepted");
				System.out.println("ACCCCCCCCCCCC"+accpeted);
			}
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return accpeted;
	}

	public int getResponseRejectCount(String articleID, int authorID){
		ConnectionManager conn=null;
		int count=0;
		try {

			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();

			String selectQuery="Select rejectCount from Response where criticismID=(select criticismID from Review where authorReviewerID="+authorID+" and articleID="+articleID+")";
			ResultSet rs = st.executeQuery(selectQuery);
			if(rs.next()){
				count = (Integer) rs.getObject("rejectCount");
			}
			rs.close();
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return count;
	}


}
