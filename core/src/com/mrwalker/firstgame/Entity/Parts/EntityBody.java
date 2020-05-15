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
        CircleShape bodyCircleShape = new CircleShape();
        bodyCircleShape.setRadius(config.radius); //set example radius

        // BODY

        FixtureDef bodyFixtureDef = new FixtureDef();
        bodyFixtureDef.shape = bodyCircleShape;

        bodyFixtureDef.restitution = config.restitution;
        bodyFixtureDef.friction = config.friction;
        bodyFixtureDef.density = config.density;

        Fixture bodyFixture = body.createFixture(bodyFixtureDef);
        bodyCircleShape.dispose();

        // SENSOR

        CircleShape sensorCircleShape = new CircleShape();
        sensorCircleShape.setRadius(config.radius + 10f); //set example radius

        FixtureDef sensorFixtureDef = new FixtureDef();
        sensorFixtureDef.shape = sensorCircleShape;
        sensorFixtureDef.isSensor = true;

        sensorFixtureDef.restitution = config.restitution;
        sensorFixtureDef.friction = config.friction;
        sensorFixtureDef.density = config.density;

        Fixture sensorFixture = body.createFixture(sensorFixtureDef);
        sensorCircleShape.dispose();


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
