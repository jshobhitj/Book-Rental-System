package com.impetus.utility;

import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaEmail.
 */
public class JavaEmail {

    /** The email properties. */
    private Properties emailProperties;
    
    /** The mail session. */
    private Session mailSession;
    
    /** The email message. */
    private MimeMessage emailMessage;

    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(JavaEmail.class);

    /**
     * Main.
     *
     * @param userId the user id
     * @param requestType the request type
     * @param firstName the first name
     * @param title the title
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    public static void main(String userId, String requestType,
            String firstName, String title) throws AddressException,
            MessagingException {
        LOGGER.info("sending mail to user id: " + userId + "for request type: "
                + requestType + " for book: " + title);
        JavaEmail javaEmail = new JavaEmail();

        javaEmail.setMailServerProperties();
        javaEmail.createEmailMessage(userId, requestType, firstName, title);
        javaEmail.sendEmail();
    }

    /**
     * Main1.
     *
     * @param userId the user id
     * @param requestType the request type
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    public static void main1(List<String> userId, String requestType)
            throws AddressException, MessagingException {
        LOGGER.info("sending mail for request type: " + requestType);
        JavaEmail javaEmail = new JavaEmail();

        javaEmail.setMailServerProperties();
        javaEmail.createEmailMessage1(userId, requestType);
        javaEmail.sendEmail();
    }

    /**
     * Sets the mail server properties.
     */
    public void setMailServerProperties() {

        String emailPort = "587";// gmail's smtp port

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

    }

    /**
     * Creates the email message.
     *
     * @param userId the user id
     * @param requestType the request type
     * @param firstName the first name
     * @param title the title
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    public void createEmailMessage(String userId, String requestType,
            String firstName, String title) throws AddressException,
            MessagingException {
        String[] toEmails = { userId };
        String emailSubject = "Library: " + requestType + " Request";
        String emailBody = "";
        if (requestType.equals("Cancellation")) {
            emailBody = "Dear " + firstName
                    + ",\n\nYour cancellation/re-issue request for " + title
                    + " is approved.\n\nThanks";
        } else {
            emailBody = "Dear " + firstName
                    + ",\n\nYour order/return request for " + title
                    + " is pending.\n\nThanks";
        }

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmails[i]));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody);// for a html email
        // emailMessage.setText(emailBody);// for a text email

    }

    /**
     * Creates the email message1.
     *
     * @param userId the user id
     * @param requestType the request type
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    public void createEmailMessage1(List<String> userId, String requestType)
            throws AddressException, MessagingException {
        List<String> toEmails = userId;
        ListIterator<String> itr1 = toEmails.listIterator();
        String emailSubject = "Library: Subscription plan will end in"
                + requestType;
        String emailBody = "Dear Suscriber"
                + ",\n\nYour subscription plan will end in " + requestType
                + " Please choose a new subscription plan.\n\nThanks";

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (int i = 0; i < toEmails.size(); i++) {
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(itr1.next()));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody);// for a html email
        // emailMessage.setText(emailBody);// for a text email

    }

    /**
     * Send email.
     *
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    public void sendEmail() throws AddressException, MessagingException {

        String emailHost = "smtp.gmail.com";
        String fromUser = "bookstoreimpetus";// just the id alone without @gmail.com
        String fromUserEmailPassword = "impetus123";

        Transport transport = mailSession.getTransport("smtp");

        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        LOGGER.info("Email sent successfully.");
    }

}