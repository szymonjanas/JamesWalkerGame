package com.mrwalker.firstgame.Entity.Models;

public class Frame {

    public final int from;
    public final int to;

    public Frame(int from, int to) {
        this.from = from;
        this.to = to;
    }
    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
