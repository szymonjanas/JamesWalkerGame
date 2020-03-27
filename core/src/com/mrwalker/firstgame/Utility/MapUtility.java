package com.mrwalker.firstgame.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class MapUtility extends Utility {
    public TiledMap getMapByName(String name){
        TiledMap tiledMap = null;
        if (assetManager.isLoaded(assetNamesMap.get(name))){
            tiledMap = assetManager.get(assetNamesMap.get(name));
        } else {
            Gdx.app.error(TAG,
                    "Map has not been loaded: " + name + ", path: " + assetNamesMap.get(name));
        }
        return tiledMap;
    }

    public TiledMap getMapByPath(String path){
        TiledMap tiledMap = null;
        if (assetManager.isLoaded(path)){
            tiledMap = assetManager.get(path);
        } else {
            Gdx.app.error(TAG, "Map has not been loaded: " + path);
        }
        return tiledMap;
    }
}
