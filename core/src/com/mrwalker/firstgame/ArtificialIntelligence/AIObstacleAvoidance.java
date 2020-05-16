package com.mrwalker.firstgame.ArtificialIntelligence;

import com.badlogic.gdx.utils.TimeUtils;
import com.mrwalker.firstgame.Entity.Parts.Entity;
import com.mrwalker.firstgame.WorldManager;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Rotation;

import java.util.ArrayList;
import java.util.HashSet;

public class AIObstacleAvoidance {

    private long time;
    private Entity entity;

    public AIObstacleAvoidance(Entity entity) {
        this.entity = entity;
    }

    private void sleep(long millis){
        time = TimeUtils.millis();
        while(TimeUtils.timeSinceMillis(time) < millis){}
    }

    public void bypassingAnObstacle(Position2 from, Position2 to){
        HashSet<Short> whereToGo = whereToGo(from);
        HashSet<Short> obstacles = whereAreObstacles(whereToGo);
        for (Short obj: obstacles){
            goToPoint(from, getVirtualPoint(from, Rotation.opposite(obj)));
        }

        Rotation rotation = new Rotation(AIcalculations.getRotation(from, to));
        if (whereToGo.contains(rotation.getValue())){
//            System.out.println(rotation.getValue());
            goToPoint(from, to);
        } else {
            if (whereToGo.contains((short) (rotation.add((short) 45))))             goToPoint(from, getVirtualPoint(from, rotation.add((short) 45)));
            else if (whereToGo.contains((short) (rotation.add((short) 90))))        goToPoint(from, getVirtualPoint(from,rotation.add((short) 90)));
            else if (whereToGo.contains((short) (rotation.subtract((short) 45))))   goToPoint(from, getVirtualPoint(from, rotation.subtract((short) 45)));
            else if (whereToGo.contains((short) (rotation.subtract((short) 90))))   goToPoint(from, getVirtualPoint(from, rotation.subtract((short) 90)));
            else if (whereToGo.contains((short) (rotation.add((short) 135))))       goToPoint(from, getVirtualPoint(from, rotation.add((short) 135)));
            else if (whereToGo.contains((short) (rotation.subtract((short) 135))))  goToPoint(from, getVirtualPoint(from, rotation.subtract((short) 135)));
            else if (whereToGo.contains((short) (rotation.subtract((short) 180))))  goToPoint(from, getVirtualPoint(from, rotation.subtract((short) 180)));
        }
    }

    public void goToPoint(Position2 from, Position2 to){
        if (AIcalculations.getDistance(from, to) > 10){
            short rotation = AIcalculations.getRotation(from, to);
            entity.actions.move(AIcalculations.getForce(rotation), rotation);
            sleep(5);
        }
//        entity.actions.stance();
    }

    public HashSet<Short> whereToGo(Position2 position){
        HashSet<Short> toGo = new HashSet<>();
        ArrayList<Pair> rotations = new ArrayList<>();
        for (short i = 0; i < 360; ++i)
            rotations.add(new Pair(
                    i, WorldManager.isBodyOnPosition(getVirtualPoint(position, i))
            ));
        short start = -1;
        short stop = -1;
        short counter = 0;
        for (short i = 0; i < rotations.size(); ++i){
            if (!rotations.get(i).isFree){
                if (start == -1) start = i;
                stop = i;
                if (counter >= 35){
                    short num = AIcalculations.generalizeRotation((short) (start + (stop-start)));
                    toGo.add(num);
                    start = -1;
                    counter = 0;
                }
                ++counter;
            } else {
                counter = 0;
                stop = -1;
                start = -1;
            }
        }
//        System.out.println(toGo.toString());
        return toGo;
    }

    public HashSet<Short> whereAreObstacles(HashSet<Short> toGo){
        short[] rotations = {0, 45, 90, 135, 180, 225, 270, 315};
        HashSet<Short> obstacles = new HashSet<>();
        for (Short obj: rotations){
            if (!toGo.contains(obj)) obstacles.add(obj);
        }
        return obstacles;
    }

    private Position2 getVirtualPoint(Position2 position, short rotation){
        return new Position2(
                position.getX() + ((float) Math.cos(Math.toRadians(rotation)) * 25f),
                position.getY() + ((float) Math.sin(Math.toRadians(rotation)) * 25f)
        );
    }

    static class Pair{
        short deg;
        boolean isFree;

        public Pair(short deg, boolean isFree) {
            this.deg = deg;
            this.isFree = isFree;
        }
    }
}
