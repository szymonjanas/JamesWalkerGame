package com.mrwalker.firstgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mrwalker.firstgame.auxiliary.Position2;

public class Player {

    private Position2 position;
    private Sprite asset;
    private int velocity;

    public Player(int velocity){
        this.velocity = velocity;
        asset = new Sprite(new Texture("player.jpg"));
    }

    public void setPosition(Position2 position){
        this.position = position;
        // centering asset
        asset.setSize(50, 50);
        asset.setPosition(position.getX() - asset.getWidth()/2, position.getY() - asset.getHeight()/2);
    }

    public void render(SpriteBatch batch){
        asset.draw(batch);
    }

    public Position2 getPosition(){
        return position;
    }

    public Sprite getSprite(){
        return asset;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
