package fr.audioengine.model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.MediaPlayer;

public class Player {
	
	Long currentFrame;
	
	Clip clip;
	
	String path;

	AudioInputStream audioInputStream;
	
	

	public Player(String path,Boolean loop) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		this.path = path;
		//this.audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/"+path));
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/"+path).getAbsoluteFile());
		this.clip = AudioSystem.getClip();
		this.clip.open(audioInputStream);
		if(loop) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}else {
			clip.start();
		}


	}
	public Player(String path,int loop) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		this.path = path;
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/"+path));
		this.clip = AudioSystem.getClip();
		this.clip.open(audioInputStream);
		this.clip.loop(loop-1);



	}
	public void play() {

			this.clip.start();


	}
	public void setVolume(float f) {

	            FloatControl volumeControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
	            volumeControl.setValue(f);

	}
	 
	public boolean pause() {
			this.clip.stop();
			return true;
		}


	public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.stop();
		this.clip = AudioSystem.getClip();
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/"+path));
		this.clip.open(audioInputStream);
		this.play();
	}

	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.clip.stop();
		this.clip.close();
	}


}
