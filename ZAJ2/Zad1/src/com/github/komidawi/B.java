package com.github.komidawi;

public class B extends A {

    public B(int number, String name) {
        super(number, name);
    }

    protected void decrement() {
        number -= 10;
    }

    void changeName() {
        name = "BBB";

    }
    private void increment() {
        number += 10;
    }
}
