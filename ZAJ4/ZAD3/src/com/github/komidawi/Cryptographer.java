package com.github.komidawi;

import java.io.*;
import java.util.Scanner;

public class Cryptographer {

    private static String inputFilePath;
    private static String outputFilePath;

    public static void main(String[] args) {

        if (args != null && args.length >= 2) {
            inputFilePath = args[0];
            outputFilePath = args[1];
        }


        run();
    }

    private static void run() {
        Algorithm chosenAlgorithm = askForAlgorithm();
        String intention = askForIntention();
        executeAlgorithm(chosenAlgorithm, intention);
    }

    private static Algorithm askForAlgorithm() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which algorithm would you like to use?\n" +
                "1. ROT11\n" +
                "2. Polibiusz");

        int userResponse = scanner.nextInt();

        switch (userResponse) {
            case 1:
                return new ROT11();

            case 2:
                return new Polibiusz();

            default:
                return null;
        }
    }

    private static String askForIntention() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like to do?\n" +
                "1. Crypt a file\n" +
                "2. Decrypt a file");

        int userResponse = scanner.nextInt();

        switch (userResponse) {
            case 1:
                return "crypt";

            case 2:
                return "decrypt";

            default:
                return "error";
        }
    }

    private static void executeAlgorithm(Algorithm chosenAlgorithm, String intention) {
        if (intention.equals("crypt")) {
            cryptfile(new File(inputFilePath), new File(outputFilePath), chosenAlgorithm);
        } else if (intention.equals("decrypt")) {
            decryptfile(new File(inputFilePath), new File(outputFilePath), chosenAlgorithm);
        }
    }

    public static void cryptfile(File source, File target, Algorithm algorithm) {
        String toBeCrypted = readFile(source);
        String crypted = algorithm.crypt(toBeCrypted);
        writeToFile(target, crypted);
    }

    public static void decryptfile(File source, File target, Algorithm algorithm) {
        String crypted = readFile(source);
        String decrypted = algorithm.decrypt(crypted);
        writeToFile(target, decrypted);
    }

    private static String readFile(File file) {
        StringBuilder content = new StringBuilder();
        String line;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File to read not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unknown exception.");
            e.printStackTrace();
        }

        return content.toString();
    }

    private static void writeToFile(File file, String content) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(content);

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Unknown error.");
            e.printStackTrace();
        }
    }
}
