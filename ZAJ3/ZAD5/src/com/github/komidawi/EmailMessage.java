package com.github.komidawi;

import java.util.LinkedList;

public class EmailMessage {
    private String from;    // required
    private LinkedList<String> to; // required at least one (must be email)
    private String subject;
    private String content;
    private String mimetype;
    private LinkedList<String> cc;
    private LinkedList<String> bcc;

    // example constructor, we can assume optional fields are null
    public EmailMessage(String from, LinkedList<String> to,
                        String subject, String content, String mimetype,
                        LinkedList<String> cc, LinkedList<String> bcc) {

        // TODO: VALIDATION

        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.mimetype = mimetype;
        this.cc = cc;
        this.bcc = bcc;
    }
}

