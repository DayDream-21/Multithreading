package com.slavamashkov.multithreading.rxJava_tempApp;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
        //onePerSec.blockingSubscribe(i -> System.out.println(TempInfo.fetch("New york")));
        //getTemperatures("New York").blockingSubscribe(System.out::println);
        //getCelsiusTemperature("Moscow").blockingSubscribe(System.out::println);
        //getNegativeTemperatures("London").blockingSubscribe(System.out::println);

        getCelsiusTemperature("New York", "Chicago", "San Francisco").blockingSubscribe(System.out::println);
    }

    public static Observable<TempInfo> getTemperatures(String town) {
        return Observable.create(emitter ->
                Observable.interval(1, TimeUnit.SECONDS).subscribe(i -> {
                    if (!emitter.isDisposed()) {
                        if (i >= 5) {
                            emitter.onComplete();
                        } else {
                            try {
                                emitter.onNext(TempInfo.fetch(town));
                            } catch (Exception e) {
                                emitter.onError(e);
                            }
                        }
                    }
                }));
    }

    public static Observable<TempInfo> getCelsiusTemperature(String town) {
        return getTemperatures(town)
                .map(tempInfo -> new TempInfo(
                                tempInfo.getTown(),
                                (tempInfo.getTemp() - 32) * 5 / 9
                        )
                );
    }

    public static Observable<TempInfo> getCelsiusTemperature(String... towns) {
        return Observable.merge(Arrays.stream(towns)
                .map(Main::getCelsiusTemperature)
                .collect(Collectors.toList()));
    }

    public static Observable<TempInfo> getNegativeTemperatures(String town) {
        return getCelsiusTemperature(town)
                .filter(tempInfo -> tempInfo.getTemp() < 0);
    }
}
