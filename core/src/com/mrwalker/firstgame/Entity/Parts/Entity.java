package com.mrwalker.firstgame.Entity.Parts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.BodyID.BodyID;
import com.mrwalker.firstgame.Entity.EntityBody;
import com.mrwalker.firstgame.Entity.Models.Behaviour;
import com.mrwalker.firstgame.Entity.Models.EntityState;
import com.mrwalker.firstgame.Entity.Parts.Animations.EntityAnimation;
import com.mrwalker.firstgame.auxiliary.Position2;

import org.jetbrains.annotations.NotNull;

public class Entity {
    private static final String TAG = Entity.class.getSimpleName();

    private final EntityBody body;
    private final EntityState state;
    private final EntityAnimation animation;

    public Entity(@NotNull EntityState state,
                  @NotNull EntityBody body,
                  @NotNull EntityAnimation animation) {
        this.state = state;
        this.body = body;
        this.animation = animation;
    }

    public void move(Vector2 force, short orientation){
        if (state.getCondition().isAlive()){
            body.clearVelocity();
            if (force.x == 0 && force.y == 0){
                state.setBehaviour(Behaviour.Stance);
            } else {
                state.setBehaviour(Behaviour.Running);
                body.move(state.getCondition().calculateRunForce(force));
                state.getLocation().setOrientation(orientation);
            }
        }
    }

    public Position2 getPosition(){
        return state.getLocation().getPosition();
    }

    public void setPosition(Position2 position){
        /*
            Forcing position setting
         */
        state.getLocation().setPosition(position);
        body.upgrade();
    }

    public void setRotation(int rotation){
        state.getLocation().setOrientation((short) rotation);
    }

    public void getDamages(int damages){
        if (damages >= state.getCondition().getHealth()) die();
        else state.getCondition().getHurt((short) damages);
    }

    public void attack(Entity entity){
        state.setBehaviour(Behaviour.MeleeSwing);
        entity.getDamages(state.getCondition().getDamages());
    }

    public void die(){
        state.getCondition().setAlive(false);
    }

    public void render(SpriteBatch batch){
        this.body.update();
        this.animation.render(batch);
    }

    public Object getID(){
        return state.getID();
    }

    public boolean checkIdentification(BodyID ID){
        return state.getID().equals(ID);
    }

    public void dispose(){

    }
}
