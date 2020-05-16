package com.mrwalker.firstgame.Contact;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mrwalker.firstgame.ArtificialIntelligence.aiManager;
import com.mrwalker.firstgame.ArtificialIntelligence.calculations;
import com.mrwalker.firstgame.BodyID.BodyID;
import com.mrwalker.firstgame.BodyID.BodyType;
import com.mrwalker.firstgame.Entity.EntitiesManager;
import com.mrwalker.firstgame.Entity.Parts.Entity;

public class Contact implements ContactListener {

    EntitiesManager entities;
    aiManager aiManager;
    public Contact(EntitiesManager entities, aiManager aiManager) {
        this.entities = entities;
        this.aiManager = aiManager;
    }

    @Override
    public void beginContact(com.badlogic.gdx.physics.box2d.Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        BodyID entityIdentificationA = (BodyID) bodyA.getUserData();
        BodyID entityIdentificationB = (BodyID) bodyB.getUserData();
        if (entityIdentificationA.getType() == BodyType.Entity &&
            entityIdentificationB.getType() == BodyType.Entity){
            Entity entityA = entities.getByIdentification(entityIdentificationA);
            Entity entityB = entities.getByIdentification(entityIdentificationB);
            if (entityA.isPlayer()){
                aiManager.getAIForEntity(entityB).setAim(entityA);
            } else if (entityB.isPlayer()) {
                aiManager.getAIForEntity(entityA).setAim(entityB);
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
