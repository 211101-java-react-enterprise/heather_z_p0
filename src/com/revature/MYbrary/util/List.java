package com.revature.MYbrary.util;

public interface List<T> extends Collection<T> {
    T get(int index);

    default void defaultMethodExample() {
        System.out.println("This is a default method in an interface, it CAN be overridden by implementing classes");
    }

    static void staticMethodExample() {
        System.out.println("This is a static method in an interface, it CANNOT be overridden - but it can be shadowed.");
    }
}
