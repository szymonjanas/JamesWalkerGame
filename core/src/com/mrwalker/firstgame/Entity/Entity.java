package com.mrwalker.firstgame.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mrwalker.firstgame.Utility.EntityUtility;
import com.mrwalker.firstgame.auxiliary.Position2;

import java.util.ArrayList;
import java.util.Arrays;

public class Entity {

    protected World world;

    protected BodyDef bodyDef;
    protected Body body;
    protected CircleShape circleShape;
    protected FixtureDef fixtureDef;
    protected Fixture fixture;



    protected Entity(World world){
        this.world = world;

        bodyDef = new BodyDef();
        bodyDef.position.set(0f, 0f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyDef);
        body.setLinearVelocity(0,0);
        circleShape = new CircleShape();
        circleShape.setRadius(10f); //set example radius

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        // default values from libgdx site
        fixtureDef.density = 1f;

        fixture = body.createFixture(fixtureDef);
        circleShape.dispose();

        body.setFixedRotation(true);

    }


}
