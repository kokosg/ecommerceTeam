package objects;
/** User(author) object
 * @author Team:Master10
 */
public class User {

	private int authorID;
    private String name;
    private String surname;
    private String affiliations;
    private String email;
    private String password;
    private Boolean isEditor;
 
	/**
	 * Empty constructor
	 */
    public User() {
    }
 
	/**Creates a user(author) object
	 * @param authorID
	 * @param name of the author
	 * @param surname surname of the author
	 * @param affiliations of the author 
	 * @param email of the author
	 * @param password of the author 
	 * @param isEditor if the author is an editor 
	 */
    public User(int authorID, String name, String surname, String affiliations, String email, String password, Boolean isEditor) {
        this.authorID = authorID;
    	this.name = name;
        this.surname = surname;
        this.affiliations = affiliations;
        this.email = email;
        this.password = password;
        this.isEditor = isEditor;        		
    }
    
	/**Creates a user(author) object
	 * @param name of the author
	 * @param surname surname of the author
	 * @param email of the author
	 */
    public User(String name, String surname, String email) {
    	this.name = name;
        this.surname = surname;
        this.email = email;       		
    }
 
	/**
	 * @return the authorID
	 */
	public int getAuthorID() {
		return authorID;
	}

	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the affiliations
	 */
	public String getAffiliations() {
		return affiliations;
	}

	/**
	 * @param affiliations the affiliations to set
	 */
	public void setAffiliations(String affiliations) {
		this.affiliations = affiliations;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isEditor
	 */
	public Boolean getIsEditor() {
		return isEditor;
	}

	/**
	 * @param isEditor the isEditor to set
	 */
	public void setIsEditor(Boolean isEditor) {
		this.isEditor = isEditor;
	}
}
