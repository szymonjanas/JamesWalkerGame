package com.mrwalker.firstgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;

public class Camera {
    private static final String TAG = Camera.class.getSimpleName();

    private static final OrthographicCamera camera = new OrthographicCamera();;

    private static class VIEWPORT{
        static Size2 viewPortSize;
        static Size2 halfViewPortSize;
    }

    public static void init() {
        camera.position.x = 0f;
        camera.position.y = 0f;
        setViewPort(camera.viewportWidth, camera.viewportHeight);
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }

    public static void setPosition(Position2 position) {
        camera.position.x = position.getX();
        camera.position.y = position.getY();
        camera.update();
    }

    public static Position2 getPosition(){
        return new Position2(
                camera.position.x ,
                camera.position.y);
    }

    public static void setViewPort(float width, float height){
        VIEWPORT.viewPortSize = new Size2(width, height);
        VIEWPORT.halfViewPortSize = new Size2(width/2, height/2);
        camera.setToOrtho(false, VIEWPORT.viewPortSize.getWidth(),
                VIEWPORT.viewPortSize.getHeight());
        camera.update();
    }

    public static void update(){
        camera.update();
    }
}
