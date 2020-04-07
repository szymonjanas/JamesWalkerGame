package com.mrwalker.firstgame.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mrwalker.firstgame.Converter.Converter;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;

public class Map {
    private static final String TAG = MapManager.class.getSimpleName();

    private World world;

    private TiledMap map = null;
    private String level;
    private IsometricTiledMapRenderer mapRenderer = null;
    private Size2 mapIsometricSize;
    private Size2 mapSize;
    private String mapName = "Load map error!";

    //lake body
    private BodyDef bodyDef;
    private Body body;
    private FixtureDef fixtureDef;
    private Fixture fixture;
    private PolygonShape shape;

    public Map(World world) {
        this.world = world;
    }

    public void loadMap(TiledMap map, String level){
        this.map = map;
        this.level = level;
        mapName = this.map.getProperties().get("name", String.class);
        mapIsometricSize = new Size2(
                this.map.getProperties().get("isometricWidth", Integer.class),
                this.map.getProperties().get("isometricHeight", Integer.class));

        mapSize = new Size2(
                (float) this.map.getProperties().get("width", Integer.class),
                (float) this.map.getProperties().get("height", Integer.class)
        );

        PolygonMapObject polygonMapObject = (PolygonMapObject) this.map.getLayers().get(level + "Collision").getObjects().get(0);
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        for (int i = 0; i < vertices.length; i+=2){
            Position2 pos = Converter.isometricToCartesian(new Position2(vertices[i], vertices[i+1]));
            vertices[i] = pos.getX();
            vertices[i+1] = pos.getY();
        }

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);

        shape = new PolygonShape();
        shape.set(vertices);
        body.createFixture(shape, 1f);
        shape.dispose();

        mapRenderer = new IsometricTiledMapRenderer(this.map);
    }

    public Position2 getPoint(String name, String layer){
        return Converter.isometricToCartesian (
                new Position2(
                        map.getLayers().get(level+layer)
                                .getObjects().get(name)
                                .getProperties()
                                .get("x", Float.class),
                        map.getLayers().get(level+layer)
                                .getObjects().get(name)
                                .getProperties()
                                .get("y", Float.class)
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

    public void setView(OrthographicCamera camera){
        mapRenderer.setView(camera);
    }

    public void render(){
        mapRenderer.render();
    }

    public void dispose(){
        map.dispose();
        mapRenderer.dispose();
    }
}
