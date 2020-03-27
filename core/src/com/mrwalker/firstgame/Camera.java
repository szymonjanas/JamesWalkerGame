package com.mrwalker.firstgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;

public class Camera {
    private static String TAG = Camera.class.getSimpleName();

    private static Camera cameraInstance = null;
    private OrthographicCamera camera;

    private static class VIEWPORT{
        static Size2 viewPortSize;
        static Size2 halfViewPortSize;
    }

    private Camera(Position2 position) {
        camera = new OrthographicCamera();
        camera.position.x = position.getX();
        camera.position.y = position.getY();
        setViewPort(camera.viewportWidth, camera.viewportHeight);
    }

    public static void initInstance(){
        if (cameraInstance == null){
            cameraInstance = new Camera(new Position2(0f, 0f)); //default camera coordinates
        }
    }

    public static Camera getInstance() {
        if (cameraInstance == null){
            Gdx.app.error(TAG, "Camera has no instance!");
        }
        return cameraInstance;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setPosition(Position2 position) {
        camera.position.x = position.getX();
        camera.position.y = position.getY();
        camera.update();
    }

    public Position2 getPosition(){
        return new Position2(
                camera.position.x ,
                camera.position.y);
    }

    public void setViewPort(float width, float height){
        VIEWPORT.viewPortSize = new Size2(width, height);
        VIEWPORT.halfViewPortSize = new Size2(width/2, height/2);
        camera.setToOrtho(false, VIEWPORT.viewPortSize.getWidth(),
                VIEWPORT.viewPortSize.getHeight());
        camera.update();
    }

    public void update(){
        camera.update();
    }
} //singleton
