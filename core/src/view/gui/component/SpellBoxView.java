package view.gui.component;

import java.util.HashMap;
import java.util.Map;

import model.gui.component.SpellPanel;
import model.gui.component.SpellBox;
import model.spell.Fireball;
import model.spell.Spell;
import model.spell.Spell.SpellEnum;
import model.spell.Water;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SpellBoxView {
	private final SpriteBatch batch;
	private final ShapeRenderer shapeRenderer;
	private final int lineWidth;
	
	private final Map<SpellEnum, Texture> textureMap;
	
	public SpellBoxView(SpriteBatch batch, ShapeRenderer shapeRenderer) {
		this.batch = batch;
		this.shapeRenderer = shapeRenderer;
		
		textureMap = new HashMap<SpellEnum, Texture>();
		loadTextures();
		
		this.lineWidth = 2;
	}
	
	private void loadTextures() {
		this.textureMap.put(SpellEnum.FIREBALL, createTexture("assets/Fireball.png"));
		this.textureMap.put(SpellEnum.WATER, createTexture("assets/Water.png"));
	}
	
	private Texture createTexture(String path) {
		return new Texture(Gdx.files.internal(path));
	}
	
	public void render(SpellBox box) {
		// Draw background 
		shapeRenderer.begin();
		// TODO: Color coverter
		shapeRenderer.setColor(new Color(box.getBackgroundColor().getRed()/255f, box.getBackgroundColor().getGreen()/255f, box.getBackgroundColor().getBlue()/255f, box.getBackgroundColor().getAlpha()));
		shapeRenderer.set(ShapeType.Filled);
		shapeRenderer.rect(box.getPosX(), box.getPosY(), box.getWidth(), box.getHeight());
		if (box.isSelected()) {
			shapeRenderer.setColor(new Color(box.getHighlightColor().getRed()/255f, box.getHighlightColor().getGreen()/255f, box.getHighlightColor().getBlue()/255f, box.getHighlightColor().getAlpha()));
			Gdx.gl20.glLineWidth(lineWidth);
			shapeRenderer.set(ShapeType.Line);
			shapeRenderer.rect(box.getPosX() + lineWidth/2, box.getPosY() - lineWidth/2, box.getWidth() - lineWidth/2, box.getHeight() + lineWidth/2);
		}
		shapeRenderer.end();
		Gdx.gl20.glLineWidth(1);

		// Draw the texture of selected spell
		batch.begin();
		Texture texture = this.textureMap.get(box.getSpell());
		batch.draw(texture, box.getPosX(), box.getPosY(), box.getWidth(), box.getHeight());
		batch.end();
	}
}
