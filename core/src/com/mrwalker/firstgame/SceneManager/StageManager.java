package com.mrwalker.firstgame.SceneManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mrwalker.firstgame.Camera;
import com.mrwalker.firstgame.Converter.Converter;
import com.mrwalker.firstgame.Map.MapManager;
import com.mrwalker.firstgame.Entity.Player;
import com.mrwalker.firstgame.PlayerController.PlayerController;
import com.mrwalker.firstgame.PlayerController.PlayerDesktopController;
import com.mrwalker.firstgame.auxiliary.Position2;

public class StageManager {

    private World world;

    private Player player;
    private MapManager mapManager;
    private Camera cameraInstance;
    private PlayerController playerController;

    private Box2DDebugRenderer debugRenderer;
    private Matrix4 matrix4;

    public StageManager() {
        Camera.initInstance();
        cameraInstance = Camera.getInstance();
        playerController = new PlayerDesktopController();
        world = new World(new Vector2(0,0), true);

        world.setContactListener(new ContactListener() {
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
        player = new Player(world);
        player.loadAssets();
        player.setAssets();
        player.setPosition(mapManager.getPoint("spawn", "Spawn"));
        playerController.setController(player);
    }

    public void createMap(){
        mapManager = new MapManager(world);
        Converter.setMapIsometricSize(mapManager.getIsometricSize());
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
        world.step(1f/60f, 6, 2);

        matrix4 = batch.getProjectionMatrix().cpy();
        mapManager.render();
        batch.begin();
        player.render(batch);
        batch.end();
        debugRenderer.render(world, matrix4);
        player.update();
    }

    public void updateCamera(){
        cameraInstance.update();
    }

    public void dispose(){
        mapManager.dispose();
        player.dispose();
    }
}
