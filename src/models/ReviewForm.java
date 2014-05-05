package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import objects.Review;

public class ReviewForm {
	private static int countReview=1;
	
	public ReviewForm(){
		
	}
	
	public void insertReviewForm(int authorID,int articleID,String judge,String expertise,String reviewSummary,String comments,String criticism,String errors){
		ConnectionManager conn;
		long reviewID = 0;
		long criticismID=0;
		//get current date time with Date()
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		//dateFormat.format(date)
		System.out.println(dateFormat.format(date));
		System.out.println("articleID Model :"+articleID );
		
		try {
			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String insertQuery = "UPDATE REVIEW SET judgement = '" + judge + "',expertise = '" + expertise + "',summary ='" + reviewSummary + "',editorComments = '" + comments + "',smallErrors = '" + errors + "',dateSubmitted = '" + dateFormat.format(date) + "' where authorReviewerID = '" + authorID + "' and articleID='" +articleID+"'";
			st.executeUpdate(insertQuery);
			Date reviewDate =getReviewRevisionDate(authorID,articleID);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String dateForMySQL=format.format(reviewDate);
			String selectQuery = "Select Review.reviewID from Review where authorReviewerID = '"+authorID+"' AND dateSubmitted = '"+dateForMySQL+"'";
			ResultSet rs=st.executeQuery(selectQuery);
			if (rs.next()){
				reviewID=(Long) rs.getObject("reviewID");
				System.out.println("reviewID "+reviewID);
			}
			rs.close();
			String insertQuery2 = "INSERT INTO Criticism (criticism, reviewID) VALUE ('" + criticism + "', '" + reviewID + "')";
			st.executeUpdate(insertQuery2);
			
			String selectQuery2 = "Select Criticism.criticismID from Criticism where reviewID = '"+reviewID+"'";
			ResultSet result=st.executeQuery(selectQuery2);
			if (result.next()){
				criticismID=(Long) result.getObject("criticismID");
				System.out.println("criticismID "+ criticismID);
			}
			String insertQuery3 = "UPDATE Review SET criticismID ='" + criticismID + "' where reviewID = '"+reviewID+"'";
			st.executeUpdate(insertQuery3);
			
			result.close();
			
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	//REVIEWREVISION - get the REVIEW revision from database (get review's recent revision date)
    public Date getReviewRevisionDate(int authorID,int articleID) {
            Date recentDate= new Date();
            try {
                    ConnectionManager conn = new ConnectionManager();
                    Statement st = conn.getInstance().getConnection().createStatement();
                    String selectQuery;
                    System.out.println("Select REVIEW revision date ");
                    selectQuery = "Select Review.dateSubmitted from Review where articleID="+articleID+" and authorReviewerID = "+authorID;
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
                                            if(recentDate.after(da)){
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
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return recentDate;
    }

	
	public Review selectReviewForm(int authorID,int articleID){
		ConnectionManager conn;
		 Review reviewart=new Review();
		//get current date time with Date()
		try {
			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery = " Select * from REVIEW where authorReviewerID = "+authorID+" and articleID="+articleID;
			ResultSet result =st.executeQuery(selectQuery);
			
			if (result.next()){
				int criticismID=(Integer) result.getObject("criticismID");
				reviewart.setCriticismID(criticismID);
				String judgement= (String) result.getObject("judgement"); 
				reviewart.setJudgement(judgement);
				String expertise= (String) result.getObject("expertise"); 
				reviewart.setExpertise(expertise);
				String summary= (String) result.getObject("summary");
				reviewart.setSummary(summary);
				String smallErrors= (String) result.getObject("smallErrors");
				reviewart.setSmallErrors(smallErrors);
				String editorComments= (String) result.getObject("editorComments");
				reviewart.setEditorsComments(editorComments);
				Date dateSubmitted=(Date)result.getObject("dateSubmitted");
				reviewart.setDateSubmitted(dateSubmitted);
			}

			String selectQuery1 = " Select criticism from Criticism where criticismID = "+reviewart.getCriticismID();
			ResultSet rs =st.executeQuery(selectQuery1);
			if (rs.next()){
				String criticism =(String) rs.getObject("criticism");
				reviewart.setCriticism(criticism);
			}
			System.out.println("reviewart :  ))))))))))))))"+reviewart.getCriticismID());
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviewart;
		
	}

	public void checkCountReviews(){
		
		
	}
	

}
   
