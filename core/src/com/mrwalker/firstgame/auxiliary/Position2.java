package com.mrwalker.firstgame.auxiliary;

public class Position2 {
    private float x;
    private float y;

    public Position2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position2(){}

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String toString(){
        return "Position x: " + x + ", y: " + y;
    }
}
