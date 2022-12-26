package com.slavamashkov.multithreading.flowAPI_tempApp;

import java.util.concurrent.Flow;

public class Main {
    public static void main(String[] args) {

        getTemperatures("New York").subscribe(new TempSubscriber());
        getCelsiusTemperatures("Moscow").subscribe(new TempSubscriber());
    }

    public static Flow.Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }

    public static Flow.Publisher<TempInfo> getCelsiusTemperatures(String town) {
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubscription(processor, town));
        };
    }
}
