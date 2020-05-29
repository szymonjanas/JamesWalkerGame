package com.mrwalker.firstgame.Config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public final class Config {
    private static final String TAG = Config.class.getSimpleName();

    private static Json json = new Json();

    public static void save(String path, Object object){
        String config = json.toJson(object);
        FileHandle file = Gdx.files.local(path);
        file.writeString(config, false);
    }

    public static <T> T load(Class<T> type, String path){
        FileHandle file = Gdx.files.local(path);
        String config = file.readString();
        return json.fromJson(type, config);
    }

}
