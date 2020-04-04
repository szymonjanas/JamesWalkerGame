package com.mrwalker.firstgame.PlayerController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.Player;

import static com.badlogic.gdx.Input.Keys.*;

public class PlayerDesktopController implements InputProcessor, PlayerController {

    private Player player;
    private float velocity = 1000000000;

    private float rotation = 0;

    private Vector2 setVelocityInMove(Vector2 move){
        return new Vector2(
            move.x * velocity,
            move.y * velocity);
    }

    public void setController(Player player) {
        this.player = player;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        Directions.keyDown(keycode);
        player.movePlayer(setVelocityInMove(Directions.getVelocity()), Directions.getRotation());
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Directions.keyUp(keycode);
        player.movePlayer(setVelocityInMove(Directions.getVelocity()), Directions.getRotation());
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
