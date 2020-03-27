package com.mrwalker.firstgame.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class EntityUtility extends Utility {

    public Texture getTextureByName(String name){
        Texture texture = null;
        if (assetManager.isLoaded(assetNamesMap.get(name))){
            texture = assetManager.get(assetNamesMap.get(name));
        } else {
            Gdx.app.error(TAG,
                    "Texture has not been loaded: " + name + ", path: " + assetNamesMap.get(name));
        }
        return texture;
    }

    public Texture getTextureByPath(String path){
        Texture texture = null;
        if (assetManager.isLoaded(path)){
            texture = assetManager.get(path);
        } else {
            Gdx.app.error(TAG,
                    "Texture has not been loaded: " + path);
        }
        return texture;
    }
}
