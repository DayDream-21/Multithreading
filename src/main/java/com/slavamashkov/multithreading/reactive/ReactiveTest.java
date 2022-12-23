package com.slavamashkov.multithreading.reactive;

public class ReactiveTest {
    public static void main(String[] args) {
        ArithmeticCell c5 = new ArithmeticCell("C5");
        ArithmeticCell c3 = new ArithmeticCell("C3");
        SimpleCell c4 = new SimpleCell("C4");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3);
        c2.subscribe(c3);

        //c3.subscribe(c5);
        //c4.subscribe(c5);
        //c5.setLeft(c3.getValue());
        //c5.setRight(c4.getValue());

        c1.onNext(10);
        System.out.println(c1);
        System.out.println(c3);
        //System.out.println(c5);
        c2.onNext(20);
        System.out.println(c2);
        System.out.println(c3);
        //System.out.println(c5);
        c1.onNext(15);
        System.out.println(c1);
        System.out.println(c3);
        //System.out.println(c5);
        //c4.onNext(1);
        //System.out.println(c4);
        //System.out.println(c5);
        //c4.onNext(3);
        //System.out.println(c4);
        //System.out.println(c5);
    }
}
