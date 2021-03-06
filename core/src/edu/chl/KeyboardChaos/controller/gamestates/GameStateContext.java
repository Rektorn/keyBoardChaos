package edu.chl.KeyboardChaos.controller.gamestates;

import com.badlogic.gdx.Gdx;

import edu.chl.KeyboardChaos.util.eventbus.BusEvent;
import edu.chl.KeyboardChaos.util.eventbus.EventBusService;
import edu.chl.KeyboardChaos.util.eventbus.BusEventHandler;

/*
 * Class that manages what state the game is in
 * When an input is recieved to change from one game state to another, the classes switches gamestate
 */

public class GameStateContext implements BusEventHandler{
	
	/*private Stack<GameState> gameStates;
	private static final int PLAY_STATE = KCConstants.PLAY_STATE;*/
	
	private final GameState uiState;
	private final GameState battleState;
	private final GameState roundOverState;
	private GameState currentState;
	
	public GameStateContext(){
		uiState = new UIState();
		battleState = new BattleState();
		roundOverState = new RoundOverState();
		switchToUIState();
		EventBusService.getInstance().subscribe(this);
	}
	
	//public void changeState...

	public void update() {
		
		currentState.update();
		
	}

	public void handleInput() {
		currentState.handleInput();
		
	}
	
	public void render() {
		currentState.render();
		
	}
	
	public void switchToUIState(){
		switchToState(uiState);
	}
	
	public void switchToRoundOverState(){
		switchToState(roundOverState);
	}
	
	
	public void switchToBattleState(){
		switchToState(battleState);
	}
	
	private void switchToState(GameState state) {
		this.currentState = state;
		currentState.reset();
		Gdx.input.setInputProcessor(state.getInputProcessor());
	}

	@Override
	public void onEvent(BusEvent e) {
		String command = e.getBusCommand();
		if (command.equals("play")) {
			this.switchToBattleState();
		} else if (command.equals("exit")) {
			Gdx.app.exit();
		} else if (command.equals("menu")) {
			this.switchToUIState();
		}
	}
}
