package com.mrwalker.firstgame.GameMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.mrwalker.firstgame.BodyID.BodyID;
import com.mrwalker.firstgame.BodyID.BodyType;
import com.mrwalker.firstgame.Camera;
import com.mrwalker.firstgame.Converter.Converter;
import com.mrwalker.firstgame.Utility.Utility;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;

import java.util.ArrayList;

public class GameMap {
    private static final String TAG = GameMap.class.getSimpleName();

    private String name = null;

    private TiledMap map = null;
    private String level;
    private IsometricTiledMapRenderer mapRenderer = null;
    private Size2 mapIsometricSize;
    private Size2 mapSize;


    private ArrayList<MapBody> mapBodies = new ArrayList<>();

    private MapObjects spawns;

    public GameMap(String path) {
        loadMap(Utility.getTiledMap(path));
    }

    public void loadMap(TiledMap map){
        this.map = map;
        name = this.map.getProperties().get("name", String.class);
        mapIsometricSize = new Size2(
                this.map.getProperties().get("isometricWidth", Integer.class),
                this.map.getProperties().get("isometricHeight", Integer.class));

        mapSize = new Size2(
                (float) this.map.getProperties().get("width", Integer.class),
                (float) this.map.getProperties().get("height", Integer.class)
        );

        createCollisionLayer();

        this.spawns = this.map.getLayers().get("Spawn").getObjects();

        mapRenderer = new IsometricTiledMapRenderer(this.map);
    }

    private void createCollisionLayer(){
        int count = this.map.getLayers().get("Collision").getObjects().getCount();
        MapLayer collision = this.map.getLayers().get("Collision");
        MapObjects collisionObjects = collision.getObjects();
        for (int objs = 0; objs< count; ++objs){
            PolygonMapObject object = (PolygonMapObject) collisionObjects.get(objs);
            float[] vertices = object.getPolygon().getTransformedVertices();
            if (vertices.length > 16){
                Gdx.app.error(TAG, "Vertices maximum value is 8 points! You have: " + vertices.length/2);
            }
            for (int i = 0; i < vertices.length; i+=2){
                Position2 pos = Converter.isometricToCartesian(new Position2(vertices[i], vertices[i+1]));
                vertices[i] = pos.getX();
                vertices[i+1] = pos.getY();
            }
            mapBodies.add(
                    new MapBody(vertices,
                            new BodyID(BodyType.Lake, (short) (2000+objs))));
        }
    }

    public Position2 getSpawnPoint(String name){
        return Converter.isometricToCartesian (
                new Position2(
                        spawns.get(name).getProperties().get("x", Float.class),
                        spawns.get(name).getProperties().get("y", Float.class)
                )
        );
    }

    public Size2 getIsometricSize(){
        return mapIsometricSize;
    }

    public Size2 getMapSize(){
        return mapSize;
    }

    public IsometricTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    public void setView(){
        mapRenderer.setView(Camera.getCamera());
        Camera.update();
    }

    public void render(){
        mapRenderer.render();
    }

    public void dispose(){
        map.dispose();
        mapRenderer.dispose();
    }
}
