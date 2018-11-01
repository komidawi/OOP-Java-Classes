package com.github.komidawi;

import java.io.File;
import java.util.Scanner;

public class Cryptographer {

    public static void main(String[] args) {
        // check if exists
        // String inputFilePath = args[0];
        // String outputFilePath = args[1];


        /*
        showInterface();

        Scanner in = new Scanner(System.in);

        System.out.println("What would you like to do?\n" +
                "1. Cypher" +
                "2. Decypher");

        // TODO: validate user Response
        int userResponse = in.nextInt();

        switch (userResponse) {
            case 1:
                System.out.println("Which algorithm would you like to use?\n" +
                        "1. ROT11" +
                        "2. Polibiusz");
                break;

            case 2:

                break;
        }
        */

        System.out.println(new ROT11().crypt("abcdefghijklmnopqrstuvwxyz !@#$%^&*() ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        System.out.println(new ROT11().decrypt("lmnopqrstuvwxyzabcdefghijk !@#$%^&*() LMNOPQRSTUVWXYZABCDEFGHIJK"));

    }

    private static void showInterface() {

    }

    public static void cryptfile(File toEncrypt, File encrypted, Algorithm algorithm) {

    }

    public static void decryptfile(File toDecrypt, File decrypted, Algorithm algorithm) {

    }
}
