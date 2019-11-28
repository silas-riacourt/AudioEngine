package view;

import javax.sound.sampled.LineUnavailableException;

import fr.audioengine.controler.Audio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Home extends Stage {

     
 	/**
 	 * Constructeur qui va iinstancier une fenetre d'aide
 	 */
 	public Home() {
 		this.setTitle("Home");

 		this.setResizable(true);
 		Scene laScene = new Scene(creerContenu(), 400, 600);
 		this.setScene(laScene);
 		this.sizeToScene();
 	}


 	/**
 	 * Definition des elements contenu dans la fenetre
 	 * 
 	 * @return Border Pane correspondant a la fenetre
 	 */
 	Parent creerContenu() {


 	     Button btn = new Button();
 	     btn.setText("Button");
 	     btn.setOnAction(new EventHandler<ActionEvent>() {

 	         @Override
 	         public void handle(ActionEvent event) {
					Audio.makeSound();


 	         }
 	     });
 	     
 	     StackPane root = new StackPane();
 	     root.getChildren().add(btn);
 	     return root;
 	}
}
