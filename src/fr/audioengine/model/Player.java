package fr.audioengine.model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player {
	
	Long currentFrame;
	
	Clip clip;
	
	String path;

	AudioInputStream audioInputStream;

	public Player(String path,Boolean loop) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		this.path = path;
		clip = AudioSystem.getClip();
		audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/"+this.path).getAbsoluteFile());
		clip.open(audioInputStream);
		if(loop) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

	}

	public void play() {
			clip.start();


	}


	public boolean pause() {
			clip.stop();
			return true;
		}


	public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.stop();
		clip = AudioSystem.getClip();
		audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/"+this.path).getAbsoluteFile());
		clip.open(audioInputStream);
		this.play();
	}

	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		clip.stop();
		clip.close();
	}


}
