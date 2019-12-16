package fr.audioengine.main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import fr.audioengine.controler.MediaPlayerHome;
import fr.audioengine.model.Player;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	// static private Home home = new Home();

	static private MediaPlayerHome mediaPlayer = new MediaPlayerHome();

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//mediaPlayer.getStage().show();

		Player p = new Player("ambiance_4.wav",true);
		p.setVolume(-10.0f);

		Thread.sleep(2000);
		Player p2 = new Player("coin.wav",2);
		p2.setVolume(0.0F);

		Thread.sleep(3000);
		p2.restart();
		
		Player p3 = new Player("heal.wav",1);	
		
	}

}
