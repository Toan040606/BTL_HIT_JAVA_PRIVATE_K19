package root.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import root.constant.OtpMessage;

import java.util.Properties;

public class EmailUtil {
    private static final String fromEmail = "giangka1005@gmail.com";
    private static final String password = "uxsqtziibuycdvej";

    public static void sendOtp(String toEmail,String otp){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmail));
            message.setSubject(OtpMessage.SUBJECT_OTP);
            message.setText(String.format(OtpMessage.TEXT_OTP,otp));

            Transport.send(message);
        }catch (MessagingException e){
            throw new RuntimeException(OtpMessage.SEND_OTP_ERROR,e);
        }
    }
}
