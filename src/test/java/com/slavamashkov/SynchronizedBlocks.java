package com.slavamashkov;

public class SynchronizedBlocks {
    private int count;
    public static int staticCount;

    public void performSynchronisedTask() {
        synchronized (this) {
            setCount(getCount()+1);
        }
    }

    public static void performStaticSyncTask(){
        synchronized (SynchronizedBlocks.class) {
            setStaticCount(getStaticCount() + 1);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static int getStaticCount() {
        return staticCount;
    }

    public static void setStaticCount(int staticCount) {
        SynchronizedBlocks.staticCount = staticCount;
    }
}
