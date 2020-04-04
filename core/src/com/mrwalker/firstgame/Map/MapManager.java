package com.mrwalker.firstgame.Map;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.mrwalker.firstgame.Utility.MapUtility;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;

public class MapManager {
    private static final String TAG = MapManager.class.getSimpleName();

    private World world;

    private Map map;

    private MapUtility utility;

    public MapManager(World world) {
        this.world = world;
        map = new Map(world);
        utility = new MapUtility();
        utility.loadAsset("main","map", "maps/basic-map.tmx");
        utility.finishLoading();

        loadMap("main");
    }

    public void loadMap(String name){
        map.loadMap(utility.getMapByName(name), "Main");
    }

    public void setView(OrthographicCamera camera){
        map.setView(camera);
    }

    public Position2 getPoint(String name, String layer){
        return map.getPoint(name, layer);
    }

    public Size2 getIsometricSize(){
        return map.getIsometricSize();
    }

    public Size2 getMapSize(){
        return map.getMapSize();
    }

    public void render(){
        map.render();
    }

    public void dispose(){
        map.dispose();
    }
}
