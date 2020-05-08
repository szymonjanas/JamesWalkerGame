package com.mrwalker.firstgame.Entity.Animations;

import com.mrwalker.firstgame.Entity.Configs.EntityConfig;
import com.mrwalker.firstgame.Entity.Models.Behaviour;
import com.mrwalker.firstgame.Entity.Models.Directions;

import java.util.ArrayList;

public class EntityAnimationConfig implements EntityConfig {
    public String type;
    public int correctionFrameX;
    public int correctionFrameY;
    public int frameWidth;
    public int frameHeight;
    public int framesInRow;
    public int framesInCol;
    public float frameDurationForActions;
    public float frameDurationsForMovement;
    public ArrayList<Behaviour> behaviours;
    public ArrayList<Integer> behaviourFramePosition;
    public ArrayList<Directions> directions;
    public ArrayList<String> animationsPaths;
}
