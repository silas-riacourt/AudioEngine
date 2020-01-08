package fr.audioengine.main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import fr.audioengine.model.Player;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	// static private Home home = new Home();

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//mediaPlayer.getStage().show();

		/* Chargements des sons */
		/* On charge le son d'ambiance avec true pour la lecture en boucle */
		Player p = new Player("ambiance_4.wav");
		Player coin = new Player("coin.wav");
		Player heal = new Player("heal.wav");
		
		
		
		/* On le démarre */
		p.play(true);
	
		/* On change le volume du son */
		p.setVolume(1.0f);

		/* Après 2s */
		Thread.sleep(2000);
		
		
		/* On joue le son coin 4 fois */

		coin.play(4);
		coin.setVolume(0.9F);

		/* Après 3s */
		Thread.sleep(3000);
		
		/* on replay le son coins */
		coin.restart();
		
		/* On joue le son heal */
		heal.play(false);
		
	}

}
