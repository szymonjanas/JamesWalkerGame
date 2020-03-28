package com.mrwalker.firstgame.SceneManager;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mrwalker.firstgame.Camera;
import com.mrwalker.firstgame.MapManager;
import com.mrwalker.firstgame.Player;
import com.mrwalker.firstgame.PlayerController.PlayerController;
import com.mrwalker.firstgame.PlayerController.PlayerDesktopController;
import com.mrwalker.firstgame.auxiliary.Position2;

public class StageManager {

    private Player player;
    private MapManager mapManager;
    private Camera cameraInstance;
    private PlayerController playerController;

    public StageManager() {
        Camera.initInstance();
        cameraInstance = Camera.getInstance();
        playerController = new PlayerDesktopController();
    }

    public void createPlayer(){
        player = new Player();
        player.loadAssets();
        player.setAssets();
        player.setPosition(mapManager.getSpawnPoints());
        playerController.setController(player);
    }

    public void createMap(){
        mapManager = new MapManager();
        mapManager.loadCurrentMap();
    }

    public void updatePlayerController(){
        playerController.controller();
    }

    public void createCameraDependency(SpriteBatch batch){
        batch.setProjectionMatrix(cameraInstance.getCamera().combined);
        mapManager.setView(cameraInstance.getCamera());
        cameraInstance.update();
    }

    public void setCameraPosition(Position2 position){
        cameraInstance.setPosition(position);
    }

    public void updateCameraToPlayerPosition(){
        cameraInstance.setPosition(player.getPosition());
        cameraInstance.update();
    }

    public void render(SpriteBatch batch){
        mapManager.render();
        batch.begin();
        player.render(batch);
        batch.end();

    }

    public void updateCamera(){
        cameraInstance.update();
    }

    public void dispose(){
        mapManager.dispose();
        player.dispose();
    }

}