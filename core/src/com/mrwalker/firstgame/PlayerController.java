package com.mrwalker.firstgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.auxiliary.Position2;

public class PlayerController {
    public void controller(Player player){
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.setPosition(new Position2(
                    player.getPosition().getX(),
                    player.getPosition().getY()+player.getVelocity()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.setPosition(new Position2(
                    player.getPosition().getX(),
                    player.getPosition().getY()-player.getVelocity()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.setPosition(new Position2(
                    player.getPosition().getX()-player.getVelocity(),
                    player.getPosition().getY()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.setPosition(new Position2(
                    player.getPosition().getX()+player.getVelocity(),
                    player.getPosition().getY()));
        }
    }
}
