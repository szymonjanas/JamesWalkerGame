package com.mrwalker.firstgame.Entity.Parts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mrwalker.firstgame.Entity.Models.Configs.EntityBodyConfig;
import com.mrwalker.firstgame.Entity.Models.EntityState;
import com.mrwalker.firstgame.WorldManager;
import com.mrwalker.firstgame.auxiliary.Position2;
import org.jetbrains.annotations.NotNull;

public class EntityBody {

    private final EntityState state;
    private final Body body;
    private Vector2 bodyVelocity = new Vector2(0f, 0f);

    public EntityBody(@NotNull EntityBodyConfig config, @NotNull EntityState state){
        this.state = state;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(state.getLocation().getPosition().toVector2());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = WorldManager.getWorld().createBody(bodyDef);
        body.setLinearVelocity(0,0);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(config.radius); //set example radius

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;

        fixtureDef.restitution = config.restitution;
        fixtureDef.friction = config.friction;
        fixtureDef.density = config.density;

        Fixture fixture = body.createFixture(fixtureDef);
        circleShape.dispose();
        body.setFixedRotation(true);

        body.setUserData(this.state.getID());
    }

    public void move(Vector2 force){
        body.setLinearVelocity(force);
        bodyVelocity = body.getLinearVelocity();
        update();
    }

    public void clearVelocity(){
        body.setLinearVelocity(0f,0f);
    }

    public void update(){
        /*
            Update state position up to body location
         */
        state.getLocation().setPosition(new Position2(body.getPosition()));
        if (!body.getLinearVelocity().equals(bodyVelocity)){
            clearVelocity();
        }
    }

    public void upgrade(){
        /*
            Force body to set specified in state position
         */
        body.setTransform(state.getLocation().getPosition().toVector2(), state.getLocation().getOrientation());
    }
}
