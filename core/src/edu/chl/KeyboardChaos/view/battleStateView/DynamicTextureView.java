package edu.chl.KeyboardChaos.view.battleStateView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

import edu.chl.KeyboardChaos.util.DirectionVector;


/*
 * This abstract class is used to show the correct texture
 *  of an element depending on its direction, during a battle in KeyboardChaos
 */
public abstract class DynamicTextureView {
	
	private final Map<DirectionVector, Texture> map;
	private final ArrayList<Texture> textureList;
	
	private final File textureDirectory;
	
	public DynamicTextureView(String path){
		map = new HashMap<DirectionVector, Texture>();
		textureList = new ArrayList<Texture>();
		this.textureDirectory = new File(path);
		this.loadListWithTextures();
		this.fillMapWithTextures();
		
	}
	
/*
 * Method maps each Vector with a texture	
 */

	public void fillMapWithTextures(){
		map.put(new DirectionVector(0, 1), textureList.get(0));
		map.put(new DirectionVector(1, 1), textureList.get(1));
		map.put(new DirectionVector(1, 0), textureList.get(2));
		map.put(new DirectionVector(1, -1), textureList.get(3));
		map.put(new DirectionVector(0, -1), textureList.get(4));
		map.put(new DirectionVector(-1, -1), textureList.get(5));
		map.put(new DirectionVector(-1, 0), textureList.get(6));
		map.put(new DirectionVector(-1, 1), textureList.get(7));
	}
	
	
	protected ArrayList<Texture> getTextureList(){
		return textureList;
	}
	
	protected Texture getTextureForVector(DirectionVector v){
		return map.get(v);
	}
	/*
	 * method collects path name for all textures and puts them in textureList
	 */
	private void loadListWithTextures(){
		for(String s : textureDirectory.list()){
			textureList.add(new Texture(textureDirectory.getPath() + "/" + s));
		}
	}	
	
	
	
}
