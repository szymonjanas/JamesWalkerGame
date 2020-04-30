package com.mrwalker.firstgame.Entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mrwalker.firstgame.WorldManager;
import com.mrwalker.firstgame.auxiliary.Position2;

public class EntityBody {

    private EntityState entityState;

    private BodyDef bodyDef;
    private Body body;
    private CircleShape circleShape;
    private FixtureDef fixtureDef;
    private Fixture fixture;

    public EntityBody(EntityState entityState){
        this.entityState = entityState;
        initialization(new Position2(0f, 0f), 10f, 1f);
    }

    public EntityBody(Position2 position, float radius, float density){
        initialization(position, radius, density);
    }

    private void initialization(Position2 position, float radius, float density){
        bodyDef = new BodyDef();
        bodyDef.position.set(position.getX(), position.getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = WorldManager.getWorld().createBody(bodyDef);
        body.setLinearVelocity(0,0);
        circleShape = new CircleShape();
        circleShape.setRadius(radius); //set example radius

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;

        fixtureDef.density = density;

        fixture = body.createFixture(fixtureDef);
        circleShape.dispose();

        body.setFixedRotation(true);
    }

    public void move(Vector2 force){
        body.applyForceToCenter(force, true);
        update();
    }

    public void clearVelocity(){
        body.setLinearVelocity(0f,0f);
    }

    public void update(){
        entityState.position = new Position2(body.getPosition());
    }

    public void upgrade(){
        body.setTransform(entityState.position.toVector2(), entityState.rotation);
    }

    public Position2 getPosition(){
        return new Position2(body.getPosition());
    }

}
