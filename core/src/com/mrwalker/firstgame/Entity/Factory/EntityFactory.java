package com.mrwalker.firstgame.Entity.Factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mrwalker.firstgame.BodyID.BodyID;
import com.mrwalker.firstgame.BodyID.BodyType;
import com.mrwalker.firstgame.Entity.Models.EntityState;
import com.mrwalker.firstgame.Entity.Models.Behaviour;
import com.mrwalker.firstgame.Entity.Models.Configs.EntityAnimationConfig;
import com.mrwalker.firstgame.Entity.Models.Configs.EntityBodyConfig;
import com.mrwalker.firstgame.Entity.Models.Directions;
import com.mrwalker.firstgame.Entity.Models.EntityCondition;
import com.mrwalker.firstgame.Entity.Models.EntityLocation;
import com.mrwalker.firstgame.Entity.Parts.EntityAnimation;
import com.mrwalker.firstgame.Entity.Parts.Entity;
import com.mrwalker.firstgame.Entity.Parts.EntityBody;
import com.mrwalker.firstgame.auxiliary.Position2;

public class EntityFactory {
    private static final String TAG = EntityFactory.class.getSimpleName();

    public static Entity getEntity(EntityType type){
        EntityState state;
        EntityBody body;
        EntityAnimation animation;

        EntityBodyConfig bodyConfig;
        Json json;
        String anim = "";
        FileHandle file;
        EntityAnimationConfig config;

        switch (type){
            case PLAYER:
                state = new EntityState(
                            new EntityLocation((short) 270, Directions.Down, new Position2(0f, 0f)),
                            new EntityCondition(true, (short) 100, (short) 0, (byte) 100),
                            BodyID.createBodyID(BodyType.Entity),
                            Behaviour.Stance
                );
                bodyConfig = new EntityBodyConfig();
                body = new EntityBody(bodyConfig, state);
                config = EntityAnimationConfig.getFromFile("configs/player-animations-config.json");
                animation = new EntityAnimation(config, state);
                return new Entity(state, body, animation);
            case ENEMY:
                state = new EntityState(
                        new EntityLocation((short) 270, Directions.Down, new Position2(100f, 100f)),
                        new EntityCondition(true, (short) 100, (short) 0, (byte) 100),
                        BodyID.createBodyID(BodyType.Entity),
                        Behaviour.Stance
                );
                bodyConfig = new EntityBodyConfig();
                body = new EntityBody(bodyConfig, state);
                config = EntityAnimationConfig.getFromFile("configs/npc-animations-config.json");
                animation = new EntityAnimation(config, state);
                return new Entity(state, body, animation);
            default:
                Gdx.app.error(TAG, "Cannot match EntityType: " + type.toString());
        }
        return null;
    }

}
