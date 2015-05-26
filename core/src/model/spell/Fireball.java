package model.spell;

import model.player.Player;

public class Fireball extends OffensiveSpell{
	
	private int damage, projectileSpeed;
	private Player originPlayer;
	private final float fireballRadius;
	
	public final static String DESCRIPTION = "It may look like a tennis ball, "
			+ "but it if you're not careful enough you will get it up your anus. "
			+ "You DO NOT want to mess with a person using this!";

	public final static String NAME = "Fireball";
	
	public Fireball(){
		super();
		this.damage = 10;
		this.projectileSpeed = 1;
		//this.originPlayer = originPlayer;
		fireballRadius = 3f;
	}
	
	/**
	 * @return The damage of the fireball
	 */
	public int getDamage(){
		return this.damage;
	}
	
	/**
	 * @return The movement speed of the fireball 
	 */
	public int getProjectileSpeed(){
		return this.projectileSpeed;
	}
	
	public float getFireballRadius(){
		return fireballRadius;
	}
	
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public String getName() {
		return NAME;
	}
}
