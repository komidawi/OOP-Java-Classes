package com.github.komidawi;

public class ROT11 implements Algorithm {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int SHIFT = 11;

    @Override
    public String crypt(String toBeCrypted) {
        return runAlgorithm(toBeCrypted, true);
    }

    @Override
    public String decrypt(String toBeDecrypted) {
        return runAlgorithm(toBeDecrypted, false);
    }

    private String runAlgorithm(String input, boolean crypt) {
        StringBuilder output = new StringBuilder();

        if (input != null) {
            for (int i = 0; i < input.length(); i++) {
                char current = input.charAt(i);

                if (canCharacterBeUsed(current)) {
                    if (crypt) {
                        current = cryptCharacter(current);
                    } else {
                        current = decryptCharacter(current);
                    }
                }
                output.append(current);
            }
        }

        return output.toString();
    }

    private boolean canCharacterBeUsed(char character) {
        int indexOfCharacter = ALPHABET.indexOf(character);
        int minimumValidPosition = 0;
        return indexOfCharacter >= minimumValidPosition;
    }

    private char cryptCharacter(char character) {
        char startingLetter = determineStartingLetter(character);
        char endingLetter = determineEndingLetter(character);

        return getCryptedCharacter(character, startingLetter, endingLetter);
    }

    private char decryptCharacter(char character) {
        char startingLetter = determineStartingLetter(character);
        char endingLetter = determineEndingLetter(character);

        return getDecryptedCharacter(character, startingLetter, endingLetter);
    }

    private char getCryptedCharacter(char currentCharacter, char startingLetter, char endingLetter) {
        char cryptedCharacter;

        int shiftedCharacterIndex = currentCharacter + SHIFT;

        if (shiftedCharacterIndex <= endingLetter) {
            cryptedCharacter = (char) shiftedCharacterIndex;
        } else {
            int numberOfLetters = endingLetter - startingLetter + 1;
            int relativeShift = (currentCharacter - startingLetter) % (numberOfLetters - SHIFT);
            shiftedCharacterIndex = startingLetter + relativeShift;
            cryptedCharacter = (char) shiftedCharacterIndex;
        }

        return cryptedCharacter;
    }

    private char getDecryptedCharacter(char currentCharacter, char startingLetter, char endingLetter) {
        char decryptedCharacter;

        int shiftedCharacterIndex = currentCharacter - SHIFT;

        if (shiftedCharacterIndex >= startingLetter) {
            decryptedCharacter = (char) shiftedCharacterIndex;
        } else {
            int numberOfLetters = endingLetter - startingLetter + 1;
            int relativeShift = (endingLetter - currentCharacter) % (numberOfLetters - SHIFT);
            shiftedCharacterIndex = endingLetter - relativeShift;
            decryptedCharacter = (char) shiftedCharacterIndex;
        }

        return decryptedCharacter;
    }

    private char determineStartingLetter(char character) {
        if (Character.isLowerCase(character)) {
            return 'a';
        } else {
            return 'A';
        }
    }

    private char determineEndingLetter(char character) {
        if (Character.isLowerCase(character)) {
            return 'z';
        } else {
            return 'Z';
        }
    }
}
