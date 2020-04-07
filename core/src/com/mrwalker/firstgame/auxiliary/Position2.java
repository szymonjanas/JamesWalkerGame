package com.mrwalker.firstgame.auxiliary;

import com.badlogic.gdx.math.Vector2;

public class Position2 {
    private float x;
    private float y;

    private float rotation;

    public Position2(float x, float y) {
        set(x, y);
    }

    public Position2(float x, float y, float rotation) {
        set(x, y);
        setRotation(rotation);
    }

    public Position2(Vector2 position){
        set(position);
    }

    public Position2(Vector2 position, float rotation){
        set(position);
        setRotation(rotation);
    }

    public Position2(){}

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

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
        setX(x);
        setY(y);
    }

    public void set(float x, float y, float rotation){
        set(x, y);
        setRotation(rotation);
    }

    public void set(Vector2 position){
        set(position.x, position.y);
    }

    public void set(Vector2 position, float rotation){
        set(position);
        setRotation(rotation);
    }

    public void set(Position2 position){
        set(position.getX(), position.getY());
    }

    public String toString(){
        return "( x: " + x + ", y: " + y + " )";
    }

    public Vector2 toVector2(){
        return new Vector2(getX(), getY());
    }
}
