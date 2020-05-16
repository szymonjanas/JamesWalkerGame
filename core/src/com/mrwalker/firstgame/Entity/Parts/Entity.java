package com.mrwalker.firstgame.Entity.Parts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.BodyID.BodyID;
import com.mrwalker.firstgame.Entity.Models.Behaviour;
import com.mrwalker.firstgame.Entity.Models.EntityState;
import com.mrwalker.firstgame.auxiliary.Position2;

import org.jetbrains.annotations.NotNull;

public class Entity {
    private static final String TAG = Entity.class.getSimpleName();

    protected final EntityBody body;
    protected final EntityState state;
    protected final EntityAnimation animation;
    public final EntityActions actions;

    public Entity(@NotNull EntityState state,
                  @NotNull EntityBody body,
                  @NotNull EntityAnimation animation) {
        this.state = state;
        this.body = body;
        this.animation = animation;
        actions = new EntityActions(this);
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

    public void render(SpriteBatch batch){
        this.body.update();
        this.animation.render(batch);
    }

    public BodyID getID(){
        return state.getID();
    }

    public boolean checkIdentification(BodyID ID){
        return state.getID().equals(ID);
    }

    public void dispose(){

    }
}
