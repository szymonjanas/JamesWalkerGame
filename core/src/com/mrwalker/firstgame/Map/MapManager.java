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
import com.mrwalker.firstgame.Utility.MapUtility;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;

public class MapManager {
    private static final String TAG = MapManager.class.getSimpleName();

    private TiledMap currentMap = null;
    private IsometricTiledMapRenderer mapRenderer = null;
    private Size2 mapIsometricSize = new Size2(-1f, -1f);
    private String mapName = "Load map error!";
    private MapUtility utility;

    //lake body
    private BodyDef lakeBodyDef;
    private Body lakeBody;
    private FixtureDef lakeFixtureDef;
    private Fixture lakeFixture;
    private PolygonShape lakeShape;

    public MapManager(World world) {
        utility = new MapUtility();
        utility.loadAsset("main","map", "maps/basic-map.tmx");
        utility.finishLoading();

        currentMap = utility.getMapByName("main");

        mapName = currentMap.getProperties().get("name", String.class);

        mapIsometricSize.setHeight(currentMap.getProperties().get("isometricHeight", Integer.class));
        mapIsometricSize.setWidth(currentMap.getProperties().get("isometricWidth", Integer.class));

        Position2 lake_pos;
        System.out.println (currentMap.getLayers().get("Collision").getObjects().get(0).getClass().toString());
        PolygonMapObject polygonMapObject = (PolygonMapObject) currentMap.getLayers().get("Collision").getObjects().get(0);

        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        for (int i = 0; i < vertices.length; i+=2){
            Position2 pos = Converter.isometricToCartesian(new Position2(vertices[i], vertices[i+1]));
            vertices[i] = pos.getX();
            vertices[i+1] = pos.getY();
        }

        lakeBodyDef = new BodyDef();
        lakeBodyDef.type = BodyDef.BodyType.StaticBody;

        lakeBody = world.createBody(lakeBodyDef);

        lakeShape = new PolygonShape();
        lakeShape.set(vertices);
        lakeBody.createFixture(lakeShape, 1f);
        lakeShape.dispose();
    }

    public Size2 getIsometricSize(){
        return mapIsometricSize;
    }

    public Position2 getSpawnPoints(){
        return Converter.isometricToCartesian (
            new Position2(
                currentMap.getLayers().get("Spawn")
                        .getObjects().get("spawn")
                        .getProperties()
                        .get("x", Float.class),

                currentMap.getLayers().get("Spawn")
                    .getObjects().get("spawn")
                    .getProperties()
                    .get("y", Float.class)
                    )
        );
    }

    public Size2 getMapSize(){
        return new Size2(
                (float) currentMap.getProperties().get("width", Integer.class),
                (float) currentMap.getProperties().get("height", Integer.class)
        );
    }

    public void loadCurrentMap(){
        mapRenderer = new IsometricTiledMapRenderer(currentMap);
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
        currentMap.dispose();
        mapRenderer.dispose();
    }
}
