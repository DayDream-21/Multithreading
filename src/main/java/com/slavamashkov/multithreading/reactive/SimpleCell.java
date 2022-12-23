package com.slavamashkov.multithreading.reactive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Publisher;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    private int value = 0;
    private String name;
    private List<Subscriber<Integer>> subscribers = new ArrayList<>();

    public SimpleCell(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add((Subscriber<Integer>) subscriber);
    }

    private void notifyAllSubscribers() {
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    @Override
    public void onNext(Integer newValue) {
        this.value = newValue;
        notifyAllSubscribers();
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public String toString() {
        return  name + ":" + value;
    }
}
