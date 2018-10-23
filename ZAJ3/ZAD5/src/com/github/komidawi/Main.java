package com.github.komidawi;

import java.util.LinkedList;

public class Main {

    //from, to, subject, content, mimetype, cc, bcc
    public static void main(String[] args) {
        String from = "email@mail.com";

        LinkedList<String> recipients = new LinkedList<>();
        recipients.add("someone@mail.com");

        LinkedList<String> cc = new LinkedList<>();
        cc.add("ccone@mail.com");
        cc.add("cctwo@mail.com");

        LinkedList<String> bcc = new LinkedList<>();
        bcc.add("bccone@mail.com");
        bcc.add("bcctwo@mail.com");


        EmailMessage emailMessage =
                new EmailMessage.Builder(from, recipients)
                        .addSubject("Subject")
                        .addContent("Hello, sir!")
                        .addMimeType("text/plain")
                        .addCc(cc)
                        .addBcc(bcc)
                        .build();

        emailMessage.TEST_IT();
    }
}
