/**
 * 
 */
package objects;

/**
 * @author Master Team 10
 *
 */
public class Response {

	private int responseID;
	private int criticismID;
	private String responseText;
	
	/**
	 * 
	 */
	public Response() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param responseID
	 * @param criticismID
	 * @param responseText
	 */
	public Response(int responseID, int criticismID, String responseText) {
		this.responseID = responseID;
		this.criticismID = criticismID;
		this.responseText = responseText;
	}

	/**
	 * @return the responseID
	 */
	public int getResponseID() {
		return responseID;
	}

	/**
	 * @param responseID the responseID to set
	 */
	public void setResponseID(int responseID) {
		this.responseID = responseID;
	}

	/**
	 * @return the criticismID
	 */
	public int getCriticismID() {
		return criticismID;
	}

	/**
	 * @param criticismID the criticismID to set
	 */
	public void setCriticismID(int criticismID) {
		this.criticismID = criticismID;
	}

	/**
	 * @return the responseText
	 */
	public String getResponseText() {
		return responseText;
	}

	/**
	 * @param responseText the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

}
