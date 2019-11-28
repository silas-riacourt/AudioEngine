package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
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
		this.setTitle("Hello World!");
 	}


 	/**
 	 * Definition des elements contenu dans la fenetre
 	 * 
 	 * @return Border Pane correspondant a la fenetre
 	 */
 	Parent creerContenu() {


 	     Button btn = new Button();
 	     btn.setText("Say 'Hello World'");
 	     btn.setOnAction(new EventHandler<ActionEvent>() {

 	         @Override
 	         public void handle(ActionEvent event) {
 	             System.out.println("Hello World!");
 	         }
 	     });
 	     
 	     StackPane root = new StackPane();
 	     root.getChildren().add(btn);
 	     return root;
 	}
}
