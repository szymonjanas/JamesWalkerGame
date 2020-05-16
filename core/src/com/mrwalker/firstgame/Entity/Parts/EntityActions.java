package com.mrwalker.firstgame.Entity.Parts;

import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.Entity.Models.Behaviour;

public class EntityActions {

    private final Entity entity;

    public EntityActions(Entity entity) {
        this.entity = entity;
    }

    public void move(Vector2 force, short orientation){
        if (entity.state.getCondition().isAlive()){
            entity.body.clearVelocity();
            if (force.x == 0 && force.y == 0){
                entity.state.setBehaviour(Behaviour.Stance);
            } else {
                entity.state.setBehaviour(Behaviour.Running);
                entity.body.move(entity.state.getCondition().calculateRunForce(force));
                entity.state.getLocation().setOrientation(orientation);
            }
        }
    }

    public void getDamages(int damages){
        if (damages >= entity.state.getCondition().getHealth()) die();
        else entity.state.getCondition().getHurt((short) damages);
    }

    public void attack(Entity enemy){
        entity.state.setBehaviour(Behaviour.MeleeSwing);
        enemy.actions.getDamages(entity.state.getCondition().getDamages());
    }

    public void die(){
        entity.state.getCondition().setAlive(false);
    }

    public void makeNoise(short noise){
        entity.state.getEnvEffect().setNoiseFor(noise, 1000);
    }

    public void startMovement(){
        entity.state.getCondition().setSpeed((byte) 100);
    }

    public void stopMovement(){
        entity.state.getCondition().setSpeed((byte) 0);
        entity.state.setBehaviour(Behaviour.Stance);
        entity.body.clearVelocity();
    }

    public void overhears(){
        stopMovement();
        entity.state.getEnvEffect().setEarshot((short) 300);
    }

    public void stopOverhears(){
        entity.state.getCondition().setSpeed((byte) 100);
        entity.state.getEnvEffect().setDefaultEarshot();
    }





}
