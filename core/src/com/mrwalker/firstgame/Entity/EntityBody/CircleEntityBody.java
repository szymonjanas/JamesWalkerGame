package com.mrwalker.firstgame.Entity.EntityBody;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mrwalker.firstgame.Entity.Models.EntityState;
import com.mrwalker.firstgame.WorldManager;
import com.mrwalker.firstgame.auxiliary.Position2;

public class CircleEntityBody implements EntityBody {

    private final EntityState state;
    private final Body body;
    private Vector2 bodyVelocity = new Vector2(0f, 0f);

    public CircleEntityBody(EntityState state){
        this.state = state;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(state.getLocation().getPosition().toVector2());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.gravityScale = 0f;
        body = WorldManager.getWorld().createBody(bodyDef);
        body.setLinearVelocity(0,0);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(7f); //set example radius

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;

        fixtureDef.restitution = 0f;
        fixtureDef.friction = 40f;
        fixtureDef.density = (80) / (3.14f * (0.5f * 0.5f));

        Fixture fixture = body.createFixture(fixtureDef);
        circleShape.dispose();
        body.setFixedRotation(true);

        body.setUserData(this.state.getID());
    }

    @Override
    public void move(Vector2 force){
        body.setLinearVelocity(force);
        bodyVelocity = body.getLinearVelocity();
        update();
    }

    public void clearVelocity(){
        body.setLinearVelocity(0f,0f);
    }

    @Override
    public void update(){
        state.getLocation().setPosition(new Position2(body.getPosition()));
        if (!body.getLinearVelocity().equals(bodyVelocity)){
            clearVelocity();
        }
    }

    public void upgrade(){
        body.setTransform(state.getLocation().getPosition().toVector2(), state.getLocation().getOrientation());
    }
}
