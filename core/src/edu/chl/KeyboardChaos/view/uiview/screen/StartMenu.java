package edu.chl.KeyboardChaos.view.uiview.screen;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import edu.chl.KeyboardChaos.util.KCConstants;
import edu.chl.KeyboardChaos.util.eventbus.BusEvent;
import edu.chl.KeyboardChaos.view.uiview.component.TextButton;

/*
 * This class represents the start menu in the GUI of KeyboardChaos
 */

public class StartMenu extends Screen{

	private final TextButton playButton;
	private final TextButton exitButton;

	public StartMenu(){
		super();
		this.playButton = new TextButton("Play", Gdx.graphics.getWidth()/2 - 250, 500, 500, 150, Color.valueOf("969696"), new BusEvent("ControllerSettings"), false);
		this.exitButton = new TextButton("Exit", Gdx.graphics.getWidth()/2 - 250, 300, 500, 150, Color.valueOf("969696"), new BusEvent("exit"), false);
		loadComponentList();
	}

	private void loadComponentList(){
		super.getComponents().add(this.playButton);
		super.getComponents().add(this.exitButton);
	}

}


