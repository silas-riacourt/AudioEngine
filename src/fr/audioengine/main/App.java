package fr.audioengine.main;

import javafx.application.Application;
import javafx.stage.Stage;

import view.Home;

public class App extends Application{

	
	static private Home home = new Home();
	
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		home.show();
	}

}
