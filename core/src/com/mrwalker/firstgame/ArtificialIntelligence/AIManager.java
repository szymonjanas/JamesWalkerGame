package com.mrwalker.firstgame.ArtificialIntelligence;

import com.badlogic.gdx.Gdx;
import com.mrwalker.firstgame.Entity.Parts.Entity;

import java.util.ArrayList;

public class AIManager {
    private static final String TAG = AIManager.class.getSimpleName();

    private final ArrayList<AIEntity> entities;

    public AIManager(ArrayList<Entity> entities) {
        this.entities = new ArrayList<>();
        for (Entity entity: entities){
            if (!entity.isPlayer())
                this.entities.add(new AIEntity(entity));
        }
    }

    public AIEntity getAIForEntity(Entity entity){
        for (AIEntity ai: entities){
            if (ai.entity.equals(entity))
                return ai;
        }
        Gdx.app.error(TAG, "AI for entity does not exist: " + entity.getID().getID());
        return null;
    }

}
