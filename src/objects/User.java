package objects;

public class User {
	private int authorID;
    private String name;
    private String surname;
    private String affiliations;
    private String email;
    private String password;
    private Boolean isEditor;
 
    public User() {
    }
 
    public User(int authorID, String name, String surname, String affiliations, String email, String password, Boolean isEditor) {
        this.authorID = authorID;
    	this.name = name;
        this.surname = surname;
        this.affiliations = affiliations;
        this.email = email;
        this.password = password;
        this.isEditor = isEditor;        		
    }
 
    //getter methods
    public int getAuthorID() {
    	return this.authorID;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getSurname() {
    	return this.surname;
    }
    
    public String getAffiliations() {
    	return this.affiliations;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public Boolean getIsEditor() {
    	return this.isEditor;
    }
      
    
    //setter methods
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setSurname(String surname) {
    	this.surname = surname;
    }
    
    public void setAffiliations(String affiliations) {
    	this.affiliations = affiliations;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public void setIsEditor(Boolean isEditor) {
    	this.isEditor = isEditor;
    }
}
