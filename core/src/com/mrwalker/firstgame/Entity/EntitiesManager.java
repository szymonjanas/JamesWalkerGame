package com.mrwalker.firstgame.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mrwalker.firstgame.BodyID.BodyID;
import com.mrwalker.firstgame.Entity.Parts.Entity;

import java.util.ArrayList;

public class EntitiesManager {
    private static final String TAG = EntitiesManager.class.getSimpleName();

    private final ArrayList<Entity> entities = new ArrayList<>();

    public void add(Entity entity){
        if (!contains(entity)){
            entities.add(entity);
        } else {
            Gdx.app.error(TAG, "Entity is already in bean!");
        }
    }

    public void remove(Entity entity){
        if (contains(entity)){
            entities.remove(entity);
        } else {
            Gdx.app.error(TAG, "Entity already removed!");
        }
    }

    public Entity getByID(int ID){
        for (Entity entity: entities){
            if (ID == entity.getID().getID())
                return entity;
        }
        Gdx.app.error(TAG, "Entity with given ID not found: " + ID);
        return null;
    }

    public Entity getByIdentification(BodyID bodyID){
        for (Entity entity: entities){
            if (entity.getID().equals(bodyID))
                return entity;
        }
        Gdx.app.error(TAG, "Entity not found! Wrong identification given!");
        return null;
    }

    public boolean contains(Entity entity){
        return entities.contains(entity);
    }

    public void render(SpriteBatch batch){
        for (Entity entity: entities){
            entity.render(batch);
        }
    }

    public void dispose(){
        for (Entity entity: entities){
            entity.dispose();
        }
    }
}
