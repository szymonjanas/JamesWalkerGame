package com.mrwalker.firstgame.Entity.Models;

import com.mrwalker.firstgame.BodyID.BodyID;

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
}
