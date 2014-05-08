package objects;

import java.util.ArrayList;
public class Utility {
 static ArrayList<MailReminder> mailReminderList =  new ArrayList<MailReminder>();
 
 public static ArrayList<MailReminder> getMailReminderList(){
	 return mailReminderList;
 }
 
}
