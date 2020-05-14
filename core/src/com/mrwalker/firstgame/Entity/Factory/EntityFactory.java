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
import com.mrwalker.firstgame.Entity.Parts.Animations.EntityAnimation;
import com.mrwalker.firstgame.Entity.Parts.Entity;
import com.mrwalker.firstgame.Entity.Parts.EntityBody.EntityBody;
import com.mrwalker.firstgame.auxiliary.Position2;

public class EntityFactory {
    private static final String TAG = EntityFactory.class.getSimpleName();

    public Entity getEntity(EntityType type){
        EntityState state;
        EntityBody body;
        EntityAnimation animation;

        switch (type){
            case PLAYER:
                state = new EntityState(
                            new EntityLocation((short) 270, Directions.Down, new Position2(0f, 0f)),
                            new EntityCondition(true, (short) 100, (short) 0, (byte) 100),
                            BodyID.createBodyID(BodyType.Entity),
                            Behaviour.Stance
                );
                EntityBodyConfig bodyConfig = new EntityBodyConfig();
                body = new EntityBody(bodyConfig, state);
                Json json = new Json();
                String anim = "";
                FileHandle file = Gdx.files.local("configs/player-animations-config.json");
                file.writeString(anim, true);
                EntityAnimationConfig config = json.fromJson(EntityAnimationConfig.class, anim);
                animation = new EntityAnimation(config, state);
                break;
            case ENEMY:
                state = new EntityState(
                        new EntityLocation((short) 270, Directions.Down, new Position2(100f, 100f)),
                        new EntityCondition(true, (short) 100, (short) 0, (byte) 100),
                        BodyID.createBodyID(BodyType.Entity),
                        Behaviour.Stance
                );
                EntityBodyConfig bodyConfig = new EntityBodyConfig();
                body = new EntityBody(bodyConfig, state);
                Json json = new Json();
                String anim = "";
                FileHandle file = Gdx.files.local("configs/npc-animations-config.json");
                file.writeString(anim, true);
                EntityAnimationConfig config = json.fromJson(EntityAnimationConfig.class, anim);
                animation = new EntityAnimation(config, state);
                break;
            default:
                Gdx.app.error(TAG, "Cannot match EntityType: " + type.toString());
        }
        return new Entity(state, body, animation);
    }

}
