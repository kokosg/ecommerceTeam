import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail4 {

    private String from;
    private String to;
    private String subject;
    private String text;

    public mail4( String from,String to, String subject, String text){

       this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }


    public void send(){

   
         
          Properties props = new Properties();

         props.put("mail.smtp.host", "smtp.gmail.com");
  		 props.put("mail.smtp.socketFactory.port", "465");
  		 props.put("mail.debug", "true");
  	     props.put("mail.smtp.debug", "true");
  		 props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
  		 props.put("mail.smtp.auth", "true");
  		 props.put("mail.smtp.port", "465");
    	  Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
  			protected PasswordAuthentication getPasswordAuthentication() {
  				return new PasswordAuthentication("javaecom@gmail.com",
  						"teammaster10");// change accordingly
  			}
  		});
          Message simpleMessage = new MimeMessage(session);

          InternetAddress fromAddress = null;
          InternetAddress toAddress = null;
          try {

              fromAddress = new InternetAddress(from);
              toAddress = new InternetAddress(to);
          } catch (AddressException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }

          try {

              simpleMessage.setFrom(fromAddress);
              simpleMessage.setRecipient(RecipientType.TO, toAddress);
              simpleMessage.setSubject(subject);
              simpleMessage.setText(text);
                          Transport.send(simpleMessage);

          }   catch(Exception e){
        	  e.printStackTrace();
          }
    }
    }
