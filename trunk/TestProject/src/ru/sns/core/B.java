package ru.sns.core;

public class B extends A {
    public static void main(String[] args) {
        A a = new B();
        a.method();
        B b = (B) a;
        b.method();
    }
    public static void method(){
        System.out.println("B");
    }
}
