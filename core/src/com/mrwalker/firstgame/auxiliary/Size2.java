package com.mrwalker.firstgame.auxiliary;

public class Size2 {
        private float width;
        private float height;

    public Size2(float width, float height) {
        setWidth(width);
        setHeight(height);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String toString(){
        return "( width: " + width + ", height: " + height + " )";
    }
}
