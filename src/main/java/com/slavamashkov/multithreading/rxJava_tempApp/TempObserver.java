package com.slavamashkov.multithreading.rxJava_tempApp;

import com.slavamashkov.multithreading.flowAPI_tempApp.TempInfo;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class TempObserver implements Observer<TempInfo> {
    @Override
    public void onComplete() {
        System.out.println("Done!");
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        System.out.println("Got problem: " + throwable.getMessage());
    }

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {

    }

    @Override
    public void onNext(@NonNull TempInfo tempInfo) {
        System.out.println(tempInfo);
    }
}
