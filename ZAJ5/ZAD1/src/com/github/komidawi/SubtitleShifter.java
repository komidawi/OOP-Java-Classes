package com.github.komidawi;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SubtitleShifter {

    private ArrayList<SubtitleLine> subtitleLines = new ArrayList<>();

    public void delay(String inputFilePath, String outputFilePath, int delay, int fps) throws SubtitleLineException, IOException{
        readFile(inputFilePath);
        shiftSubtitleLines(delay, fps);
        writeFile(outputFilePath);
    }

    private void readFile(String filePath) throws SubtitleLineException, IOException {
        String line;

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {
            subtitleLines.add(parseLine(line));
        }

        bufferedReader.close();
    }

    private SubtitleLine parseLine(String line) throws SubtitleLineException {
        Pattern pattern = Pattern.compile("\\{(\\d+)}\\{(\\d+)}(.+)");
        Matcher matcher = pattern.matcher(line);

        int startFrame;
        int endFrame;

        if (matcher.find()) {
            startFrame = Integer.parseInt(matcher.group(1));
            endFrame = Integer.parseInt(matcher.group(2));
        } else {
            throw new InvalidLineFormatException(subtitleLines.size()+1, line);
        }

        if (endFrame < startFrame) {
            throw new WrongFrameOrderException(subtitleLines.size()+1, line);
        }

        String text = matcher.group(3);

        return new SubtitleLine(startFrame, endFrame, text);
    }

    private void shiftSubtitleLines(int delay, int fps) {
        int shift = delay * fps / 1000;

        for (SubtitleLine subtitleLine : subtitleLines) {
            subtitleLine.setStartFrame(subtitleLine.getStartFrame() + shift);
            subtitleLine.setEndFrame(subtitleLine.getEndFrame() + shift);
        }
    }

    private void writeFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (SubtitleLine subtitleLine : subtitleLines) {
            bufferedWriter.write(subtitleLine.toString());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
