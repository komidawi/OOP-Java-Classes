package com.github.komidawi;

public class InvalidLineFormatException extends SubtitleLineException {

    public InvalidLineFormatException(int lineNumber, String line) {
        super(lineNumber, line);
    }

    @Override
    public String getMessage() {
        return String.format("Invalid line format in line %d: \"%s\"" +
                "\nValid format is: {startFrame}{endFrame}subtitle_line", getLineNumber(), getLine());
    }
}
