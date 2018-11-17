package com.github.komidawi;

public class Polibiusz implements Algorithm {
    private static final char[][] checkerboard = new char[][]{
            {'A', 'B', 'C', 'D', 'E'},
            {'F', 'G', 'H', 'I', 'K'},
            {'L', 'M', 'N', 'O', 'P'},
            {'Q', 'R', 'S', 'T', 'U'},
            {'V', 'W', 'X', 'Y', 'Z'}};

    @Override
    public String crypt(String toBeCrypted) {
        StringBuilder crypted = new StringBuilder();

        toBeCrypted = prepareStringForEncryption(toBeCrypted);

        for (int i = 0; i < toBeCrypted.length(); i++) {
            char current = toBeCrypted.charAt(i);

            crypted.append(cryptLetter(current));
        }

        return crypted.toString();
    }

    private String prepareStringForEncryption(String toBeCrypted) {
        toBeCrypted = toBeCrypted.toUpperCase();
        toBeCrypted = toBeCrypted.replace('J', 'I');
        toBeCrypted = toBeCrypted.replaceAll("\\s", "");
        return toBeCrypted;
    }

    private String cryptLetter(char letter) {
        for (int row = 0; row < checkerboard.length; row++) {
            for (int col = 0; col < checkerboard[row].length; col++) {
                if (letter == checkerboard[row][col]) {
                    return String.format("%d%d", row + 1, col + 1);
                }
            }
        }

        return Character.toString(letter);
    }

    @Override
    public String decrypt(String toBeDecrypted) {
        String[] crypted = toBeDecrypted.split("(?<=\\G..)");

        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < crypted.length; i++) {
            char firstElement = crypted[i].charAt(0);
            char secondElement = crypted[i].charAt(1);
            int row = Character.getNumericValue(firstElement) - 1;
            int col = Character.getNumericValue(secondElement) - 1;

            System.out.printf("row=%d col=%d\n", row, col);

            if (row >= 0 && col >= 0) {
                decrypted.append(checkerboard[row][col]);
            } else {
                decrypted.append(firstElement).append(secondElement);
            }
        }

        return decrypted.toString();
    }
}
