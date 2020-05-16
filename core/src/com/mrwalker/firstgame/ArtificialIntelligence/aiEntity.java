package com.mrwalker.firstgame.ArtificialIntelligence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mrwalker.firstgame.Entity.Parts.Entity;
import com.mrwalker.firstgame.auxiliary.Position2;

public class aiEntity extends Thread{

    protected final Entity entity;

    private Entity aim = null;
    private Position2 aimPosition = null;
    private float distance = 10000;

    public aiEntity(Entity entity) {
        this.entity = entity;
        this.start();
    }
    public void run(){
        while (true){
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (aim != null){
                this.distance = calculations.getDistance(entity.getPosition(), aim.getPosition());
                System.out.println(this.distance);
                if (distance > 20){
                    entity.actions.startMovement();
                    short rotation = calculations.getOrientation(entity.getPosition(), aim.getPosition());
                    entity.actions.move(calculations.getForce(rotation), rotation);
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
