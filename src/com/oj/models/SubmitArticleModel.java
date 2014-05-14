package com.oj.models;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.oj.objects.Article;
import com.oj.objects.ArticleRevision;
import com.oj.objects.CriticismResponse;
import com.oj.objects.Keyword;
import com.oj.objects.User;

/** Submit Article model - methods for ................ 
 * @author Team:Master10
 */
public class SubmitArticleModel {

	/**
	 * Empty Constructor
	 */
	public SubmitArticleModel() {

	}
	
	public ArrayList<Object> getAuthorArticles(int authorID) throws SQLException {
		ConnectionManager conn=null;
		ArrayList<Object> arrayResults = new ArrayList<Object>();
		ArrayList<Article> articleResults = new ArrayList<Article>();
		ArrayList<ArticleRevision> filepathResults = new ArrayList<ArticleRevision>();
		String findArticles = "SELECT * FROM Author INNER JOIN ArticleAuthor ON Author.authorID = ArticleAuthor.authorID INNER JOIN Article ON ArticleAuthor.articleID = Article.articleID INNER JOIN ArticleRevision ON ArticleRevision.articleID = Article.articleID WHERE Author.authorID = '" + authorID + "'";
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(findArticles);
			while (rs.next()) {
				int counter = 0;
				int articleID = rs.getInt("Article.articleID");
				String title = (String) rs.getObject("Article.title");
				String summary = (String) rs.getObject("Article.summary");
				boolean published = rs.getBoolean("Article.published");
				boolean reviewed = rs.getBoolean("Article.reviewed");
				int pageNo = rs.getInt("Article.pageNo");
				
				if (articleResults != null) {
					for (Article article : articleResults) {
					    if (article.getArticleID() == (articleID)) {
					    	System.out.println("already in list");
					    	counter ++;
					    } else {
					    	System.out.println("new article");
					    }
					}
				}
				if (counter == 0) {
					Article article = new Article(articleID, title, summary, published, reviewed, pageNo);
					articleResults.add(article);
				}
				int articleRevisionID = rs.getInt("ArticleRevision.articleRevisionID");
				String filePath = (String) rs.getObject("ArticleRevision.filePath");
				Date dateSubmitted = rs.getDate("ArticleRevision.dateSubmitted");
				java.sql.Date sqlDate = new java.sql.Date(dateSubmitted.getTime());
				
				ArticleRevision revision = new ArticleRevision(articleRevisionID, articleID, filePath, sqlDate);
				filepathResults.add(revision);
			}
			
			arrayResults.add(articleResults);
			arrayResults.add(filepathResults);
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
	
	
	//Keyword - insert a new keyword in the database
	public void insertKeyword(ArrayList<Keyword> keywords) {
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();

			for (Keyword keyword : keywords) {
				//check if keyword already in database
				String getKeywordID = "SELECT keywordID FROM Keyword WHERE text ='" + keyword.getText()+ "' "; 
				ResultSet keywordResult=st.executeQuery(getKeywordID);
				if (keywordResult.next()) {
					System.out.println("keyword already in db - do nothing");
					//else insert the keyword in the database
				} else {
					String keywordText = keyword.getText();
					String updateQuery = "INSERT INTO Keyword (text) VALUES ('" + keywordText + "')";
					st.executeUpdate(updateQuery);
				}
				keywordResult.close();
			} 
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
	}

	//Article - insert a new article in the database
	public void insertArticle(Article article) {
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();

			String articleTitle = article.getTitle();
			String articleSummary = article.getSummary();
			int pageNum = article.getPageNo();

			String updateQuery = "INSERT INTO Article (title, summary, pageNo) VALUES ('" + articleTitle + "', '" + articleSummary + "', '" + pageNum + "')";
			st.executeUpdate(updateQuery);
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
	}

	//ArticleKeyword - insert article's keywords in the database
	public void updateArticleKeyword(Article article, ArrayList<Keyword> keywords) {
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			article = getArticleWithID(article);
			//find the ID for each keyword
			for (Keyword keyword : keywords) {
				String getKeywordID = "SELECT keywordID FROM Keyword WHERE text ='" + keyword.getText()+ "' "; 
				ResultSet keywordResult=st.executeQuery(getKeywordID);
				if (keywordResult.next()) {
					int keywordID = keywordResult.getInt("keywordID");
					keyword.setKeywordID(keywordID);
				}
				keywordResult.close();
				//insert article's keyword in database
				String insertArticleKeyword = "INSERT INTO ArticleKeyword (articleID, keywordID) VALUES ('" + article.getArticleID() + "', '" + keyword.getKeywordID() + "')";
				st.executeUpdate(insertArticleKeyword);
			}
			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
	}

	//ArticleRevision - insert new article revision in database (insert article's path)
	public void insertArticleRevision(Article article, String path) {
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String insertQuery;
			//String titleExists= getFileName(title);
			if (path != null) {
				System.out.println("INSERT");
				Date now = new Date();
				Date subDate = new java.sql.Date(now.getTime());
				insertQuery = "INSERT INTO ArticleRevision (articleID, filePath, dateSubmitted) VALUES ('" + article.getArticleID() + "', '" + path + "', '" + subDate +"')";
				st.executeUpdate(insertQuery);
			} else {
				System.out.println("no file found");
				//	insertQuery = "UPDATE Template SET filePath ='"+path+"' WHERE title='"+title+"'";
			}

			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
	}

	//return article object with its ID
	public Article getArticleWithID(Article article) {
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement statement = conn.getInstance().getConnection().createStatement();
			//get the articles' ID
			String getArticleID = "SELECT articleID FROM Article WHERE title ='" + article.getTitle() + "' AND summary ='" + article.getSummary() + "' "; 
			ResultSet articleResult=statement.executeQuery(getArticleID);
			if (articleResult.next()) {
				int articleID = articleResult.getInt("articleID");
				article.setArticleID(articleID);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	//insert article's authors in the database
	public Boolean insertAuthors(Article article, ArrayList<User> authors) {
		boolean registered = false;
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			article.getArticleID();
			for(User author : authors) {
				System.out.println("in insert authors - name: " + author.getName() + ", surname: " + author.getSurname() + ", email: " + author.getEmail() + ", aff: " + author.getAffiliations());
				//check if author is already in the database
				int isMain = author.getIsMain()?1:0;
				int hasAccount = 0;
				//String getAuthorID = "SELECT authorID FROM Author WHERE email ='" + author.getEmail()+ "' "; 
				String getAuthorID = "SELECT * FROM Author WHERE email ='" + author.getEmail()+ "' "; 
				ResultSet authorExists = st.executeQuery(getAuthorID);
				if (authorExists.next()) {
					int authorID = authorExists.getInt("authorID");
					author.setAuthorID(authorID);
					hasAccount = authorExists.getBoolean("hasAccount")?1:0;
				} else {				
					//insert new author to database
					String insertAuthor = "INSERT INTO Author (name, surname, email, affiliations, hasAccount) VALUES ('" + author.getName() + "', '" + author.getSurname() + "', '" + author.getEmail() + "', '" + author.getAffiliations() + "', '" + hasAccount + "')";
					st.executeUpdate(insertAuthor);
					//find the ID for each author
					String getNewAuthorID = "SELECT authorID FROM Author WHERE email ='" + author.getEmail()+ "' "; 
					ResultSet authorResult = st.executeQuery(getNewAuthorID);
					if (authorResult.next()) {
						int authorID = authorResult.getInt("authorID");
						author.setAuthorID(authorID);
					}
					authorResult.close();
				}
				//insert article's authors in database
				String insertArticleAuthor = "INSERT INTO ArticleAuthor (articleID, authorID, isMainContact) VALUES ('" + article.getArticleID() + "', '" + author.getAuthorID() + "', '" + isMain + "')";
				st.executeUpdate(insertArticleAuthor);
				authorExists.close();
				if ((isMain == 1) && (hasAccount == 1)) {
					//already registered
					System.out.println("already registered - name: " + author.getName() + ", surname: " + author.getSurname() + ", email: " + author.getEmail());
					registered = true;
				} else if ((isMain == 1) && (hasAccount == 0)) {
					//call register author
					System.out.println("needs registration - name: " + author.getName() + ", surname: " + author.getSurname() + ", email: " + author.getEmail());
					registered = registerUser(author);
				}
			}

			st.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return registered;
	}
	
	public Boolean registerUser(User author) {
		boolean registered = false;
		ConnectionManager conn=null;
		//generate a 10-size random string containing alphanumeric characters
		String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@";
	    char[] text = new char[10];
	    Random rnd = new Random();
	    for (int i = 0; i < 10; i++) {
	        text[i] = characters.charAt(rnd.nextInt(characters.length()));
	    }
	    String authorText = new String(text);
	    System.out.println("pass: " + authorText);
	    try {
	    	//md5 operations
	    	MessageDigest digest;
			digest = MessageDigest.getInstance("MD5");
	        digest.update(authorText.getBytes());
	        byte byteData[] = digest.digest();
	        //convert the byte to hex format
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        System.out.println("MD5: " + sb.toString());
	        String pass = sb.toString();
			//insert author into authorReviewer table
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
	        String insertAuthorReviewer = "INSERT INTO AuthorReviewer (authorID, password) VALUES ('" + author.getAuthorID() + "', '" + pass + "')";
			st.executeUpdate(insertAuthorReviewer);
			//mark author as having an account
			String updateQuery = "UPDATE Author SET hasAccount = 1 WHERE authorID = '" + author.getAuthorID() + "'";
			st.executeUpdate(updateQuery);
			
			st.close();
			conn.close();
			registered = true;
			
			//send email with details
			ContactModel contactModel = new ContactModel();
			String name = author.getName() + " " + author.getSurname();
			String messageText = "Thanks for submitting your article. You can login into the website to track the process of your artice and review others with the following details: \n Username: " + author.getEmail() + "\n Password: " + authorText;
			String subject = "Automatic Message - Journal Registration";
			
	    	//call the method sendRegistrationEmail from contactModel object
			contactModel.sendEmail(name, author.getEmail(), subject, messageText);
	        
	        
	    } catch (Exception e) {
			// TODO Auto-generated catch block
	    	System.out.println("Error " + e);
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return registered;
	}

	//ArticleRevision - get the article revision from database (get article's path)
	public String getArticleRevision(String articleID) {
		String filepath = null;
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery;

			System.out.println("Select article revision");
			Date date = getArticleRevisionDate(articleID);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateForMySql = "";
			dateForMySql = sdf.format(date);
			
	        selectQuery = "Select ArticleRevision.filePath from ArticleRevision where articleID= '"+articleID +"' AND dateSubmitted= '"+dateForMySql+"'";

			ResultSet artResult = st.executeQuery(selectQuery);
			if (artResult.next()) {
				filepath = (String) artResult.getObject("filePath");
			}
			artResult.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return filepath;
	}
	
	//ArticleRevision - get the article revision from database (get article's path)
	public String getDownloadPath(String articleID, String articleRevisionID) {
		String filepath = null;
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery;

	        selectQuery = "Select ArticleRevision.filePath from ArticleRevision where articleID= '"+articleID +"' AND articleRevisionID = '"+ articleRevisionID +"'";

			ResultSet artResult = st.executeQuery(selectQuery);
			if (artResult.next()) {
				filepath = (String) artResult.getObject("filePath");
			}
			artResult.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return filepath;
	}

	//ArticleRevision - get the article revision from database (get article's recent revision date)
	public Date getArticleRevisionDate(String articleID) {
		Date recentDate= new Date();
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery;
			System.out.println("Select article revision date ");
			selectQuery = "Select ArticleRevision.dateSubmitted from ArticleRevision where articleID="+articleID;
			ArrayList<Date> date= new ArrayList<Date>();
			ResultSet artResult = st.executeQuery(selectQuery);
			while (artResult.next()) {
				Date d = (Date) artResult.getObject("dateSubmitted");
				date.add(d);
			}
			System.out.println("Date arrayList: "+ date);
			int i=1;
			if(!date.isEmpty()){
				for(Date da: date){
					if(i==1){
						recentDate = da;
					}
					else {
						if(recentDate.before(da)){
						}
						recentDate = da;
					}
					i++;
				}
			}
			System.out.println("recentDate: "+recentDate);
			artResult.close();
			st.close();
			conn.close();
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
		return recentDate;
	}
	
	//Review - set isDownloaded
	public void setArticleDownloaded(String articleID, int authorID){
		ConnectionManager conn=null;
		try {
			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String insertQuery ="INSERT INTO Review (articleID, authorReviewerID,isDownloaded,reviewCount) VALUES ('" + articleID + "', '" + authorID + "','1','0')";
			st.executeUpdate(insertQuery);
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
	}

	//Response - insert into response table
	public void insertIntoResponses(ArrayList<CriticismResponse> myResponses){
		ConnectionManager conn=null;
		try {
			 conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			Statement st2 = conn.getInstance().getConnection().createStatement();
			Statement st3 = conn.getInstance().getConnection().createStatement();
			for (CriticismResponse res : myResponses) {
				int criticismID = res.getCriticismID(); 
				String responseText = res.getResponseText(); 
				
				String selectQuery ="SELECT * FROM Response WHERE criticismID = '" + criticismID + "'";
				ResultSet responses = st.executeQuery(selectQuery);
				if (responses.next()) {
					int responseID = (Integer)responses.getObject("responseID");
					System.out.println("updating criticism " + criticismID + " " +responseText);
					String updateQuery ="UPDATE Response SET responseText = '" + responseText + "'  WHERE responseID = '" + responseID + "'";
					st2.executeUpdate(updateQuery);
				} else {
					System.out.println("inserting criticism " + criticismID + " " +responseText);
					String insertQuery ="INSERT INTO Response (criticismID, responseText) VALUES ('" + criticismID + "', '" + responseText + "')";
					st3.executeUpdate(insertQuery);	
				}
				responses.close();
			} 
			st.close();
			st2.close();
			st3.close();
			conn.close();
		} catch(Exception e ) {
			System.out.println("Error " + e);
		}finally{
			if (conn!=null){
				conn.close();
			}
		}
	}
	//ArticleRevision - insert new article revision in database (insert article's path)
		public void insertUpdatedArticleRevision(Article article, String path) {
			ConnectionManager conn=null;
			try {
				 conn = new ConnectionManager();
				Statement st = conn.getInstance().getConnection().createStatement();
				String insertQuery;
				//String titleExists= getFileName(title);
				if (path != null) {
					System.out.println("INSERT article revision and update article");
					Date now = new Date();
					Date subDate = new java.sql.Date(now.getTime());
					insertQuery = "INSERT INTO ArticleRevision (articleID, filePath, dateSubmitted) VALUES ('" + article.getArticleID() + "', '" + path + "', '" + subDate +"')";
					st.executeUpdate(insertQuery);
				} else {
					System.out.println("no file found");
					//	insertQuery = "UPDATE Template SET filePath ='"+path+"' WHERE title='"+title+"'";
				} 

				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (conn!=null){
					conn.close();
				}
			}
		}
	
		public void updateArticleAfterRevision(Article article) {
			ConnectionManager conn=null;
			try {
				 conn = new ConnectionManager();
				Statement st = conn.getInstance().getConnection().createStatement();
				String updateQuery = "UPDATE Article SET needsRevision = 0 WHERE articleID = '" + article.getArticleID() + "'";
				st.executeUpdate(updateQuery);
				st.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (conn!=null){
					conn.close();
				}
			}

		}
}

