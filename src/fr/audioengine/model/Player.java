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

	public Player(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		this.path = path;
		clip = AudioSystem.getClip();
		audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/"+this.path).getAbsoluteFile());
		clip.open(audioInputStream);

	}

	public void play() {
			clip.start();

	}


	public boolean pause() {

			clip.stop();
			return true;
		}


	public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		clip.stop();
		clip.close();
		resetAudioStream();
		currentFrame = 0L;
		clip.setMicrosecondPosition(0);
		this.play();
	}

	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		currentFrame = 0L;
		clip.stop();
		clip.close();
	}



	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/"+this.path).getAbsoluteFile());
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
