package com.github.komidawi;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        String from = "zaj3zad5@mail-online.cz";

        LinkedList<String> recipients = new LinkedList<>();
        recipients.add("bca20876@nbzmr.com");

        LinkedList<String> cc = new LinkedList<>();
        cc.add("qb99r@wimsg.com");

        LinkedList<String> bcc = new LinkedList<>();
        bcc.add("qcwo5fza.qrj@20minutemail.it");


        EmailMessage emailMessage =
                new EmailMessage.Builder(from, recipients)
                        .addSubject("JavaMail Message")
                        .addContent("This message has been sent using JavaMail.")
                        .addMimeType("text/plain")
                        .addCc(cc)
                        .addBcc(bcc)
                        .build();

        emailMessage.send();
    }
}
