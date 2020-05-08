package com.mrwalker.firstgame.Entity.EntityBody;

import com.badlogic.gdx.math.Vector2;

public interface EntityBody {
    public void move(Vector2 force);
    public void update();

}
