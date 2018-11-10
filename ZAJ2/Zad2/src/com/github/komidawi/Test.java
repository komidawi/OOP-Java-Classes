package com.github.komidawi;

import java.util.LinkedList;
import java.util.Scanner;

public class Test {

    private static LinkedList<Rectangle> figures = new LinkedList<>();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        boolean ifRun = true;

        while (ifRun) {
            showMenu();
            ifRun = handleUserResponse();
        }
    }

    private static void showMenu() {
        String[] menuOptions = {"Add new rectangle", "Show all rectangles", "Calculate sum of all areas", "Close"};

        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println(i + ". " + menuOptions[i]);
        }
    }

    private static boolean handleUserResponse() {
        Scanner scanner = new Scanner(System.in);
        int userResponse = scanner.nextInt();

        switch (userResponse) {
            case 0:
                addNewRectangle();
                return true;
            case 1:
                showAllRectangles();
                return true;
            case 2:
                showSumOfAllAreas();
                return true;
            case 3:
                prepareAppForClosing(scanner);
                return false;
            default:
                System.out.println("Wrong input. Try again.");
                return true;
        }
    }

    private static void addNewRectangle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set a: ");
        double a = scanner.nextDouble();
        System.out.println("Set b: ");
        double b = scanner.nextDouble();

        if (isSideLengthValid(a) && isSideLengthValid(b)) {
            figures.add(new Rectangle(a, b));
        } else {
            System.out.println("Wrong input data. Please try again.");
        }
    }

    private static void showAllRectangles() {
        for (int i = 0; i < figures.size(); i++) {
            Rectangle current = figures.get(i);
            System.out.printf("Figure #%d: a=%f, b=%f, area=%f\n", i, current.getA(), current.getB(), current.area());
        }
    }

    private static void showSumOfAllAreas() {
        System.out.println("Sum of all areas=" + calculateSumOfAllAreas());
    }

    private static double calculateSumOfAllAreas() {
        double sumOfAllAreas = 0.0;
        for (Rectangle rectangle : figures) {
            sumOfAllAreas += rectangle.area();
        }

        return sumOfAllAreas;
    }

    private static void prepareAppForClosing(Scanner scanner) {
        scanner.close();
    }

    private static boolean isSideLengthValid(double sideLength) {
        return sideLength > 0;
    }
}
