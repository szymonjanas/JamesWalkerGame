package com.mrwalker.firstgame.Entity.Models.Configs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mrwalker.firstgame.Entity.Models.Behaviour;
import com.mrwalker.firstgame.Entity.Models.Directions;

import java.util.ArrayList;

public class EntityAnimationConfig {
    public String type;
    public int correctionFrameX;
    public int correctionFrameY;
    public int frameWidth;
    public int frameHeight;
    public float frameDurationForActions;
    public float frameDurationsForMovement;
    public ArrayList<Behaviour> behaviours;
    public ArrayList<Integer> behaviourFramePosition;
    public ArrayList<Directions> directions;
    public ArrayList<String> animationsPaths;

    public static EntityAnimationConfig getFromFile(String path){
        FileHandle file = Gdx.files.local(path);
        Json json = new Json();
        return json.fromJson(EntityAnimationConfig.class, file.readString());
    }
}
