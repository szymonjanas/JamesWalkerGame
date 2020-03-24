package com.mrwalker.firstgame;

import com.badlogic.gdx.Game;
import com.mrwalker.firstgame.MainScreen.MainScreen;

public class GameMain extends Game {

	
	@Override
	public void create () {
		setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
