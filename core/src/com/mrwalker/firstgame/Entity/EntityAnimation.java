package com.mrwalker.firstgame.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.Utility.EntityUtility;
import com.mrwalker.firstgame.auxiliary.Position2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.mrwalker.firstgame.Entity.EntityAnimation.Movement.stay_DOWN;
import static com.mrwalker.firstgame.Entity.EntityAnimation.Movement.stay_LEFT;
import static com.mrwalker.firstgame.Entity.EntityAnimation.Movement.stay_RIGHT;
import static com.mrwalker.firstgame.Entity.EntityAnimation.Movement.stay_UP;
import static com.mrwalker.firstgame.Entity.EntityAnimation.Movement.walk_DOWN;
import static com.mrwalker.firstgame.Entity.EntityAnimation.Movement.walk_LEFT;
import static com.mrwalker.firstgame.Entity.EntityAnimation.Movement.walk_RIGHT;
import static com.mrwalker.firstgame.Entity.EntityAnimation.Movement.walk_UP;


public class EntityAnimation {

    private Map<Movement, Animation<TextureRegion>> walkAnimations;
    private float stateTime = 0f;

    private Movement movementType = stay_DOWN;

    public EntityAnimation() {
    }

    public enum Movement {
        walk_UP, stay_UP,
        walk_RIGHT, stay_RIGHT,
        walk_DOWN, stay_DOWN,
        walk_LEFT, stay_LEFT
    }

    public void loadAnimations(EntityUtility utility){
        walkAnimations = new HashMap<>();
        Texture walkTexture = utility.getTextureByName("player");
        TextureRegion[][] temp = TextureRegion.split(walkTexture,
                64, 64);

        walkAnimations.put(walk_UP, new Animation<TextureRegion>(1f/10f,
                    Arrays.copyOfRange(temp[8], 0, 9)));
        walkAnimations.put(walk_LEFT, new Animation<TextureRegion>(1f/10f,
                Arrays.copyOfRange(temp[9], 0, 9)));
        walkAnimations.put(walk_DOWN, new Animation<TextureRegion>(1f/10f,
                Arrays.copyOfRange(temp[10], 0, 9)));
        walkAnimations.put(walk_RIGHT, new Animation<TextureRegion>(1f/10f,
                Arrays.copyOfRange(temp[11], 0, 9)));

        walkAnimations.put(stay_UP, new Animation<TextureRegion>(1f/10f,
                Arrays.copyOfRange(temp[8], 0, 1)));
        walkAnimations.put(stay_LEFT, new Animation<TextureRegion>(1f/10f,
                Arrays.copyOfRange(temp[9], 0, 1)));
        walkAnimations.put(stay_DOWN, new Animation<TextureRegion>(1f/10f,
                Arrays.copyOfRange(temp[10], 0, 1)));
        walkAnimations.put(stay_RIGHT, new Animation<TextureRegion>(1f/10f,
                Arrays.copyOfRange(temp[11], 0, 1)));
    }

    public void move(float rotation, Vector2 force){
        System.out.println(force.toString());
        if (force.x == 0 && force.y == 0){
            if (rotation == 0){
                movementType = stay_UP;
            } else if (rotation == 90) {
                movementType = stay_RIGHT;
            } else if (rotation == 180) {
                movementType = stay_DOWN;
            } else if (rotation == 270) {
                movementType = stay_LEFT;
            }
        } else {
            if (rotation == 0){
                movementType = walk_UP;
            } else if (rotation == 90) {
                movementType = walk_RIGHT;
            } else if (rotation == 180) {
                movementType = walk_DOWN;
            } else if (rotation == 270) {
                movementType = walk_LEFT;
            }
        }
    }

    public void renderAnimation(SpriteBatch batch, Position2 position){
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimations.get(movementType).getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.getX()-32, position.getY()-8);
    }
}
