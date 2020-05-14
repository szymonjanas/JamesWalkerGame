package com.mrwalker.firstgame.PlayerController;

import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.Input.*;

public class Directions {

    private static Vector2 velocity = new Vector2(0,0);
    private static int rotation = 0;

    private static final float multiplayer = (float) (Math.sqrt(2)/2);

    public static void keyDown(int keycode){
        switch (keycode){
            case Keys.UP:
                if (velocity.y <= 1)
                    velocity.y += 1;
                break;
            case Keys.DOWN:
                if (velocity.y >= 0)
                    velocity.y -= 1;
                break;
            case Keys.LEFT:
                if (velocity.x >= 0)
                    velocity.x -= 1;
                break;
            case Keys.RIGHT:
                if (velocity.x <= 1)
                    velocity.x += 1;
                break;
        }
    }

    public static void keyUp(int keycode){
        switch (keycode){
            case Keys.DOWN:
                if (velocity.y <= 1)
                    velocity.y += 1;
                break;
            case Keys.UP:
                if (velocity.y >= 0)
                    velocity.y -= 1;
                break;
            case Keys.RIGHT:
                if (velocity.x >= 0)
                    velocity.x -= 1;
                break;
            case Keys.LEFT:
                if (velocity.x <= 1)
                    velocity.x += 1;
                break;
        }
    }

    public static Vector2 getVelocity(){
        if (velocity.x != 0 && velocity.y != 0){
            return new Vector2(velocity.x * multiplayer, velocity.y * multiplayer);
        }
        return velocity;
    }

    public static int getRotation() {
        if (velocity.x == 0 && velocity.y >= 0){
            rotation = 90;

        }
        else if (velocity.x == 0 && velocity.y <= 0){
            rotation = 270;
        }
        else if (velocity.x >= 0 && velocity.y == 0){
            rotation = 0;
        }
        else if (velocity.x <= 0 && velocity.y == 0){
            rotation = 180;
        }
        else if (velocity.x > 0 && velocity.y > 0){
            rotation = 45;
        }
        else if (velocity.x < 0 && velocity.y < 0){
            rotation = 225;
        }
        else if (velocity.x < 0 && velocity.y > 0){
            rotation = 135;
        }
        else if (velocity.x > 0 && velocity.y < 0){
            rotation = 315;
        }
        return rotation;
    }
}
