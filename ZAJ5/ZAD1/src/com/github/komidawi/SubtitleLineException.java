package com.github.komidawi;

public class SubtitleLineException extends Exception {

    private int lineNumber;
    private String line;

    public SubtitleLineException(int lineNumber, String line) {
        super();
        this.lineNumber = lineNumber;
        this.line = line;
    }

    @Override
    public String getMessage() {
        return String.format("An error occurred in line %d: \"%s\"", getLineNumber(), getLine());
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLine() {
        return line;
    }
}
