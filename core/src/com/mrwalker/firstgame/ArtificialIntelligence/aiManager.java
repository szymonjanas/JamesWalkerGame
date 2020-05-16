package com.mrwalker.firstgame.ArtificialIntelligence;

import com.badlogic.gdx.Gdx;
import com.mrwalker.firstgame.Entity.Parts.Entity;

import java.util.ArrayList;

public class aiManager {
    private static final String TAG = aiManager.class.getSimpleName();

    private final ArrayList<aiEntity> entities;

    public aiManager(ArrayList<Entity> entities) {
        this.entities = new ArrayList<>();
        for (Entity entity: entities){
            if (!entity.isPlayer())
                this.entities.add(new aiEntity(entity));
        }
    }

    public aiEntity getAIForEntity(Entity entity){
        for (aiEntity ai: entities){
            if (ai.entity.equals(entity))
                return ai;
        }
        Gdx.app.error(TAG, "AI for entity does not exist: " + entity.getID().getID());
        return null;
    }

}
