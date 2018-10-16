package com.github.komidawi;

import com.github.komidawi2.C;

public class Main {

    public static void main(String[] args) {
        testClasses();
    }

    private static void testClasses() {
        testClassA();
        testClassB();
        testClassC();
    }

    private static void testClassA() {
        A objectA = new A(100, "ObjectA");

        objectA.callChangeName();
        System.out.println(objectA.name);

        objectA.callIncrement();
        System.out.println(objectA.number);

        objectA.callDecrement();
        System.out.println(objectA.number);

        System.out.println();
    }

    private static void testClassB() {
        B objectB = new B(200, "ObjectB");

        objectB.callChangeName();
        System.out.println(objectB.name);

        objectB.callIncrement();
        System.out.println(objectB.number);

        objectB.callDecrement();
        System.out.println(objectB.number);

        System.out.println();
    }

    private static void testClassC() {
        C objectC = new C(300, "ObjectC");

        objectC.callChangeName();
        /* Error:(47, 35) java: name is not public in com.github.komidawi.A; cannot be accessed from outside package */
        //System.out.println(objectC.name);

        objectC.callIncrement();
        System.out.println(objectC.number);

        objectC.callDecrement();
        System.out.println(objectC.number);

        System.out.println();
    }
}
