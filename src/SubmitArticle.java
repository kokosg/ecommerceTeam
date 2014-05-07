import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SubmitArticleModel;
import objects.Article;
import objects.Keyword;
import objects.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/** Velocity View Servlet implementation class SubmitArticle 
 * @author Team:Master10
 */
public class SubmitArticle extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "F:/"; //change that to /tmp???
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		Boolean registered = false;
		context.put("apptitle", "E-com Journal - Submit an Article");
		String flag = null, filePath = null;
		Template template = null;
		response.setContentType("text/html");
		
		System.out.println("before if");
		if(ServletFileUpload.isMultipartContent(request)){
			System.out.println("inside");
			try { 
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				ArrayList<Keyword> articleKeywords = new ArrayList<Keyword>();
				ArrayList<User> articleAuthors = new ArrayList<User>();
				ArrayList<String> authorNames = new ArrayList<String>();
				ArrayList<String> authorSurnames = new ArrayList<String>();
				ArrayList<String> authorEmails = new ArrayList<String>();
				ArrayList<String> authorAffiliations = new ArrayList<String>();
				
				SubmitArticleModel model = new SubmitArticleModel(); 
				Article article = new Article();
				
				String name = null;
				for(FileItem item : items) {
					//check if the item posted is a form field
					if(item.isFormField()) {
						name = item.getFieldName();
						//retrieve the values of each field in the form from the request
						if (name.equals("flag")) {
							flag = item.getString(); 
							System.out.println("flag:" + flag);
						}
						if (name.equals("authorNames")) {
							String authorName = item.getString(); 
							System.out.println("name:" + authorName);
							authorNames.add(authorName);
						}
						if (name.equals("authorSurnames")) {
							String authorSurname = item.getString(); 
							System.out.println("surname:" + authorSurname);
							authorSurnames.add(authorSurname);
						}
						if (name.equals("authorEmails")) {
							String authorEmail = item.getString(); 
							System.out.println("email:" + authorEmail);
							authorEmails.add(authorEmail);
						}
						if (name.equals("authorAffiliations")) {
							String authorAffiliation = item.getString(); 
							System.out.println("affiliation:" + authorAffiliation);
							authorAffiliations.add(authorAffiliation);
						}
						if (name.equals("pageNum")) {
							int pageNum = Integer.parseInt(item.getString()); 
							System.out.println("number of pages:" + pageNum);
							article.setPageNo(pageNum);
						}
						if (name.equals("keywords")) {
							String keywords = item.getString(); 
							System.out.println("keyword:" + keywords);
							Keyword keyword = new Keyword();
							keyword.setText(keywords);
							articleKeywords.add(keyword);
						}
						if (name.equals("articleTitle")) {
							String articleTitle = item.getString(); 
							System.out.println("title:" + articleTitle);
							article.setTitle(articleTitle);
						}
						if (name.equals("articleAbstract")) {
							String articleAbstract = item.getString(); 
							System.out.println("abstract:" + articleAbstract);
							article.setSummary(articleAbstract);
						}
					//else it is a file
					} else {
						try {
							name = new File(item.getName()).getName();
							System.out.println("file found");
							item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
							filePath = UPLOAD_DIRECTORY + name;
							System.out.println("name of file:" + name);
						} catch (Exception e) {
							context.put("message", "File Upload Failed due to " + e);
							System.out.println("File Upload Failed due to " + e);
						}
					}
				}
				
				for (int i = 0; i < authorNames.size(); i++) {
					User author = new User();
					author.setName(authorNames.get(i));
					author.setSurname(authorSurnames.get(i));
					author.setEmail(authorEmails.get(i));
					author.setAffiliations(authorAffiliations.get(i));
					if (i == 0) {	
						author.setIsMain(true);
					} else {
						author.setIsMain(false);
					}
					System.out.println(i + " name: " + author.getName() + ", surname: " + author.getSurname() + ", email: " + author.getEmail() + ", aff: " + author.getAffiliations() + ", main: " + author.getIsMain());
					articleAuthors.add(author);
				}
				
								
				model.insertArticle(article);	
				model.insertKeyword(articleKeywords);
				model.updateArticleKeyword(article, articleKeywords);
				article = model.getArticleWithID(article);
				model.insertArticleRevision(article, filePath);
				//add authors to db
				registered = model.insertAuthors(article, articleAuthors);
				
			} catch (Exception e) {
				context.put("message", "Form exception " + e);
				System.out.println("Form exception " + e);
			}
		} else{
			System.out.println("Not a multipart content form.");
		}
			

		//if we haven't post any data then we have just to load the template
		if (flag == null) {
			System.out.println("No data posted.");
			try {
				template = getTemplate("/forms/submitArticle.vm");
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		//if we have posted data from a form do the following
		} else {
			System.out.println("Data posted.");
			try {
				if (registered) {
					context.put("successfully", "Submit article and registration completed! Check your email for login details.");
					template = getTemplate("/forms/home.vm");
				} else {
					template = getTemplate("/forms/submitArticle.vm");
				}
				
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
}