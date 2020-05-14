package com.mrwalker.firstgame.Entity.Models;

import com.badlogic.gdx.Gdx;
import com.mrwalker.firstgame.auxiliary.Position2;

import static com.mrwalker.firstgame.Entity.Models.Directions.Down;
import static com.mrwalker.firstgame.Entity.Models.Directions.DownLeft;
import static com.mrwalker.firstgame.Entity.Models.Directions.DownRight;
import static com.mrwalker.firstgame.Entity.Models.Directions.Left;
import static com.mrwalker.firstgame.Entity.Models.Directions.Right;
import static com.mrwalker.firstgame.Entity.Models.Directions.Up;
import static com.mrwalker.firstgame.Entity.Models.Directions.UpLeft;
import static com.mrwalker.firstgame.Entity.Models.Directions.UpRight;

public class EntityLocation {
    private static final String TAG = EntityLocation.class.getSimpleName();

    private short orientation; // 0-360 degrees
    private Directions directions;
    private Position2 position;

    public EntityLocation(short orientation, Directions directions, Position2 position) {
        this.orientation = orientation;
        this.directions = directions;
        this.position = position;
    }

    public short getOrientation() {
        return orientation;
    }

    public void setOrientation(short orientation) {
        this.orientation = orientation;
        setDirection(orientation);
    }

    public Directions getDirections() {
        return directions;
    }

    public void setDirections(Directions directions) {
        this.directions = directions;
    }

    public Position2 getPosition() {
        return position;
    }

    public void setPosition(Position2 position) {
        this.position = position;
    }

    public void setDirection(short orientation){
        switch (orientation){
            case 90:  directions = Up;          break;
            case 45:  directions = UpRight;     break;
            case 0:   directions = Right;       break;
            case 315: directions = DownRight;   break;
            case 270: directions = Down;        break;
            case 225: directions = DownLeft;    break;
            case 180: directions = Left;        break;
            case 135: directions = UpLeft;      break;
            default:
                Gdx.app.error(TAG, "Wrong rotation given: " + orientation);
        }
    }

}
