package com.mrwalker.firstgame.Contact;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mrwalker.firstgame.Entity.EntitiesManager;
import com.mrwalker.firstgame.Entity.Entity;

public class Contact implements ContactListener {

    EntitiesManager entities;

    public Contact(EntitiesManager entities) {
        this.entities = entities;
    }

    @Override
    public void beginContact(com.badlogic.gdx.physics.box2d.Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        EntityIdentification entityIdentificationA = (EntityIdentification) bodyA.getUserData();
        EntityIdentification entityIdentificationB = (EntityIdentification) bodyB.getUserData();
        if (entityIdentificationA.getType() == ObjectsTypes.Entity &&
            entityIdentificationB.getType() == ObjectsTypes.Entity){
            Entity entityA = entities.getByIdentification(entityIdentificationA);
            Entity entityB = entities.getByIdentification(entityIdentificationB);
            if (entityA.isPlayer()){
                entityB.attack(entityA);
            } else {
                entityA.attack(entityB);
            }
        }
    }

    @Override
    public void endContact(com.badlogic.gdx.physics.box2d.Contact contact) {

    }

    @Override
    public void preSolve(com.badlogic.gdx.physics.box2d.Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(com.badlogic.gdx.physics.box2d.Contact contact, ContactImpulse impulse) {

    }
}
