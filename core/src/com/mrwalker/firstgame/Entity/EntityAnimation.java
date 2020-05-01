package com.mrwalker.firstgame.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.utils.Json;
import com.mrwalker.firstgame.Entity.Configs.EntityAnimationConfig;
import com.mrwalker.firstgame.Utility.Utility;
import com.mrwalker.firstgame.auxiliary.Position2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.mrwalker.firstgame.Entity.EntityState.Directions.*;
import static com.mrwalker.firstgame.Entity.EntityState.Movements.*;
import com.mrwalker.firstgame.Entity.EntityState.Directions;
import com.mrwalker.firstgame.Entity.EntityState.Movements;
import com.mrwalker.firstgame.auxiliary.Size2;

public class EntityAnimation {
    private static final String TAG = EntityAnimation.class.getSimpleName();

    private final Movements[] movementTypes = {
            Stance, Running, MeleeSwing, Block, HitAndDie, CastSpell, ShootBow
    };

    private final Directions[] directionsTypes = {
            Left, UpLeft, Up, UpRight, Right, DownRight, Down, DownLeft
    };

    public EntityAnimationConfig config;


    private ArrayList<Map<Directions, Map<Movements, Animation<TextureRegion>>>> animations = new ArrayList<>();

    private float stateTime = 0f;

    private EntityState entityState;

    private int correctionFrameX = -64;
    private int correctionFrameY = -42;

    private Size2 frameSize = new Size2(128, 128);

    private final int[] framesFrom = {
            0, 4, 12, 17, 19, 25, 29
    };

    private final int[] framesTo = {
            3, 11, 16, 18, 24, 28, 32
    };


    public EntityAnimation(EntityState entityState, EntityAnimationConfig config) {
        this.config = config;
        this.entityState = entityState;
        loadAnimations();
    }

    public void loadAnimations(){
        for (String path: config.animationsPaths){
            addAnimations(Utility.getTexture(path));
        }
    }

    private void addAnimations(Texture texture){
        Map<Directions, Map<Movements, Animation<TextureRegion>>> animations_map = new HashMap<>();
        TextureRegion[][] temp = TextureRegion.split(texture,
                (int) frameSize.getWidth(), (int) frameSize.getHeight());

        for (int dir = 0; dir < directionsTypes.length; ++dir){
            Map<Movements, Animation<TextureRegion>> movingAnimations = new HashMap<>();
            for (int type = 0; type < movementTypes.length; ++type){
                movingAnimations.put(movementTypes[type],
                        new Animation<TextureRegion>(
                                entityState.frameDuration,
                                Arrays.copyOfRange(temp[dir], framesFrom[type], framesTo[type])));
            }
            animations_map.put(directionsTypes[dir], movingAnimations);
        }
        animations.add(animations_map);
    }


    public void update(){
        Directions direction = null;
        switch (entityState.rotation){
            case 0: direction = Up; break;
            case 45: direction = UpRight; break;
            case 90: direction = Right; break;
            case 135: direction = DownRight; break;
            case 180: direction = Down; break;
            case 225: direction = DownLeft; break;
            case 270: direction = Left; break;
            case 315: direction = UpLeft; break;
            default:
                Gdx.app.error(TAG, "Wrong rotation given: " + entityState.rotation);
        }
        entityState.direction = direction;
    }

    private int hitanddie = 0;

    public void renderAnimation(SpriteBatch batch){
        update();
        stateTime += Gdx.graphics.getDeltaTime();
        if (entityState.movement == HitAndDie){
            for ( Map<Directions, Map<Movements, Animation<TextureRegion>>> object : animations){
                batch.draw(object.get(entityState.direction).get(entityState.movement).getKeyFrame(0.6666666f, false),
                        entityState.position.getX()+correctionFrameX, entityState.position.getY()+correctionFrameY);
            }
            return;
        }
        for ( Map<Directions, Map<Movements, Animation<TextureRegion>>> object : animations){
            batch.draw(object.get(entityState.direction).get(entityState.movement).getKeyFrame(stateTime, true),
                    entityState.position.getX()+correctionFrameX, entityState.position.getY()+correctionFrameY);
        }
    }


}
