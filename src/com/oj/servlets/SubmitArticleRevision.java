package com.oj.servlets;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.ReviewForm;
import com.oj.models.SubmitArticleModel;
import com.oj.objects.Article;
import com.oj.objects.CriticismResponse;
import com.oj.objects.Review;

/**
 * Servlet implementation class SubmitArticleRevision
 */
public class SubmitArticleRevision extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	private String UPLOAD_DIRECTORY = "";
	 public void init(ServletConfig config)throws ServletException{
		 super.init(config);
		 //UPLOAD_DIRECTORY = getServletContext().getRealPath("/") + File.separator +"fileupload"+File.separator;
//		 UPLOAD_DIRECTORY = "/share/student/stucat004/fileupload/";
//		 UPLOAD_DIRECTORY = "//stustore/student/stucat004/context/fileupload/";
		 UPLOAD_DIRECTORY = System.getProperty("catalina.base") + "/temp/";
	 }
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {
		Template template=null;
		context.put("apptitle", "Ecom Journal - Articles' Reviews");
		HttpSession session = request.getSession();
		System.out.println("inside submit article revision");
		if ((session.getAttribute("userID"))!=null) {
			int articleID = 0;
		
		ReviewForm model=new ReviewForm();
		SubmitArticleModel submitModel = new SubmitArticleModel();
		
		String flag = null, filePath = null;
		response.setContentType("text/html");
		Article article = new Article();
		System.out.println("before if");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			System.out.println("inside");
			try {
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				ArrayList<CriticismResponse> allResponses = new ArrayList<CriticismResponse>();
				ArrayList<Integer> allCriticismID = new ArrayList<Integer>();
				ArrayList<String> allResponsesText = new ArrayList<String>();
				
				String name = null;
				for(FileItem item : items) {
					//check if the item posted is a form field 
					if(item.isFormField()) {
						name = item.getFieldName();
						//retrieve the values of each field in the form from the request
						if (name.equals("id")) {
							articleID = Integer.parseInt(item.getString().replaceAll("\\D", "")); 
							System.out.println("artID:" + articleID);
							article.setArticleID(articleID);
						}
						if (name.equals("flag")) {
							flag = item.getString(); 
							System.out.println("flag:" + flag);
						}
						if (name.equals("myResponse")) {
							int criticismID = Integer.parseInt(item.getString().replaceAll("\\D", "")); 
							System.out.println("criticismID:" + criticismID);
							allCriticismID.add(criticismID);
						}
						if (name.equals("myResponses")) {
							String responses = item.getString(); 
							System.out.println("response:" + responses);
							allResponsesText.add(responses);
						}
					//else it is a file
					} else {
						try {
							name = new File(item.getName()).getName();
							System.out.println("file found");
							item.write( new File(UPLOAD_DIRECTORY+ name));
							filePath = UPLOAD_DIRECTORY + name;
							System.out.println("name of file:" + name);
						} catch (Exception e) {
							context.put("message", "File Upload Failed due to " + e);
							System.out.println("File Upload Failed due to " + e);
						}
					}
				}
				
				for (int i = 0; i < allResponsesText.size(); i++) {
					CriticismResponse criRes = new CriticismResponse();
					criRes.setCriticismID(allCriticismID.get(i));
					criRes.setResponseText(allResponsesText.get(i));
					System.out.println(i + " criticism: " + allCriticismID.get(i) + ", response: " + allResponsesText.get(i));
					allResponses.add(criRes);
				}	
				
				System.out.println("before adding responses");
				//add responses to db 
				submitModel.insertIntoResponses(allResponses);
				//add revision to db
				submitModel.insertUpdatedArticleRevision(article, filePath);
				//update article
				submitModel.updateArticleAfterRevision(article);
				
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
		} else {
			System.out.println("Not a multipart content form.");
			articleID = Integer.parseInt(request.getParameter("id"));
			article.setArticleID(articleID);
		}
		context.put("fileUpload",getServletContext().getRealPath("/fileupload/"));
		context.put("articleID", articleID);
		//if we haven't post any data then we have just to load the template
		if (flag == null) {
			System.out.println("No data posted.");
			ArrayList<Review> reviewsArray = new ArrayList<Review>();
			try {
				reviewsArray = model.haveReviews(articleID);
				System.out.println("submitting article servlet: id " + articleID);
				context.put("articleID", articleID);
			    context.put("myReviews", reviewsArray);
			    //context.put("articleRevisions", revisions);
				template = getTemplate("/forms/submitArticleRevision.vm");
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
		//if we have posted data from a form do the following
		} else {
			System.out.println("Data posted.");
			try {
				context.put("successfully", "Article revision and responses submitted succesfully");
				template = getTemplate("/forms/home.vm");
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			
		} 
		}else{
			template = getTemplate("/forms/home.vm");
		}
		return template;
	}
}
