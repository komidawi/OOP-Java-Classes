package com.github.komidawi;

import java.util.LinkedList;
import java.util.Scanner;

public class Test {

    private static LinkedList<Rectangle> figures = new LinkedList<>();
    private static boolean ifRun = true;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        while (ifRun) {
            showMenu();
            handleUserResponse();
        }
    }

    private static void showMenu() {
        String[] menuOptions = {"Add new rectangle", "Show all rectangles", "Calculate sum of all areas", "Close"};

        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println(i + ". " + menuOptions[i]);
        }
    }

    private static void handleUserResponse() {
        Scanner scanner = new Scanner(System.in);
        int userResponse = scanner.nextInt();

        switch (userResponse) {
            case 0:
                addNewRectangle();
                break;
            case 1:
                showAllRectangles();
                break;
            case 2:
                showSumOfAllAreas();
                break;
            case 3:
                close(scanner);
                break;
            default:
                System.out.println("Wrong input. Try again.");
        }
    }

    private static void addNewRectangle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set a: ");
        double a = scanner.nextDouble();
        System.out.println("Set b: ");
        double b = scanner.nextDouble();

        figures.add(new Rectangle(a, b));
    }

    private static void showAllRectangles() {
        for (int i = 0; i < figures.size(); i++) {
            Rectangle current = figures.get(i);
            System.out.printf("Figure #%d: a=%.2f, b=%.2f, area=%.2f\n", i, current.getA(), current.getB(), current.area());
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

    private static void close(Scanner scanner) {
        scanner.close();
        ifRun = false;
    }
}
