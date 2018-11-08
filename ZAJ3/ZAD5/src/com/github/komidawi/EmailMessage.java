package com.github.komidawi;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.Properties;


public class EmailMessage {
    private String from;    // required
    private LinkedList<String> to; // required at least one (must be email)
    private String subject;
    private String content;
    private String mimeType;
    private LinkedList<String> cc;
    private LinkedList<String> bcc;

    // example constructor, we can assume optional fields are null
    protected EmailMessage(String from, LinkedList<String> to,
                           String subject, String content, String mimeType,
                           LinkedList<String> cc, LinkedList<String> bcc) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.mimeType = mimeType;
        this.cc = cc;
        this.bcc = bcc;
    }

    public void send() {
        try {
            // 1. Creating javax.mail.Session object
            Properties properties = setupProperties();
            Session session = Session.getDefaultInstance(properties);
            //session.setDebug(true);

            /* 2. Creating javax.mail.internet.MimeMessage object, we have to set different properties
            in this object such as recipient email address, Email Subject, Reply-To email, email body, attachments etc. */
            Message emailMessage = prepareEmailMessage(session);

            //3. Using javax.mail.Transport to send the email message.
            Transport transport = session.getTransport("smtp");
            transport.connect(
                    session.getProperty("mail.smtp.host"),
                    session.getProperty("mail.smtp.user"),
                    session.getProperty("mail.smtp.password"));
            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Properties setupProperties() {
        final String password = "EmailBuilder1";
        final String host = "mail.mail.world";

        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.auth", "true");

        return properties;
    }

    private Message prepareEmailMessage(Session session) throws MessagingException {
        Message emailMessage = new MimeMessage(session);
        emailMessage.setFrom(new InternetAddress(from));
        emailMessage.setSubject(subject);
        emailMessage.setText(content);

        for (String recipient : to) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        }

        for (String ccRecipient : cc) {
            emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ccRecipient));
        }

        for (String bccRecipient : bcc) {
            emailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccRecipient));
        }

        return emailMessage;
    }


    public static class Builder {
        private String from;    // required
        private LinkedList<String> to; // required at least one (must be email)
        private String subject;
        private String content;
        private String mimeType;
        private LinkedList<String> cc;
        private LinkedList<String> bcc;

        // Java Builder class should have a public constructor with all the required attributes as parameters.
        public Builder(String from, LinkedList<String> to) {
            addFrom(from);
            addTo(to);
        }

        private void addFrom(String senderEmail) {
            if (senderEmail != null && isEmailAddressValid(senderEmail)) {
                this.from = senderEmail;
            }
        }

        private void addTo(LinkedList<String> recipients) {
            if (recipients != null && recipients.size() > 0) {
                this.to = recipients;
            }
        }

        private boolean isEmailAddressValid(String email) {
            boolean result = true;
            try {
                InternetAddress emailAddress = new InternetAddress(email);
                emailAddress.validate();
            } catch (AddressException e) {
                result = false;
                e.printStackTrace();
            }
            return result;
        }

        /* Java Builder class should have methods to set the optional parameters and
        it should return the same Builder object after setting the optional attribute. */
        public Builder addSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder addContent(String content) {
            this.content = content;
            return this;
        }

        public Builder addMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder addCc(LinkedList<String> cc) {
            this.cc = cc;
            return this;
        }

        public Builder addBcc(LinkedList<String> bcc) {
            this.bcc = bcc;
            return this;
        }

        /* The final step is to provide a build() method in the builder class that will return the Object needed by
        client program. For this we need to have a private constructor in the Class with Builder class as argument. */
        public EmailMessage build() {
            return new EmailMessage(from, to, subject, content, mimeType, cc, bcc);
        }
    }
}