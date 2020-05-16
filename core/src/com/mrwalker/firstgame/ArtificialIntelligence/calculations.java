package com.mrwalker.firstgame.ArtificialIntelligence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.auxiliary.Position2;

public final class calculations {
    private static final String TAG = calculations.class.getSimpleName();

    private static final float multiplayer = (float) (Math.sqrt(2)/2);

    public static short getOrientation(Position2 from, Position2 to){
        short orientation;
        double TWOPI = 6.2831853071795865;
        double RAD2DEG = 57.2957795130823209;
        // if (a1 = b1 and a2 = b2) throw an error
        double theta = Math.atan2(to.getX() - from.getX(), from.getY() - to.getY());
        if (theta < 0.0)
            theta += TWOPI;
        double exact_rotation = RAD2DEG * theta;
        if (exact_rotation - 90 < 0) exact_rotation = 270 + exact_rotation;
        else exact_rotation -= 90;

        return  generalizeRotation((short) exact_rotation);
    }

    public static short generalizeRotation(short exact_rotation){
        short rotation = -1;
        if (exact_rotation > 22  && exact_rotation <= 67)  rotation = 45;
        else if (exact_rotation > 67  && exact_rotation <= 112) rotation = 90;
        else if (exact_rotation > 112 && exact_rotation <= 157) rotation = 135;
        else if (exact_rotation > 157 && exact_rotation <= 202) rotation = 180;
        else if (exact_rotation > 202 && exact_rotation <= 247) rotation = 225;
        else if (exact_rotation > 247 && exact_rotation <= 292) rotation = 270;
        else if (exact_rotation > 292 && exact_rotation <= 338) rotation = 315;
        else if ((exact_rotation > 338 && exact_rotation <= 360) ||
                (exact_rotation <= 22 && exact_rotation >= 0))   rotation = 0;

        if (rotation < 0) Gdx.app.error(TAG, "Wrong rotation calculated!");

        return rotation;
    }

    public static Vector2 getForce(short rotation){
        Vector2 force = new Vector2(0f, 0f);
        switch (rotation){
            case 0:   force = new Vector2(1f, 0f); break;
            case 45:  force = new Vector2(1f * multiplayer, 1f*multiplayer); break;
            case 90:  force = new Vector2(0f, 1f*multiplayer); break;
            case 135: force = new Vector2(-1f * multiplayer, 1f*multiplayer); break;
            case 180: force = new Vector2(-1f, 0f); break;
            case 225: force = new Vector2(-1f * multiplayer, -1f*multiplayer); break;
            case 270: force = new Vector2(-1f, -1f); break;
            case 315: force = new Vector2(1f * multiplayer, -1f*multiplayer); break;
            default:
                Gdx.app.error(TAG, "Wrong rotation given: " + rotation);
        }
        return force;
    }

    public static float getDistance(Position2 from, Position2 to){
        float distance = (float) (Math.pow(to.getX() - from.getX(), 2) + Math.pow(to.getY() - from.getY(), 2));
        return (float) Math.sqrt(distance);
    }

}
