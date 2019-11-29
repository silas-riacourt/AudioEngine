package fr.audioengine.controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import fr.audioengine.model.AudioPlayer;
import fr.audioengine.model.Audio;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MediaPlayerHome implements Initializable {

	/** Stage de la fenêtre principal */
	private Stage stage;

	@FXML
	private Button PlayBtn;

	@FXML
	private Button PauseBtn;

	@FXML
	private Button StopBtn;

	@FXML
	private Button RestartBtn;

	@FXML
	private Label StatusLbl;

	@FXML
	private ListView<Audio> mediaList;

	ObservableList<Audio> items = FXCollections.observableArrayList();
	AudioPlayer audioPlayer;

	/**
	 * Constructeur qui charge la scene "menuprincipal" depuis le .fxml
	 */
	public MediaPlayerHome() {
		// On construit la stage
		stage = new Stage();
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/audioengine/view/mediaPlayer.fxml"));

			loader.setController(this);
			stage.setScene(new Scene(loader.load()));
			stage.setTitle("AudioEngine");
			// stage.setMaximized(true);
			stage.setMinHeight(600);
			stage.setMinWidth(800);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		items.add(new Audio("trap", "trap.wav"));
		items.add(new Audio("Trap Melody", "melody.wav"));
		items.add(new Audio("bass", "bass.wav"));
		items.add(new Audio("bass", "loop.wav"));
		mediaList.setCellFactory(new Callback<ListView<Audio>, ListCell<Audio>>() {

			@Override
			public ListCell<Audio> call(ListView<Audio> p) {

				ListCell<Audio> cell = new ListCell<Audio>() {

					protected void updateItem(Audio t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getName() + " (" + t.getPath()+")");
						}
					}

				};

				return cell;
			}
		});
		mediaList.setItems(items);
		// TODO Auto-generated method stub
		StatusLbl.setText("aucun son");
		try {
			audioPlayer = new AudioPlayer();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PlayBtn.setOnAction(e -> play());
		PauseBtn.setOnAction(e -> pause());
		StopBtn.setOnAction(e -> stop());
		RestartBtn.setOnAction(e -> restart());
		
		 mediaList.getSelectionModel().selectedItemProperty()
	        .addListener(new ChangeListener<Audio>() {
	          public void changed(ObservableValue<? extends Audio> observable,
	              Audio oldValue, Audio newValue) {
	            System.out.println("Choix "+newValue.getName()+" fichier : "+newValue.getPath()); 
	            AudioPlayer.setFilePath(newValue.getPath());
	          }
	        });

	}

	private void play() {
		try {
			if (audioPlayer.play()) {
				updateLabel("Lecture");
			} else {
				updateLabel("erreur de lecture");
			}

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void pause() {
		if (audioPlayer.pause()) {
			updateLabel("son mit en pause !");
		} else {
			updateLabel("impossible de mettre en pause");
		}

	}

	private void stop() {
		try {

			audioPlayer.stop();
			updateLabel("la lecture a été arrétée ! ");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void restart() {
		try {
			audioPlayer.restart();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void updateLabel(String t) {
		StatusLbl.setText(t);
	}

}
