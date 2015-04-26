package controller;

import java.util.List;
import com.badlogic.gdx.Input.Keys;

import model.player.Player;

public class PlayerManager {
	
	List<PlayerController> pControllers;
	
	public PlayerManager(List<Player> players){
		PlayerController p1 = new PlayerController(players.get(0),Keys.W ,Keys.S,Keys.A, Keys.D,Keys.Q,Keys.E);
		PlayerController p2 = new PlayerController(players.get(1), Keys.U, Keys.J, Keys.H, Keys.K, Keys.Y, Keys.I);
		PlayerController p3 = new PlayerController(players.get(2), Keys.UNKNOWN, Keys.UNKNOWN, Keys.UNKNOWN, Keys.UNKNOWN, Keys.UNKNOWN, Keys.UNKNOWN);
		PlayerController p4 = new PlayerController(players.get(3), Keys.UNKNOWN, Keys.UNKNOWN, Keys.UNKNOWN, Keys.UNKNOWN, Keys.UNKNOWN, Keys.UNKNOWN);
		
		pControllers.add(p1);
		pControllers.add(p2);
		pControllers.add(p3);
		pControllers.add(p4);
	}
	
	public void updatePlayers(){
		for(PlayerController p: pControllers){
			p.update();
		}
	}
	
	public List<PlayerController> getPlayerControllers(){
		return pControllers;
	}
}
