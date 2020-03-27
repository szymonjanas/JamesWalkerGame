package com.mrwalker.firstgame.PlayerController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mrwalker.firstgame.Player;
import com.mrwalker.firstgame.auxiliary.Position2;

public class PlayerDesktopController implements PlayerController {

    private Player player;

    @Override
    public void setController(Player player) {
        this.player = player;
    }

    @Override
    public void controller(){
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
