package edu.chl.KeyboardChaos.view.battleStateView;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import edu.chl.KeyboardChaos.model.player.Player;
import edu.chl.KeyboardChaos.model.spell.Fireball;
import edu.chl.KeyboardChaos.model.spell.Iceball;
import edu.chl.KeyboardChaos.util.KCConstants;
import edu.chl.KeyboardChaos.util.MatchStats;
import edu.chl.KeyboardChaos.util.eventbus.BusEvent;
import edu.chl.KeyboardChaos.util.eventbus.BusEventHandler;
import edu.chl.KeyboardChaos.util.eventbus.EventBusService;
import edu.chl.KeyboardChaos.view.uiview.Font;
import edu.chl.KeyboardChaos.view.uiview.FontUtil;


/*
 * This class renders all the visual elements during a battle in KeyboardChaos
 */
public class BattleView implements BusEventHandler {
	private final SpriteBatch spriteBatch;
	private final ShapeRenderer shapeRenderer;
	private final OrthographicCamera worldCam, box2DCam;
	private Box2DDebugRenderer debugRenderer;
	
	private final FontUtil fontUtil;
	
	private final OrthogonalTiledMapRenderer mapRenderer;
	
	private final World world;
	
	private final PlayerView playerView;
	private final FireballView fireballView;
	private final IceballView iceballView;
	
	private Array<Fixture> fixtures;
	
	private MatchStats matchStats;
	private MatchStatsView msv;
	
	private boolean roundIsOver;
	
	public BattleView(Array<Fixture> fixtures, World world, TiledMap tileMap, MatchStats matchStats) {		
		this.fixtures = fixtures;
		this.world = world;
		this.matchStats = matchStats;
		
		this.roundIsOver = false;
		
		spriteBatch = new SpriteBatch();
		//debugRenderer = new Box2DDebugRenderer();
		mapRenderer = new OrthogonalTiledMapRenderer(tileMap);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		
		this.fontUtil = new FontUtil();
		
		playerView = new PlayerView(spriteBatch);
		fireballView = new FireballView(spriteBatch);
		iceballView = new IceballView(spriteBatch);
		
		// Cameras
		worldCam = new OrthographicCamera(KCConstants.GAME_WIDTH, KCConstants.GAME_HEIGHT);
		/*
		 * Translate is done since the camera before is centered at x = y = 0.
		 * We can move the camera so that the bottom left corner is at (0, 0).
		 */
		worldCam.translate(KCConstants.GAME_WIDTH / 2, KCConstants.GAME_HEIGHT / 2);
		worldCam.update();
		
		spriteBatch.setProjectionMatrix(worldCam.combined);
		shapeRenderer.setProjectionMatrix(worldCam.combined);
		
		mapRenderer.setView(worldCam);
		
		//debugRenderer = new Box2DDebugRenderer();
		
		box2DCam = new OrthographicCamera();
		box2DCam.setToOrtho(false, KCConstants.GAME_WIDTH, KCConstants.GAME_HEIGHT);
		box2DCam.combined.scale(KCConstants.PPM, KCConstants.PPM, 0);
		
		msv = new MatchStatsView(shapeRenderer, matchStats, KCConstants.GAME_WIDTH - 200, KCConstants.GAME_HEIGHT);
		EventBusService.getInstance().subscribe(this);
	}
	
	public void render() {
		// Clear screen
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Render the map
		mapRenderer.render();
		
		// Render objects on map
		spriteBatch.begin();
		
		for (Fixture f : fixtures) {
			if (f != null) {
				if (f.getUserData() instanceof Player) {
					playerView.render((Player)f.getUserData());
					
					// Could make a view only for spells (no specific spell needed)
				} else if (f.getUserData() instanceof Fireball) {
					fireballView.render((Fireball)f.getUserData());
				} else if (f.getUserData() instanceof Iceball) {
					iceballView.render((Iceball)f.getUserData());
				}
			}
		}
		
		spriteBatch.end();
		msv.render(spriteBatch);
		
		if (roundIsOver) {
			// Make screen darker
			shapeRenderer.begin();
			shapeRenderer.setColor(new Color(0, 0 , 0 , 0.5f)); 
			Gdx.gl.glEnable(GL20.GL_BLEND);
		    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.set(ShapeType.Filled);
			shapeRenderer.rect(0, 0, KCConstants.GAME_WIDTH, KCConstants.GAME_HEIGHT);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			shapeRenderer.end();
			
			// Print message on screen
			spriteBatch.begin();
			fontUtil.setFont(Font.SLABO_43);
			String text = "SOMEBODY WON\nPRESS ENTER TO RETURN TO MENU";
			// Ugly line of code
			fontUtil.getFont().draw(spriteBatch, text, fontUtil.getCenteredTextPos(text, 0, KCConstants.GAME_WIDTH), KCConstants.GAME_HEIGHT/2 + fontUtil.getTextHeight(text)/2);
			spriteBatch.end();
		}
		
		//debugRenderer.render(world, box2DCam.combined);
	}
	
	public void setFixtureArray(Array<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	@Override
	public void onEvent(BusEvent e) {
		if (e.getBusCommand().equals("round over"))
			this.roundIsOver = true;
		else if (e.getBusCommand().equals("play"))
			this.roundIsOver = false;
	}
}