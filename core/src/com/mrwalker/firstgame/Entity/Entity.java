package com.mrwalker.firstgame.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.Camera;
import com.mrwalker.firstgame.Contact.EntityIdentification;
import com.mrwalker.firstgame.Entity.Configs.EntityAnimationConfig;
import com.mrwalker.firstgame.Entity.Configs.EntityConfig;
import com.mrwalker.firstgame.PlayerController.PlayerController;
import com.mrwalker.firstgame.auxiliary.Position2;

public class Entity {
    private static final String TAG = Entity.class.getSimpleName();

    private EntityBody body;
    private EntityState entityState;
    private EntityAnimation animation;

    private EntityIdentification entityIdentification;

    public Entity(
                    float speed,
                    Position2 position,
                    EntityIdentification entityIdentification,
                    EntityConfig config) {
        this.entityIdentification = entityIdentification;
        entityState = new EntityState(this.entityIdentification);
        entityState.speed = speed;
        entityState.rotation = 180;
        entityState.movement = EntityState.Movements.Stance;
        entityState.position = new Position2(0f, 0f);
        entityState.direction = EntityState.Directions.Down;
        body = new EntityBody(entityState);
        this.setPosition(position);
        animation = new EntityAnimation(entityState, (EntityAnimationConfig) config);
    }

    public boolean isPlayer(){
        return entityState.isPlayer;
    }

    public void setAsPlayer(PlayerController controller){
        controller.setController(this);
        entityState.isPlayer = true;
    }

    private void updateCamera(){
        Camera.setPosition(getPosition());
        Camera.update();
    }

    public void move(Vector2 force, int rotation){
        if (isAlive()){
            body.clearVelocity();
            if (force.x == 0 && force.y == 0){
                entityState.movement = EntityState.Movements.Stance;
            } else {
                entityState.movement = EntityState.Movements.Running;
                body.move(entityState.multiplyWithVelocity(force));
                entityState.rotation = rotation;
            }
        }
    }

    public Position2 getPosition(){
        return entityState.position;
    }

    public void setPosition(Position2 position){
        if (isAlive()){
            entityState.position = position;
            this.body.upgrade();
        }
    }

    public void setRotation(int rotation){
        entityState.rotation = rotation;
    }

    public void getDamages(int damages){
        if (damages >= entityState.health) kill();
        else this.entityState.health -= damages;
    }

    public void attack(Entity entity){
        this.entityState.movement = EntityState.Movements.MeleeSwing;
        entity.getDamages(entityState.damages);
    }

    public void kill(){
        entityState.isAlive = false;
        entityState.movement = EntityState.Movements.HitAndDie;
    }

    public boolean isAlive(){
        return entityState.isAlive;
    }

    public void render(SpriteBatch batch){
        if(entityState.isPlayer) updateCamera();
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
