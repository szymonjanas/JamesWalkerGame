package com.mrwalker.firstgame.SceneManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mrwalker.firstgame.Camera;
import com.mrwalker.firstgame.Converter.Converter;
import com.mrwalker.firstgame.Entity.Entity;
import com.mrwalker.firstgame.GameMap.GameMap;
import com.mrwalker.firstgame.PlayerController.PlayerController;
import com.mrwalker.firstgame.PlayerController.PlayerDesktopController;
import com.mrwalker.firstgame.Utility.Utility;
import com.mrwalker.firstgame.WorldManager;
import com.mrwalker.firstgame.auxiliary.Position2;

public class StageManager {

    private Entity player;
    private PlayerController playerController;
    private GameMap map;

    private Box2DDebugRenderer debugRenderer;
    private Matrix4 matrix4;


    public StageManager() {
        playerController = new PlayerDesktopController();
        Utility.loadAsset("tiledmap", "maps/basic-map.tmx");
        Utility.loadAsset("texture", "entity/hero/leather_armor.png");
        Utility.loadAsset("texture", "entity/hero/male_head2.png");
        Utility.finishLoading();
        player = new Entity();
        map = new GameMap("maps/basic-map.tmx");

        WorldManager.getWorld().setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                System.out.println("CONTACT");
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
        debugRenderer = new Box2DDebugRenderer();
    }

    public void createPlayer(){
        player.setPosition(map.getPoint("spawn", "Spawn"));
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
    }
}
