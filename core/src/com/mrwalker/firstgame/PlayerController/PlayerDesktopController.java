package com.mrwalker.firstgame.PlayerController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mrwalker.firstgame.Entity.Parts.Entity;

public class PlayerDesktopController implements InputProcessor, PlayerController {

    private Entity player;

    private float rotation = 0;

    public void setController(Entity player) {
        this.player = player;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        Directions.keyDown(keycode);
        player.actions.move(Directions.getVelocity(), (short) Directions.getRotation());

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Directions.keyUp(keycode);
        player.actions.move(Directions.getVelocity(), (short) Directions.getRotation());
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
