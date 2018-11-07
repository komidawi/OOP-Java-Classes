package com.github.komidawi;

public class ParametersException extends Exception {

    public ParametersException() {
        super("Program requires 4 parameters: " +
        "inputFilePath, outputFilePath, delayInMilliseconds, movieFPS.");
    }
}
