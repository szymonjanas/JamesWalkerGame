package com.mrwalker.firstgame.Contact;

import com.badlogic.gdx.Gdx;
import com.mrwalker.firstgame.Entity.Entity;

public class EntityIdentification implements ObjectIdentification {
    private static final String TAG = EntityIdentification.class.getSimpleName();

    private int ID;
    private ObjectsTypes type;
    private String name;
    private Entity entity;

    public EntityIdentification(int ID, ObjectsTypes type, String name) {
        this.ID = ID;
        this.type = type;
        this.name = name;
    }

    public EntityIdentification() {
    }

    public boolean equals(EntityIdentification identification){
        return (this.ID == identification.ID) &&
                (this.type == identification.type) &&
                (this.name.equals(identification.name));
    }

    public int getID() {
        return ID;
    }

    public ObjectsTypes getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "[" + this.ID + ", " + this.name + ", " + this.type.toString() + "]";
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public static final class Builder{
        private int ID;
        private ObjectsTypes type = null;
        private String name = null;

        public Builder id(int ID){
            this.ID = ID;
            return this;
        }

        public Builder type(ObjectsTypes type){
            this.type = type;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public EntityIdentification build() {
            if (this.ID <=0 ){
                Gdx.app.error(TAG, "ID must be > 0, given ID: " + this.ID);
                return new EntityIdentification();
            }

            if (this.type == null){
                Gdx.app.error(TAG, "Type can not be null!");
                return new EntityIdentification();
            }

            if (this.name == null || this.name.length() == 0){
                Gdx.app.error(TAG, "Name can not be null or empty! ");
                return new EntityIdentification();
            }

            return new EntityIdentification(
                    this.ID,
                    this.type,
                    this.name
            );
        }
    }
}
