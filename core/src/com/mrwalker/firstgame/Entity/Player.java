package com.mrwalker.firstgame.Entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mrwalker.firstgame.Utility.EntityUtility;
import com.mrwalker.firstgame.auxiliary.Position2;

public class Player extends Entity {

    private Position2 position;
    private Sprite asset;
    private int velocity;
    private EntityUtility utility;
    private EntityAnimation animation;


    public Player(World world){
        super(world);
        this.velocity = 5; // default value
        utility = new EntityUtility();
        animation = new EntityAnimation();
    }

    public void loadAssets(){
        utility.loadAsset("player", "texture", "Player/player.png");
        utility.finishLoading();
    }

    public void setAssets(){
        animation.loadAnimations(utility);
    }

    public boolean isFinishedLoadingAssets(){
        return utility.isFinished();
    }

    public void setPosition(Position2 position){
        this.position = position;
        body.setTransform(position.toVector2(), 0);
    }

    public void movePlayer(Vector2 move, float rotation){
        if (move.x == 0 && move.y == 0){
            clearVelocity();
            return;
        }
        body.applyLinearImpulse(move, this.position.toVector2(), true);
        this.position.set(body.getPosition(), rotation);
        animation.move(rotation, move);
    }

    public void update(){
        this.position.set(body.getPosition());
    }

    public void clearVelocity(){
        body.setLinearVelocity(0f,0f);
        animation.move(this.position.getRotation(), new Vector2(0f,0f));
    }

    public void render(SpriteBatch batch){
        animation.renderAnimation(batch, position);
    }

    public Position2 getPosition(){
        return position;
    }

    public void dispose(){
        utility.dispose();
    }
}
