package com.mrwalker.firstgame.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Utility {
    protected static final String TAG = Utility.class.getSimpleName();

    private static final AssetManager assetManager = new AssetManager();
    private static final InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();


    public static void init(){
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    }

    public static void loadAssets(UtilityConfig config){
        for (Map.Entry<String,String> entry : config.assertPaths.entrySet()){
            loadAsset(entry.getValue(), entry.getKey());
        }
    }

    public static void loadAsset(String type, String path){
        /*
           Load to Utility:
            assets name types:
            - tiledmap
            - texture
         */
        if (filePathResolver.resolve(path).exists()){
            if (!assetManager.isLoaded(path)){
                switch (type){
                    case "tiledmap":
                        assetManager.load(path, TiledMap.class);
                        break;
                    case "texture":
                        assetManager.load(path, Texture.class);
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

    public static TiledMap getTiledMap(String path){
        if (assetManager.isLoaded(path)){
            return assetManager.get(path);
        } else {
            Gdx.app.error(TAG, "File not loaded: " + path);
        }
        return null;
    }

    public static Texture getTexture(String path){
        if (assetManager.isLoaded(path)){
            return assetManager.get(path);
        } else {
            Gdx.app.error(TAG, "File not loaded: " + path);
        }
        return null;
    }

    public static void unloadAllAssets(){ // TO DO player always stay loaded
        assetManager.clear();
    }

    public static float getLoadingProgress(){
        return assetManager.getProgress();
    }

    public static boolean isFinished(){
        return assetManager.isFinished();
    }

    public static void finishLoading(){
        assetManager.finishLoading();
    }

    public static void update(){
        assetManager.update();
    }

    public static void dispose(){
        assetManager.dispose();
    }
}
