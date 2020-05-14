package com.mrwalker.firstgame.BodyID;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;

public class BodyID {
    private static final String TAG = BodyID.class.getSimpleName();

    private static ArrayList<BodyID> ids = new ArrayList<>();

    private final BodyType type;

    protected static final short idMaxSize = 32766;
    private final short ID;

    public BodyID(BodyType type, short id) {
        this.type = type;
        ID = id;
    }

    public BodyType getType() {
        return type;
    }

    public short getID() {
        return ID;
    }

    public boolean equals(BodyID bodyID){
        return  (bodyID.getID() == this.getID()) &&
                (bodyID.getType() == this.getType());
    }

    public static BodyID createBodyID(BodyType type){
        short range;
        short correction;
        switch (type){
            case Entity:
                range = 5000;
                correction = 0;
                break;
            case Lake:
                range = 10000;
                correction = 5001;
            break;
            default:
                range = 0;
                correction = 0;
        }

        BodyID body = new BodyID( type, createID(range, correction));
        addBody(body);
        return body;
    }

    protected static short createID(short range, short correction){
        Random random = new Random(range);
        short number;
        do {
            number = (short) (random.nextInt() + correction);
        } while (isBodyIDPresent(number));
        return number;
    }

    public static boolean isBodyIDPresent(short id){
        for (BodyID body: ids){
            if (body.getID() == id) return true;
        }
        return false;
    }

    public static boolean isBodyIDPresent(BodyID bodyID){
        return ids.contains(bodyID);
    }

    public static void addBody(BodyID bodyID){
        if (isBodyIDPresent(bodyID))
            Gdx.app.error(TAG,
                    "Cannot add body to map, body with ID already exist: [" +
                            bodyID.getID() + ", " + bodyID.getType().toString() + " ]" );
        else
            ids.add(bodyID);
    }

}
