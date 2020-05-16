package com.mrwalker.firstgame.auxiliary;

public class Rotation {
    private short value;

    public Rotation(short value) {
        this.setValue(value);
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        if (value > 359) this.value = (short) (value - 360);
        else if (value < 0) this.value = (short) (360 + value);
        else this.value = value;
    }

    public boolean equals(Rotation rotation){
        return value == rotation.getValue();
    }

    public boolean equals(short rotation){
        return value == rotation;
    }

    public Rotation cpy(){
        return new Rotation(getValue());
    }
}
