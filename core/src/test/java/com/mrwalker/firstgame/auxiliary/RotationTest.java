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

    @Test
    public void opposite(){
        assertEquals(Rotation.opposite((short) 135), (short) 315);
        assertEquals(Rotation.opposite((short) 180), (short) 0);
        assertEquals(Rotation.opposite((short) 240), (short) 60);
        assertEquals(Rotation.opposite((short) 30), (short) 210);
    }

    @Test
    public void equals(){
        Rotation rotation = new Rotation((short) 135);
        assertTrue(rotation.equals((short) 135));
        Rotation rotation2 = new Rotation((short) 135);
        assertTrue(rotation2.equals(rotation));
        Rotation rotation3 = new Rotation((short) 145);
        assertFalse(rotation3.equals(rotation2));
        assertFalse(rotation3.equals((short) 155));
    }

}
