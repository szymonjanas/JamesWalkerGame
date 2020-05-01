package com.mrwalker.firstgame.Entity;

import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.auxiliary.Position2;

public class EntityState {

    public Object entityData;

    public EntityState(Object entityData) {
        this.entityData = entityData;
    }

    public boolean isAlive = true;
    public Position2 position;
    public Movements movement;
    public Directions direction;
    public int rotation;
    public int velocity = 5000000;
    public float speed = 1f; // 0-100%
    public float frameDuration = 1f/9f;

    public Vector2 multiplyWithVelocity(Vector2 force){
        return new Vector2(
                force.x*velocity * speed,
                force.y*velocity * speed);
    }

    public enum Movements {
        Stance,
        Running,
        MeleeSwing,
        Block,
        HitAndDie,
        CastSpell,
        ShootBow
    }

    public enum Directions {
        Left, UpLeft, Up, UpRight, Right, DownRight, Down, DownLeft
    }
}
