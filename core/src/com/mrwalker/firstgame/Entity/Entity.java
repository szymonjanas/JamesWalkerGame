package com.mrwalker.firstgame.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.Utility.Utility;
import com.mrwalker.firstgame.auxiliary.Position2;

public class Entity {
    private static final String TAG = Entity.class.getSimpleName();

    private EntityBody body;
    private EntityState entityState;
    private EntityAnimation animation;

    private EntityIdentification entityIdentification;

    public Entity(EntityIdentification entityIdentification) {
        this.entityIdentification = entityIdentification;
        entityState = new EntityState(this.entityIdentification);
        entityState.rotation = 180;
        entityState.movement = EntityState.Movements.Stance;
        entityState.position = new Position2(0f,0f);
        entityState.direction = EntityState.Directions.Down;
        body = new EntityBody(entityState);
        animation = new EntityAnimation(entityState);
    }

    public void move(Vector2 force, int rotation){
        body.clearVelocity();
        if (force.x == 0 && force.y == 0){
            entityState.movement = EntityState.Movements.Stance;
        } else {
            entityState.movement = EntityState.Movements.Running;
            body.move(entityState.multiplyWithVelocity(force));
            entityState.rotation = rotation;
        }
    }

    public Position2 getPosition(){
        return entityState.position;
    }

    public void setPosition(Position2 position){
        entityState.position = position;
        this.body.upgrade();
    }

    public void setRotation(int rotation){
        entityState.rotation = rotation;
    }

    public void render(SpriteBatch batch){
        this.body.update();
        this.animation.renderAnimation(batch);
    }

    public EntityIdentification getEntityIdentification(){
        return entityIdentification;
    }

    public boolean checkIdentification(EntityIdentification entityIdentification){
        return this.entityIdentification.equals(entityIdentification);
    }

    public void dispose(){

    }
}
