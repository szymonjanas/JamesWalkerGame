package com.mrwalker.firstgame.auxiliary;

import com.badlogic.gdx.math.Vector2;

public class Position2 {
    private float x;
    private float y;

    public Position2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position2(Vector2 position){
        this.x = position.x;
        this.y = position.y;
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

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 position){
        set(position.x, position.y);
    }

    public void set(Position2 position){
        set(position.getX(), position.getY());
    }

    public String toString(){
        return "Position x: " + x + ", y: " + y;
    }

    public Vector2 toVector2(){
        return new Vector2(this.x, this.y);
    }
}
