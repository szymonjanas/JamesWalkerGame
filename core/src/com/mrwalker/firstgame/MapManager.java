package com.mrwalker.firstgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.mrwalker.firstgame.Utility.MapUtility;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;

public class MapManager {
    private static final String TAG = MapManager.class.getSimpleName();

    private TiledMap currentMap = null;
    private IsometricTiledMapRenderer mapRenderer = null;
    private int cartesianHeight = -1;
    private String mapName = "Load map error!";
    private MapUtility utility;

    public MapManager() {
        utility = new MapUtility();
        utility.loadAsset("main","map", "maps/basic-map.tmx");
        utility.finishLoading();
        currentMap = utility.getMapByName("main");
        cartesianHeight = currentMap.getProperties().get("cartesianHeight", Integer.class);
        mapName = currentMap.getProperties().get("name", String.class);
    }

    public Position2 getSpawnPoints(){
        return new Position2(
            currentMap.getLayers().get("Spawn")
                    .getObjects().get("spawn")
                    .getProperties()
                    .get("x", Float.class),
            tiledToCartesian(
                    currentMap.getLayers().get("Spawn")
                        .getObjects().get("spawn")
                        .getProperties()
                        .get("y", Float.class))
        );
    }

    private float tiledToCartesian(Float isometric){
        if (cartesianHeight == -1){
            Gdx.app.error(TAG, "cartesianHeight not found in map" + mapName);
        }
        return isometric - cartesianHeight/2f;
    }

    public Size2 getMapSize(){
        return new Size2(
                (float) currentMap.getProperties().get("width", Integer.class),
                (float) currentMap.getProperties().get("height", Integer.class)
        );
    }

    public void loadCurrentMap(){
        mapRenderer = new IsometricTiledMapRenderer(currentMap);
    }

    public IsometricTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    public void setView(OrthographicCamera camera){
        mapRenderer.setView(camera);
    }

    public void render(){
        mapRenderer.render();
    }

    public void dispose(){
        currentMap.dispose();
        mapRenderer.dispose();
    }
}
