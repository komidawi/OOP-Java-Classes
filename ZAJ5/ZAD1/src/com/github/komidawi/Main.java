package com.github.komidawi;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length < 4) {
                throw new ParametersException();
            }

            String input = args[0];
            String output = args[1];
            int delay = Integer.parseInt(args[2]);
            int fps = Integer.parseInt(args[3]);

            new SubtitleShifter().delay(input, output, delay, fps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
