package fr.audioengine.main;

import java.util.concurrent.TimeUnit;

import fr.audioengine.controler.MediaPlayerHome;
import fr.audioengine.model.Player;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

	
	//static private Home home = new Home();

	
	static private MediaPlayerHome mediaPlayer = new MediaPlayerHome();
	
	public static void main(String[] args) {
		launch(args);
	
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//mediaPlayer.getStage().show();
		Player p = new Player("trap.wav");
		p.play();
		TimeUnit.SECONDS.sleep(3);
		p.play();
		//Player p1 = new Player("Loop.wav");
		//p1.play();
	}

}
