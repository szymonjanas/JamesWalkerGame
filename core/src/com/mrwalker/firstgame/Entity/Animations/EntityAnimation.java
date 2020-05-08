package com.mrwalker.firstgame.Entity.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.mrwalker.firstgame.Entity.Models.Behaviour;
import com.mrwalker.firstgame.Entity.Models.Directions;
import com.mrwalker.firstgame.Entity.Models.EntityState;
import com.mrwalker.firstgame.Utility.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.mrwalker.firstgame.auxiliary.Size2;

import static com.mrwalker.firstgame.Entity.Models.Directions.*;
import static com.mrwalker.firstgame.Entity.Models.Behaviour.*;

public class EntityAnimation implements Animations{
    private static final String TAG = EntityAnimation.class.getSimpleName();

//    private final Movements[] movementTypes = {
//            Stance, Running, MeleeSwing, Block, HitAndDie, CastSpell, ShootBow
//    };
//
//    private final Directions[] directionsTypes = {
//            Left, UpLeft, Up, UpRight, Right, DownRight, Down, DownLeft
//    };

    private final EntityState entityState;
    private ArrayList<Map<Directions, Map<Behaviour, Animation<TextureRegion>>>> animations = new ArrayList<>();

    private final EntityAnimationConfig config;


    private float stateTime = 0f;

//    private final int correctionFrameX = -64;
//    private final int correctionFrameY = -42;

//    private final Size2 frameSize = new Size2(128, 128);

    private ArrayList<Frame> frames;

//    private int[] framesFrom = {
//            0, 4, 12, 17, 19, 25, 29
//    };
//
//    private int[] framesTo = {
//            3, 11, 16, 18, 24, 28, 32
//    };

    public EntityAnimation(EntityState entityState, EntityAnimationConfig config) {
        this.config = config;
        this.entityState = entityState;
        this.frames = calculateFrames(config.behaviourFramePosition);
        for (String path: config.animationsPaths){
            addAnimations(Utility.getTexture(path));
        }
    }

    public ArrayList<Frame> calculateFrames(ArrayList<Integer> behaviourFramePosition){
        ArrayList<Frame> frames = new ArrayList<>();
        ArrayList<Integer> tempArr = new ArrayList<>();
        int sum = 0;
        tempArr.add(sum);
        for (Integer num: behaviourFramePosition){
            sum += num;
            tempArr.add(sum);
        }
        for (int i = 0; i < config.behaviours.size(); i+=2){
            frames.add(new Frame(tempArr.get(i), tempArr.get(i+1)-1));
        }
        return frames;
    }

    private void addAnimations(Texture texture){
        Map<Directions, Map<Behaviour, Animation<TextureRegion>>> animations_map = new HashMap<>();
        TextureRegion[][] temp = TextureRegion.split(texture,
                config.frameWidth, config.frameHeight);

        for (int dir = 0; dir < config.directions.size(); ++dir){
            Map<Behaviour, Animation<TextureRegion>> movingAnimations = new HashMap<>();
            for (int type = 0; type < config.behaviours.size(); ++type){
                if (type > 1){
                    movingAnimations.put(config.behaviours.get(type),
                            new Animation<TextureRegion>(
                                    config.frameDurationForActions,
                                    Arrays.copyOfRange(temp[dir],
                                            frames.get(type).getFrom(),
                                            frames.get(type).getTo())));

                } else {
                    movingAnimations.put(config.behaviours.get(type),
                            new Animation<TextureRegion>(
                                    config.frameDurationsForMovement,
                                    Arrays.copyOfRange(temp[dir],
                                            frames.get(type).getFrom(),
                                            frames.get(type).getTo())));
                }
            }
            animations_map.put(config.directions.get(dir), movingAnimations);
        }
        animations.add(animations_map);
    }

    public void render(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();

        if (    entityState.getBehaviour() == HitAndDie ||
                entityState.getBehaviour() == MeleeSwing ){
            for ( Map<Directions, Map<Behaviour, Animation<TextureRegion>>> object : animations){
                batch.draw(object.get(entityState.getLocation().getDirections()).get(
                        entityState.getBehaviour()).getKeyFrame(stateTime, false),
                        entityState.getLocation().getPosition().getX()+config.correctionFrameX,
                        entityState.getLocation().getPosition().getY()+config.correctionFrameY);
            }
            if (entityState.getBehaviour() == MeleeSwing) entityState.setBehaviour(Stance);

        } else {
            for ( Map<Directions, Map<Behaviour, Animation<TextureRegion>>> object : animations){
                batch.draw(object   .get(entityState.getLocation().getDirections())
                                .get(entityState.getBehaviour()).getKeyFrame(stateTime, true),
                        entityState.getLocation().getPosition().getX()+correctionFrameX,
                        entityState.getLocation().getPosition().getY()+correctionFrameY);
            }
        }
    }
}
