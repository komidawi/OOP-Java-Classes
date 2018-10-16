package com.github.komidawi2;

import com.github.komidawi.B;

public class C extends B {
    public C(int number, String name) {
        super(number, name);
    }

    void changeName() {
        /* name = "CCC"; */
        /* Error:(11, 9) java: name is not public in com.github.komidawi.A; cannot be accessed from outside package */
    }
}
