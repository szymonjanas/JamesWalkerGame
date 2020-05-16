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
        this.value = calculateValue(value);
    }

    private static short calculateValue(short value) {
        short temp = -1;
        if (value > 359) temp = (short) (value - 360);
        else if (value < 0) temp = (short) (360 + value);
        else temp = value;
        return temp;
    }

    public short add(short value){
        return calculateValue((short) (this.getValue()+value));
    }

    public short subtract(short value){
        return calculateValue((short) (this.getValue()-value));
    }

    public static short opposite(short value){
        return calculateValue((short) (value + 180));
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
