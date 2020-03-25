package com.mrwalker.firstgame.MainScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.Camera;
import com.mrwalker.firstgame.GameMain;
import com.mrwalker.firstgame.MapManager;
import com.mrwalker.firstgame.Player;
import com.mrwalker.firstgame.PlayerController;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;
import com.mrwalker.firstgame.coordinatesConverters.CamToIsoConverter;

public class MainScreen  implements Screen {

    private GameMain gameMain;

    private SpriteBatch batch;
    private Texture img;


    private MapManager mapManager;

    private Player player;
    private PlayerController playerController;

    public MainScreen(GameMain gameMain) {
        this.gameMain = gameMain;
    }

    @Override
    public void show() {
        Camera.initInstance();
        Camera.getInstance().setWorldSize(new Size2(6000f, 6000f));
        Camera.getInstance().getCamera().position.x = 0f;
        Camera.getInstance().getCamera().position.y = 0f;
        batch = new SpriteBatch();
        img = new Texture("player.jpg");

        mapManager = new MapManager();
        mapManager.loadCurrentMap();

        player = new Player(10); // example velocity injected
        player.setPosition(mapManager.getSpawnPoints());

        playerController = new PlayerController();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(Camera.getInstance().getCamera().combined);
        playerController.controller(player);
        Camera.getInstance().setPosition(new Position2(
                player.getPosition().getX(),
                player.getPosition().getY()));
        Camera.getInstance().update();
        mapManager.setView(Camera.getInstance().getCamera());
        mapManager.render();
        batch.begin();
        player.render(batch);
        batch.end();
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
        img.dispose();
        mapManager.dispose();
    }
}
