package com.mrwalker.firstgame.Entity.Parts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.mrwalker.firstgame.Entity.Models.Behaviour;
import com.mrwalker.firstgame.Entity.Models.Configs.EntityAnimationConfig;
import com.mrwalker.firstgame.Entity.Models.Directions;
import com.mrwalker.firstgame.Entity.Models.EntityState;
import com.mrwalker.firstgame.Entity.Models.Frame;
import com.mrwalker.firstgame.Utility.Utility;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.mrwalker.firstgame.Entity.Models.Behaviour.*;

public class EntityAnimation {
    private static final String TAG = EntityAnimation.class.getSimpleName();

    private final EntityAnimationConfig config;
    private final EntityState entityState;

    private ArrayList<Map<Directions, Map<Behaviour, Animation<TextureRegion>>>> animations = new ArrayList<>();
    private float stateTime = 0f;
    private ArrayList<Frame> frames;

    public EntityAnimation(@NotNull EntityAnimationConfig config, @NotNull EntityState entityState) {
        this.config = config;
        this.entityState = entityState;
        this.frames = calculateFrames(config.behaviourFramePosition);
        for (String path: config.animationsPaths){
            addAnimations(Utility.getTexture(path));
        }
    }

    protected ArrayList<Frame> calculateFrames(@NotNull ArrayList<Integer> behaviourFramePosition){
        /*
            creates pattern for loading animations frames:
            method is getting Array of frames numbers which describe specific behaviours,
            and returns Array of specific frames' positions
         */
        ArrayList<Frame> frames = new ArrayList<>();
        ArrayList<Integer> tempArr = new ArrayList<>();
        int sum = 0;
        tempArr.add(sum);
        for (Integer num: behaviourFramePosition){
            sum += num;
            tempArr.add(sum);
        }
        for (int i = 0; i < config.behaviours.size(); ++i){
            frames.add(new Frame(tempArr.get(i), tempArr.get(i+1)-1));
        }
        return frames;
    }

    protected void addAnimations(Texture texture){
        /*
            Split texture, create Animations, and put them to Map.
         */
        Map<Directions, Map<Behaviour, Animation<TextureRegion>>> animations_map = new HashMap<>();
        TextureRegion[][] temp = TextureRegion.split(texture,
                config.frameWidth, config.frameHeight);

        for (int dir = 0; dir < config.directions.size(); ++dir){
            Map<Behaviour, Animation<TextureRegion>> movingAnimations = new HashMap<>();
            for (int type = 0; type < config.behaviours.size(); ++type){
                float frameDuration;
                if (type > 1)
                    frameDuration = config.frameDurationForActions;
                else
                    frameDuration = config.frameDurationsForMovement;

                movingAnimations.put(config.behaviours.get(type),
                        new Animation<TextureRegion>(
                                frameDuration,
                                Arrays.copyOfRange(temp[dir],
                                        frames.get(type).getFrom(),
                                        frames.get(type).getTo())));
            }
            animations_map.put(config.directions.get(dir), movingAnimations);
        }
        animations.add(animations_map);
    }

    public void render(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();

        boolean looping =   entityState.getBehaviour() != HitAndDie &&
                            entityState.getBehaviour() != MeleeSwing;

        for ( Map<Directions, Map<Behaviour, Animation<TextureRegion>>> object : animations)
            batch.draw(object.get(entityState.getLocation().getDirections()).get(
                    entityState.getBehaviour()).getKeyFrame(stateTime, looping),
                    entityState.getLocation().getPosition().getX() + config.correctionFrameX,
                    entityState.getLocation().getPosition().getY() + config.correctionFrameY);

        if (entityState.getBehaviour() == MeleeSwing)
            entityState.setBehaviour(Stance);
        }
}
