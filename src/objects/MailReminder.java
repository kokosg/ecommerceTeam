package objects;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import models.ContactModel;

public class MailReminder {
	Timer timer;
	final int INTERVAL = 604800000;
	
	//String mFrom;
	ArrayList<String> mTo;
	String mSubject;
	String mMsg;
	

	public MailReminder(ArrayList<String> to){
		mTo = to;
	    mSubject = "Gentle Reminder to Review The Articles";
	    mMsg = "Dear Reviewer Kindly Review The unpublished Articles";
	    timer = new Timer();
	    timer.schedule(new MailTimer()  , INTERVAL);
	}
	
	class MailTimer extends TimerTask {

		public void functionToRepeat() throws ClassNotFoundException, SQLException {
			ContactModel model=new ContactModel();
			model.sendEmail(mTo,mSubject,mMsg);
		}

		public void run() {
			try {
				functionToRepeat();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
