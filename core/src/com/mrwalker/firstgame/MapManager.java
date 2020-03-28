package com.mrwalker.firstgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.mrwalker.firstgame.Converter.Converter;
import com.mrwalker.firstgame.Utility.MapUtility;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;

public class MapManager {
    private static final String TAG = MapManager.class.getSimpleName();

    private TiledMap currentMap = null;
    private IsometricTiledMapRenderer mapRenderer = null;
    private Size2 mapIsometricSize = new Size2(-1f, -1f);
    private String mapName = "Load map error!";
    private MapUtility utility;

    public MapManager() {
        utility = new MapUtility();
        utility.loadAsset("main","map", "maps/basic-map.tmx");
        utility.finishLoading();

        currentMap = utility.getMapByName("main");

        mapName = currentMap.getProperties().get("name", String.class);

        mapIsometricSize.setHeight(currentMap.getProperties().get("isometricHeight", Integer.class));
        mapIsometricSize.setWidth(currentMap.getProperties().get("isometricWidth", Integer.class));
    }

    public Size2 getIsometricSize(){
        return mapIsometricSize;
    }

    public Position2 getSpawnPoints(){
        return Converter.isometricToCartesian (
            new Position2(
                currentMap.getLayers().get("Spawn")
                        .getObjects().get("spawn")
                        .getProperties()
                        .get("x", Float.class),

                currentMap.getLayers().get("Spawn")
                    .getObjects().get("spawn")
                    .getProperties()
                    .get("y", Float.class)
                    )
        );
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
