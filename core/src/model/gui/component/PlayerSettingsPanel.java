package model.gui.component;
import java.awt.Color;

import controller.eventbus.BusEvent;


public class PlayerSettingsPanel extends Component {

	private int posX, posY;
	private final int height;
	private final int width;
	private final Color color;
	
	private TextButton upButton;
	private TextButton downButton;
	private TextButton rightButton;
	private TextButton leftButton;


	/*
	 * Creates a playerSettingsPanel containing controller settings for the user
	 * @param posX determines the X-position of the button
	 * @param posY determines the Y-position of the button
	 */

	public PlayerSettingsPanel (int posX, int posY, BusEvent event){
		super(event);

		this.posX = posX;
		this.posY = posY;
		height = 600;
		width = 150;
		this.color = new Color(152,152,152);
		loadTextButtons();

	}
	/*
	 * this method creates one controllerSettingsButton
	 * @param posX determines the X-position of the button inside the PlayerSettingsPanel
	 * @param posY determines the Y-position of the button inside the PlayerSettingsPanel
	 */
	private TextButton addControllerSettingsButton(int posX, int posY, String startText, BusEvent event){
		return new TextButton(startText, (this.posX + posX), (this.posY + posY), 30, 30, event, true);
	}
	
	private void loadTextButtons(){
		upButton = addControllerSettingsButton(75, -100, "W", new BusEvent("chosenUpButton"));
		downButton = addControllerSettingsButton(75, -135, "S", new BusEvent("chosenDownButton"));
		rightButton = addControllerSettingsButton(110, -135, "D", new BusEvent("chosenRightButton"));
		leftButton = addControllerSettingsButton(40, -135, "A", new BusEvent("chosenLeftButton"));
	}

	public int getX(){
		return this.posX;	
	}

	public int getY(){
		return this.posY;	
	}
	
	public Color getPanelColor(){
		return this.color;
	}
	
	

}
