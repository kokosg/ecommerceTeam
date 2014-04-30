package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UploadModel {

	public UploadModel(){

	}

	public String getFileName(String title){
		ConnectionManager conn;
		String filePath= null;
		try {
			conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String selectQuery = "Select * FROM Template where title='"+title+"'";
			ResultSet result=st.executeQuery(selectQuery);
			if(result.next()){
				filePath=(String) result.getObject("filePath");
				System.out.println("filePath in get :"+filePath);
			}
			conn.close();
			
			return filePath;
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("Inside upc get method: " + filePath);
		return filePath;
	}

	public void setFilePath(String filePath,String title){
		ConnectionManager conn;
		try {
			conn = new ConnectionManager();
			String insertQuery;
			Statement st = conn.getInstance().getConnection().createStatement();
			String titleExists= getFileName(title);
			if(titleExists==null){
				System.out.println("INSERT");
				insertQuery = "INSERT INTO Template (filePath,title) VALUES ('" + filePath + "','"+title+"')";
			}
			else{
				System.out.println("UPDATE");
				insertQuery = "UPDATE Template SET filePath ='"+filePath+"' WHERE title='"+title+"'";
			}
			st.executeUpdate(insertQuery);
			st.close();
			conn.close();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
