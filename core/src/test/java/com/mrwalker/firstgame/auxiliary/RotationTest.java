package com.mrwalker.firstgame.auxiliary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotationTest {

    @Test
    public void initMore(){
        Rotation rotation = new Rotation((short)370);
        assertEquals(rotation.getValue(), (short) 10);
    }

    @Test
    public void initLess(){
        Rotation rotation = new Rotation((short) - 10);
        assertEquals(rotation.getValue(), (short) 350);
    }

    @Test
    public void addMore(){
        Rotation rotation = new Rotation((short) 180);
        assertEquals(rotation.getValue(), (short) 180);
        assertEquals(rotation.add((short) 190), (short) 10);
    }

    @Test
    public void addLess(){
        Rotation rotation = new Rotation((short) 180);
        assertEquals(rotation.getValue(), (short) 180);
        assertEquals(rotation.add((short) -190), (short) 350);
    }

    @Test
    public void subtractMore(){
        Rotation rotation = new Rotation((short) 180);
        assertEquals(rotation.getValue(), (short) 180);
        assertEquals(rotation.subtract((short) 190), (short) 350);
    }

    @Test
    public void subtractLess(){
        Rotation rotation = new Rotation((short) 180);
        assertEquals(rotation.getValue(), (short) 180);
        assertEquals(rotation.subtract((short) -190), (short) 10);
    }

}
