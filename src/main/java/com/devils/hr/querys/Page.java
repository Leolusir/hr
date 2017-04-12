package com.devils.hr.querys;

/**
 * Created by AndyL on 2017/4/6.
 */
public class Page {

    private long   cursor;

    private int    skip     = 0;

    private int    count;

    public Page(long cursor, int skip, int count) {
        this.cursor = cursor;
        this.skip = skip;
        this.count = count;
    }

    public long getCursor() {
        return cursor;
    }

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Page{" +
                "cursor=" + cursor +
                ", skip=" + skip +
                ", count=" + count +
                '}';
    }
}
