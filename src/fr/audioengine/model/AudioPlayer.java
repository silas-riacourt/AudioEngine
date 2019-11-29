package fr.audioengine.model;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	Long currentFrame;
	Clip clip;

	String status = "first";

	AudioInputStream audioInputStream;
	static String filePath = "melody.wav";

	// constructor to initialize streams and clip
	public AudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// create clip reference
		clip = AudioSystem.getClip();

	}

	// Method to play the audio
	public boolean play() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		if (status.equals("first")) {
			restart();
			return true;
		} else {

			// start the clip
			clip.start();

			status = "play";
			return true;
		}
	}

	// Method to pause the audio
	public boolean pause() {
		if (status.equals("paused")) {
			System.out.println("audio is already paused");
			// MediaPlayerHome.updateLabel("audio is already paused");
			return false;
		} else {

			this.currentFrame = this.clip.getMicrosecondPosition();
			clip.stop();
			status = "paused";
			return true;
		}

	}

	// Method to resume the audio
	public boolean resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (status.equals("play")) {
			return false;
		} else {

			clip.close();
			resetAudioStream();
			clip.setMicrosecondPosition(currentFrame);
			this.play();
			return true;
		}

	}

	// Method to restart the audio
	public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		clip.stop();
		clip.close();
		resetAudioStream();
		currentFrame = 0L;
		clip.setMicrosecondPosition(0);
		this.play();
	}

	// Method to stop the audio
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		currentFrame = 0L;
		clip.stop();
		clip.close();
		status = "first";
	}

	// Method to jump over a specific part
	public void jump(long c) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (c > 0 && c < clip.getMicrosecondLength()) {
			clip.stop();
			clip.close();
			resetAudioStream();
			currentFrame = c;
			clip.setMicrosecondPosition(c);
			this.play();
		}
	}

	// Method to reset audio stream
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		System.out.println("test");
		audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/"+filePath).getAbsoluteFile());
		System.out.println(audioInputStream.getFormat());
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		status = "play";
	}

	/**
	 * @return the filePath
	 */
	public static String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public static void setFilePath(String filePath) {
		AudioPlayer.filePath = filePath;
	}

}
