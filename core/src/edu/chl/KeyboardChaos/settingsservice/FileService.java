package edu.chl.KeyboardChaos.settingsservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * This class saves and loads the playersettings to a file on a local directory on the computer
 */

public class FileService {
	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	
	private List<String> settingsFileAddresses;
	
	public FileService(){
		settingsFileAddresses = new ArrayList<String>();
		for(int i = 1; i < 5; i++){
			settingsFileAddresses.add("./assets/player" + i + "settings.ser"); //H�r
		}
	}
	
	public void writePlayerSettings(int playerNumber, Object playerSettings){
		try{
			fileOutputStream = new FileOutputStream(settingsFileAddresses.get(playerNumber-1), false);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(playerSettings);
			objectOutputStream.close();
		}catch(FileNotFoundException e){
			File tmp = new File(settingsFileAddresses.get(playerNumber-1));
			tmp.getParentFile().mkdirs();
			writePlayerSettings(playerNumber, playerSettings);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Object readPlayerSettings(int playerNumber){
		try{
			fileInputStream = new FileInputStream(settingsFileAddresses.get(playerNumber-1));
			objectInputStream = new ObjectInputStream(fileInputStream);
			Object o = objectInputStream.readObject();
			objectInputStream.close();
			return o;
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
		
		
	}

}
