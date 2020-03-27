package com.mrwalker.firstgame.MainScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mrwalker.firstgame.Camera;
import com.mrwalker.firstgame.GameMain;
import com.mrwalker.firstgame.SceneManager.StageManager;

public class MainScreen  implements Screen {

    private GameMain gameMain;

    private SpriteBatch batch;

    private StageManager stage;


    public MainScreen(GameMain gameMain) {
        this.gameMain = gameMain;
    }

    @Override
    public void show() {
        Camera.initInstance();
        Camera.getInstance().getCamera().position.x = 0f;
        Camera.getInstance().getCamera().position.y = 0f;
        batch = new SpriteBatch();

        stage = new StageManager();
        stage.createMap();
        stage.createPlayer();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.createCameraDependency(batch);
        stage.updatePlayerController();
        stage.updateCameraToPlayerPosition();
        stage.render(batch);
     }

    @Override
    public void resize(int width, int height) {
        Camera.getInstance().setViewPort(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
