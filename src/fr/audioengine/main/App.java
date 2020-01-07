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

		Player p = new Player("ambiance_4.wav",true);
		p.setVolume(0);
		p.play();
		p.setVolume(0.7f);

		Thread.sleep(2000);
		Player p2 = new Player("coin.wav",5);
		p2.play();
		p2.setVolume(0.9F);

		Thread.sleep(3000);
		p2.restart();
		
		Player p3 = new Player("heal.wav",1);
		p3.play();
		
	}

}
