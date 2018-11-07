package com.github.komidawi;

public class WrongFrameOrderException extends SubtitleLineException {

    public WrongFrameOrderException(int lineNumber, String line) {
        super(lineNumber, line);
    }

    @Override
    public String getMessage() {
        return String.format("Invalid frame order in line %d: \"%s\"" +
                "\nStart frame must be lower than end frame.", getLineNumber(), getLine());
    }
}
