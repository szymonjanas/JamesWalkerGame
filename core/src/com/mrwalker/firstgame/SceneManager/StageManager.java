package com.mrwalker.firstgame.SceneManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Json;
import com.mrwalker.firstgame.Camera;
import com.mrwalker.firstgame.Config.Config;
import com.mrwalker.firstgame.Contact.Contact;
import com.mrwalker.firstgame.Contact.ObjectsTypes;
import com.mrwalker.firstgame.Converter.Converter;
import com.mrwalker.firstgame.Entity.Configs.EntityAnimationConfig;
import com.mrwalker.firstgame.Entity.EntitiesManager;
import com.mrwalker.firstgame.Entity.Entity;
import com.mrwalker.firstgame.Contact.EntityIdentification;
import com.mrwalker.firstgame.GameMap.GameMap;
import com.mrwalker.firstgame.PlayerController.PlayerController;
import com.mrwalker.firstgame.PlayerController.PlayerDesktopController;
import com.mrwalker.firstgame.Utility.Utility;
import com.mrwalker.firstgame.Utility.UtilityConfig;
import com.mrwalker.firstgame.WorldManager;
import com.mrwalker.firstgame.auxiliary.Position2;

public class StageManager {

    private EntitiesManager entities;
    private Entity player;

    private PlayerController playerController;
    private GameMap map;

    private Box2DDebugRenderer debugRenderer;
    private Matrix4 matrix4;

    private Json json = new Json();

    private Entity npc;

    public StageManager() {
        playerController = new PlayerDesktopController();

        Utility.loadAssets(Config.load(UtilityConfig.class, "configs/entity-assets-config.json"));
        Utility.loadAssets(Config.load(UtilityConfig.class, "configs/maps-assets-config.json"));
        Utility.finishLoading();

        map = new GameMap("maps/basic-map.tmx");

        // TODO load entities config from file - config for entity
        entities = new EntitiesManager();
        entities.add( new Entity(
                map.getSpawnPoint("npc"),
                new EntityIdentification.Builder()
                        .id(1001)
                        .name("John")
                        .type(ObjectsTypes.NPC)
                        .build(),
                Config.load(EntityAnimationConfig.class, "configs/npc-animations-config.json")
        ));
        player = new Entity(
                map.getSpawnPoint("player"),
                new EntityIdentification.Builder()
                            .id(1000)
                            .name("James")
                            .type(ObjectsTypes.Player)
                            .build(),
                Config.load(EntityAnimationConfig.class, "configs/player-animations-config.json")
        );


        WorldManager.getWorld().setContactListener(new Contact(entities, player));
        debugRenderer = new Box2DDebugRenderer();
    }

    public void createPlayer(){
        playerController.setController(player);
    }

    public void createMap(){
        Converter.setMapIsometricSize(map.getIsometricSize());
    }

    public void createCameraDependency(SpriteBatch batch){
        batch.setProjectionMatrix(Camera.getCamera().combined);
        map.setView();
    }

    public void setCameraPosition(Position2 position){
        Camera.setPosition(position);
    }

    public void updateCameraToPlayerPosition(){
        Camera.setPosition(player.getPosition());
        Camera.update();
    }

    public void render(SpriteBatch batch){
        WorldManager.getWorld().step(1f/60f, 6, 2);

        matrix4 = batch.getProjectionMatrix().cpy();
        map.render();
        batch.begin();
        entities.render(batch);
        player.render(batch);
        batch.end();
        debugRenderer.render(WorldManager.getWorld(), matrix4);
    }

    public void updateCamera(){
        Camera.update();
    }

    public void dispose(){
        map.dispose();
        player.dispose();
        entities.dispose();
    }
}
