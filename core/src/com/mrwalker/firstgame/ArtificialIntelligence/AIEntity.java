package com.mrwalker.firstgame.ArtificialIntelligence;

import com.mrwalker.firstgame.Entity.Parts.Entity;
import com.mrwalker.firstgame.auxiliary.Position2;

public class AIEntity extends Thread{

    protected final Entity entity;
    protected final AIObstacleAvoidance aiObstacleAvoidance;

    private Entity aim = null;
    private Position2 aimPosition = null;
    private float distance = 10000;

    public AIEntity(Entity entity) {
        this.entity = entity;
        this.aiObstacleAvoidance = new AIObstacleAvoidance(entity);
        this.start();
    }
    public void run(){
        while (true){
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (aim != null){
                this.distance = AIcalculations.getDistance(entity.getPosition(), aim.getPosition());
                if (distance > 40){ // distance between entities
                    entity.actions.startMovement();
                    aiObstacleAvoidance.bypassingAnObstacle(entity.getPosition(), aim.getPosition());
//                    short rotation = calculations.getRotation(entity.getPosition(), aim.getPosition());
//                    entity.actions.move(calculations.getForce(rotation), rotation);
                } else {
                    entity.actions.stopMovement();
                }
            } else if (aimPosition != null) {

            } else {

            }
        }
    }

    public void setAim(Entity aim) {
        this.aim = aim;
    }

    public void deleteAim(){
        this.aim = null;
    }

    public void setAimPosition(Position2 aimPosition) {
        this.aimPosition = aimPosition;
    }

    public void deletePosition(){
        this.aimPosition = null;
    }

}
