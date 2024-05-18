package com.hospital.module.Services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    private final String username;
    private final String password;
    private final Properties properties;

    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;

        // SMTP server properties for Gmail
        this.properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP server
        properties.put("mail.smtp.port", "587"); // Gmail SMTP port
    }


    public void sendEmail(String recipient, String subject, String body) throws MessagingException {
        // Create a session with authentication
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        // Create a MimeMessage object
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setText(body);

        // Send the message
        Transport.send(message);
        System.out.println("Email sent successfully to " + recipient);
    }
}
