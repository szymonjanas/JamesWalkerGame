package com.mrwalker.firstgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class MapManager {
    private static final String TAG = MapManager.class.getSimpleName();

    private TiledMap currentMap = null;
    private IsometricTiledMapRenderer mapRenderer = null;

    public MapManager() {
        currentMap = new TmxMapLoader().load("maps/basic-map.tmx");
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
