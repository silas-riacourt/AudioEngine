package fr.audioengine.model;

import java.util.ArrayList;
import java.util.Random;


/**
 * @author silas
 * 
 * Enum pour gérer les ambiances sonores
 *
 */
public enum Ambiance {

	suspens("suspens"), happy("happy"),end("end"),begin("début");

	private String name = "";
	private ArrayList<Audio> songs = new ArrayList<Audio>();

	// Constructeur
	Ambiance(String name) {
		this.name = name;
	}

	public void addSong(Audio a) {
		if (!songs.contains(a)) {
			this.songs.add(a);
		}

	}
	public Audio getRandom() {
		 return songs.get(new Random().nextInt(songs.size()));
	}
	public String toString() {
		return name;
	}
}