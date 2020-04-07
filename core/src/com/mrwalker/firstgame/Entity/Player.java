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

    public Player(World world){
        super(world);
        this.velocity = 5; // default value
        utility = new EntityUtility();
    }

    public void loadAssets(){
//        utility.loadAsset("player", "texture", "player.jpg");
        utility.loadAsset("player", "texture", "Player/player.png");
        utility.finishLoading();
    }

    public void setAssets(){
//        asset = new Sprite(utility.getTextureByName("player"));
//        asset.setScale(0.1f, 0.1f);
        loadAnimations(utility);
    }

    public boolean isFinishedLoadingAssets(){
        return utility.isFinished();
    }

    public void setPosition(Position2 position){
        this.position = position;
        body.setTransform(position.toVector2(), 0);
//        asset.setPosition(
//                position.getX() - asset.getWidth()/2f,
//                position.getY() - asset.getHeight()/2f);
    }

    public void movePlayer(Vector2 move, float rotation){
        if (move.x == 0 && move.y == 0){
            clearVelocity();
            return;
        }
        body.applyLinearImpulse(move, this.position.toVector2(), true);
        this.position.set(body.getPosition(), rotation);
    }

    public void update(){
        this.position.set(body.getPosition());
    }

    public void clearVelocity(){
        body.setLinearVelocity(0f,0f);
    }

    public void render(SpriteBatch batch){
        renderAnimation(batch, this.position);
    }

    public Position2 getPosition(){
        return position;
    }

    public void dispose(){
        utility.dispose();
    }
}
