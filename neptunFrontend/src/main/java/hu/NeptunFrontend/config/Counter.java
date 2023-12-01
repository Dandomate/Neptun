package hu.NeptunFrontend.config;

public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

    public int get() {
        return count;
    }

    public int incrementAndGet(){
        return count++;
    }

    public void clear(){
        this.count = 0;
    }
}

