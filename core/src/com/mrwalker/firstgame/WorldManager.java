package com.mrwalker.firstgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;

public class WorldManager {
    private static final String TAG = WorldManager.class.getSimpleName();

    private static World worldInstance;

    public static void init(World world){
        worldInstance = world;
    }

    public static void init(){
        worldInstance = new World(new Vector2(0f, 0f), true);
    }

    public static World getWorld(){
        return worldInstance;
    }

    public static World loadWorldFromJsonFile(String jsonPath){
        FileHandle file = Gdx.files.local(jsonPath);
        String settings = file.readString();
        Json json = new Json();
        return json.fromJson(World.class, settings);
    }
    public static void saveCurrentWorldToJsonFile(World world, String jsonPath){
        if (world != null){
            Json json = new Json();
            String settings = json.toJson(world);
            FileHandle file = Gdx.files.local(jsonPath);
            file.writeString(settings, false);
        } else {
            Gdx.app.error(TAG, "World cannot be saved, because it does not exist!");
        }
    }
}
