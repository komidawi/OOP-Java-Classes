package com.github.komidawi;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        String from = "zaj3zad5@mail-online.cz";

        LinkedList<String> recipients = new LinkedList<>();
        recipients.add("i90669@nwytg.net");

        LinkedList<String> cc = new LinkedList<>();
        cc.add("i91949@nwytg.net");

        LinkedList<String> bcc = new LinkedList<>();
        bcc.add("i91974@nwytg.net");


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
