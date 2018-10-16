package com.github.komidawi;

public class A {
    protected int number;
    String name;

    public A(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public void callDecrement() {
        decrement();
    }

    public void callChangeName() {
        changeName();
    }

    public void callIncrement() {
        increment();
    }

    private void increment() {
        number++;
    }

    protected  void decrement() {
        number--;
    }

    void changeName() {
        name = "AAA";
    }
}
