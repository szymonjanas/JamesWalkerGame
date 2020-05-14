package com.mrwalker.firstgame.Entity.Models;

import com.mrwalker.firstgame.BodyID.BodyID;
import com.mrwalker.firstgame.BodyID.BodyType;
import com.mrwalker.firstgame.auxiliary.Position2;

public class EntityState {

    private final EntityLocation location;
    private final EntityCondition condition;
    private final BodyID ID;
    private Behaviour behaviour;

    public EntityState(EntityLocation location, EntityCondition condition, BodyID ID, Behaviour behaviour) {
        this.location = location;
        this.condition = condition;
        this.ID = ID;
        this.behaviour = behaviour;
    }

    public EntityLocation getLocation() {
        return location;
    }

    public EntityCondition getCondition() {
        return condition;
    }

    public BodyID getID() {
        return ID;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }


    public static EntityState getDefault(){
        // FOR TESTING
        return new EntityState(
                new EntityLocation((short) 270, Directions.Down, new Position2(0f, 0f)),
                new EntityCondition(true, (short) 100, (short) 0, (byte) 100),
                BodyID.createBodyID(BodyType.Entity),
                Behaviour.Stance
        );
    }
}
