package com.mrwalker.firstgame.Entity.Models;

import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.Config.Settings;

public class EntityCondition {

    private boolean alive;
    private short health;
    private short damages;
    private byte speed; // 0-100%

    public float frameDuration = 1f/8f;
    public float frameDurationForAction = 1f/2f;

    public EntityCondition(boolean alive, short health, short damages, byte speed) {
        this.alive = alive;
        this.health = health;
        this.damages = damages;
        this.speed = speed;
    }

    public Vector2 calculateRunForce(Vector2 direction){
        return new Vector2(
                direction.x * speed * Settings.velocity,
                direction.y * speed * Settings.velocity
        );
    }

    public void getHurt(short damages){
        if (damages >= health){
            health = 0;
            alive = false;
        } else if (damages > 0){
            health -= damages;
        }
    }

    public byte getSpeed() {
        return speed;
    }

    public void setSpeed(byte speed) {
        this.speed = speed;
    }

    public short getDamages() {
        return damages;
    }

    public void setDamages(short damages) {
        this.damages = damages;
    }

    public short getHealth() {
        return health;
    }

    public void setHealth(short health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }

    public float getFrameDurationForAction() {
        return frameDurationForAction;
    }

    public void setFrameDurationForAction(float frameDurationForAction) {
        this.frameDurationForAction = frameDurationForAction;
    }
}
