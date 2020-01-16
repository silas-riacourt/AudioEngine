package fr.audioengine.main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import fr.audioengine.model.Ambiance;
import fr.audioengine.model.Audio;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application  {

	/* son pour les exemples */
	Audio coin = new Audio("coin.wav");
	Audio heal = new Audio("heal.wav");
	Audio gameover = new Audio("game_over.wav");
	
	Audio suspens_1 = new Audio("suspens_1.wav");
	Audio suspens_2 = new Audio("suspens_2.wav");

	Audio happy_1 = new Audio("happy_1.wav");
	Audio happy_2 = new Audio("happy_2.wav");
	
	
	/**
	 *
	 * Exemple 1 
	 * Avec une ambiance happy
	 */
	public void exemple_1() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		/* création de l'ambiance */
		Ambiance happy = Ambiance.happy;
		
		/* On y ajoute 2 sons */
		happy.addSong(happy_1);
		happy.addSong(happy_2);
		
		/* On récupère un son aléatoirement dans l'ambiance */
		Audio a = happy.getRandom();
		a.play(true);
		
		/* Après 2s */
		Thread.sleep(2000);

		/* On joue le son coin 4 fois */
		coin.play(2);
		coin.setVolume(0.9F);	
		
		/* Après 3s */
		Thread.sleep(2000);
		heal.play(false);
		


	}
	public void exemple_2() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		
		/* création de l'ambiance */
		Ambiance suspens = Ambiance.suspens;
		
		/* On y ajoute 2 sons */
		suspens.addSong(suspens_1);
		suspens.addSong(suspens_2);
		
		/* On récupère un son aléatoirement dans l'ambiance */
		Audio a = suspens.getRandom();
		a.play(true);
		
		Thread.sleep(3000);
		heal.play();
		
		Thread.sleep(3000);
		coin.play();	
		
		
		Thread.sleep(2000);
		a.stop();
		gameover.setVolume(0.9f);
		gameover.play();

		
	}
	
	/**
	 * Exemple 3
	 * 
	 * - Montre le fait de pouvoir jouer un son un certain temps
	 * 
	 */
	public void exemple_3() throws InterruptedException {
		
		suspens_2.timedPlay((float)3);
		
	}
	/**
	 * Exemple 4
	 * 
	 * - Montre le fait de pouvoir ajouter un effet de balance stereo
	 * @throws InterruptedException 
	 * 
	 */
	public void exemple_4() throws InterruptedException {
		
		happy_2.play();	
		happy_2.effetStereo(250);

		//happy_2.stopEffect();

		
	}
	public void start(Stage primaryStage) throws Exception {

		//exemple_1();
		//exemple_2();
		//exemple_3();
		exemple_4();
		
	}

}
