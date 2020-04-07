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

    private ArrayList<Animation<TextureRegion>> walkAnimations;
    private Texture walkTexture;
    private float stateTime = 0f;

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

    protected void loadAnimations(EntityUtility utility){
        walkAnimations = new ArrayList<>();
        walkTexture = utility.getTextureByName("player");
        TextureRegion[][] temp = TextureRegion.split(walkTexture,
                64, 64);
        ArrayList<TextureRegion> walkFrames = new ArrayList<>();
        for (TextureRegion[] regionTable : temp){
            walkAnimations.add(new Animation<TextureRegion>(1f/10f,
                    Arrays.copyOfRange(regionTable, 0, 9)));
        }
    }

    protected void renderAnimation(SpriteBatch batch, Position2 position){
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimations.get(10).getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.getX()-32, position.getY()-8);
    }
}
