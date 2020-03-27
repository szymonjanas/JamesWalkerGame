package com.mrwalker.firstgame.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.HashMap;

public class Utility {
    protected static final String TAG = Utility.class.getSimpleName();

    protected AssetManager assetManager;
    protected InternalFileHandleResolver filePathResolver;
    protected HashMap<String, String> assetNamesMap; //<name, path>

    public Utility(){
        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        filePathResolver = new InternalFileHandleResolver();
        assetNamesMap = new HashMap<>();
    }

    public void loadAsset(String name, String type, String path){
        /*
            assets name types:
            - map
            - texture
         */

        if (filePathResolver.resolve(path).exists()){
            if (!assetManager.isLoaded(path)){
                switch (type){
                    case "map":
                        assetManager.load(path, TiledMap.class);
                        assetNamesMap.put(name, path);
                        break;
                    case "texture":
                        assetManager.load(path, Texture.class);
                        assetNamesMap.put(name, path);
                        break;
                    default:
                        Gdx.app.error(TAG,
                                "Type of asset path: " + path + ", does not exists: " + type);
                }
            } else {
                Gdx.app.error(TAG, "Path already exists: " + path);
            }
        } else {
            Gdx.app.error(TAG, "Path does not exist: " + path);
        }
    }

    public void unloadAllAssets(){ // TO DO player always stay loaded
        assetManager.clear();
    }

    public boolean isFinished(){
        return assetManager.isFinished();
    }

    public void finishLoading(){
        assetManager.finishLoading();
    }

    public void update(){
        assetManager.update();
    }

    public void dispose(){
        assetManager.dispose();
    }
}
