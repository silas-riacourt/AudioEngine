package fr.audioengine.main;

import fr.audioengine.controler.MediaPlayerHome;
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

		mediaPlayer.getStage().show();
	}

}
