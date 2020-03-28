package com.mrwalker.firstgame.Converter;

import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.Config.Config;
import com.mrwalker.firstgame.auxiliary.Position2;

public class Converter {

    private static final int tileWidth = Config.tileWidth, tileHeight = Config.tileHeight;

    public static Position2 cartesianToIsometric(Position2 position){
        Vector2 point = new Vector2(position.getX(), position.getY());
        point.x /= tileWidth;
        point.y = (point.y - tileHeight / 2f) / tileHeight + point.x;
        point.x -= point.y - point.x;
        return new Position2(point.x * tileWidth / 2f, point.y * tileHeight);
    }

    public static Position2 isometricToCartesian(Position2 position){
        Vector2 point = new Vector2(position.getX(), position.getY());
        point.x = position.getY() + position.getX();
        point.y = (position.getY() - position.getX()) / 2f + 16;
        return new Position2(point.x, point.y);
    }
}
