public class mail5 {
public static void main(String[] args) {

        String from = "javaecom@gmail.com";
        String to = "shreebha.arora@gmail.com";
        String subject = "Test";
        String message = "A test message";
        mail4 sendMail = new mail4(from,to,subject,message);
        sendMail.send();
    }
}