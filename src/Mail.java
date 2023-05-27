import javax.mail.*;
import javax.mail.internet.*;

import java.io.IOException;
import java.util.Properties;

public class Mail {
    // TODO SET YOUR EMAIL
    private String username = "<YOUR MAIL>";
    private String password = "<YOUR PASSWORD>";
    private Properties props;

    public Mail() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void sendMail(String reciever, String subject, String body, String filePath) {

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(reciever));
            message.setSubject(subject);
            message.setText(body);
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            try {
                mimeBodyPart.attachFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
