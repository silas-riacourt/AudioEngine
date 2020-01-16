package fr.audioengine.model;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.Float;

/**
 * @author silas and alexis
 * 
 *         Class pour gérer l'objet Audio Un audio est un son auquel on fournit
 *         toutes les méthodes pour :
 * 
 *         - le jouer - L'arrêter - Le redémarrer - Le jouer en boucle (ou un
 *         certains nombre de fois) - Le jouer avec une durée spécifier
 *
 */
public class Audio {

	Clip clip;

	String path;

	boolean loop = false;

	int loop_time;
	
	boolean effect = false;

	AudioInputStream audioInputStream;

	/**
	 * 
	 * Constructeur qui permet de construire un nouvel Audio
	 * 
	 * @param path : le chemin vers le fichier
	 */
	public Audio(String path) {

		this.path = path;
		try {
			this.audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/" + path));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Méthode pour démarrer un son
	 * 
	 * @param loop : boolean -> spécifie si le son doit être jouer en boucle
	 * @return status : boolean -> indique si le son a été joué
	 */
	public boolean play(boolean loop) {

		boolean status = false;
		/* Si le son n'est pas déjà en route */
		if (!clip.isRunning()) {

			/* si on spécifie de le jouer en boucle */
			if (loop) {

				clip.loop(Clip.LOOP_CONTINUOUSLY);
				status = true;
			} else {
				clip.start();
				status = true;
			}
		}

		return status;
	}

	/**
	 * 
	 * Méthode pour jouer un son avec durée définie
	 * 
	 * @param duree : float -> détermine la durée du son joué
	 * @return waiting -> indique si le son a été joué
	 * @throws InterruptedException
	 */
	public boolean timedPlay(Float duree) throws InterruptedException {
		this.play(false);
		boolean waiting = false;

		if (duree > this.clip.getMicrosecondLength() / 1000000) {
			System.out.println("Erreur, timecode supérieur à la durée du son");
			return waiting;
		} else if (waiting == false) {
			while (waiting == false) {
				// System.out.println("rentré dans boucle while timecode : "+getTimeCode());

				if (Float.compare(getTimeCode(), duree) == 0) {
					this.clip.stop();
					waiting = true;
				}
			}
		}
		return waiting;

	}

	public float getTimeCode() {
		return (float) (this.clip.getMicrosecondPosition() / 1000000);
	}

	/**
	 * 
	 * Méthode pour démarrer un son
	 * 
	 * @param loop : entier -> spécifie le nombre de fois que le son doit être jouer
	 * @return status : boolean -> indique si le son a été jouer
	 */
	public boolean play(int loop) {
		boolean status = false;
		/* Si le son n'est pas déjà en route */
		if (!clip.isRunning()) {

			/* si on spécifie un nombre de fois correct */
			if (loop > 1) {

				clip.loop(loop - 1);
				status = true;
			} else {
				System.out.println("play : nombre de boucles incorrect < 1");
			}
		}

		return status;
	}

	/**
	 * 
	 * Méthode pour démarrer un son
	 * 
	 * @return status : boolean -> indique si le son a été jouer
	 */
	public boolean play() {
		boolean status = false;
		/* Si le son n'est pas déjà en route */
		if (!clip.isRunning()) {

			clip.start();
		}

		return status;
	}

	/**
	 * 
	 * Méthode permettant de régler le volume d'un son
	 * 
	 * @param volume : float -> entre 0.0 (muet) et 1.0 (maximum)
	 * @return status : boolean -> indique si tout s'est bien passé
	 * 
	 */
	public boolean setVolume(float volume) {

		boolean status = false;

		if (clip.isOpen()) {

			if (volume < 0f || volume > 1f)
				throw new IllegalArgumentException("Volume not valid: " + volume);

			FloatControl volumeControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
			float range = volumeControl.getMaximum() - volumeControl.getMinimum();
			float gain = (range * volume) + volumeControl.getMinimum();
			volumeControl.setValue(gain);
			status = true;

		}
		return status;
	}

	/**
	 * 
	 * Permet de mettre en pause un son
	 * 
	 * @return boolean : retourne TRUE si tout s'est bien passé FALSE sinon
	 */
	public boolean pause() {

		boolean status = false;

		if (this.clip.isRunning()) {
			status = true;
			this.clip.stop();
		}

		return status;
	}

	/**
	 * 
	 * Méthode permettant de redémarrer un son déjà jouer
	 * 
	 * @return status : indique si tout s'est bien passé
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws UnsupportedAudioFileException
	 */
	public boolean restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {

		boolean status = false;

		this.stop();
		this.clip = AudioSystem.getClip();
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("src/fr/audioengine/samples/" + path));
		this.clip.open(audioInputStream);
		// this.play(this.);
		status = true;

		return status;
	}

	/**
	 * 
	 * Méthode pour arrêter un son.
	 * 
	 * @return status : boolean -> permet de savoir si tout s'est bien passé
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public boolean stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		boolean status = false;

		if (this.clip.isRunning()) {
			this.clip.stop();
			this.clip.close();
			status = true;
		}

		return status;
	}

	/**
	 * 
	 * Méthode pour créer l'effet stereo
	 * 
	 * @param speed : vitesse de l'effet en milli-seconde
	 * @throws InterruptedException
	 */
	public void effetStereo(int speed) throws InterruptedException {
		effect = true;
		new Thread(() -> {
			try {
				while(clip.isRunning() && effect) {
					balanceChange(speed);
					Thread.sleep(speed*4);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
	}

	/**
	 * 
	 * Méthode pour créer un effet stereo en changant la balance
	 * 
	 * @param speed
	 * @throws InterruptedException
	 */
	private void balanceChange(int speed) throws InterruptedException {
		FloatControl balance = (FloatControl) this.clip.getControl(FloatControl.Type.BALANCE);
		
		System.out.println("gauche");
		balance.setValue(-1);
		Thread.sleep(speed);
		System.out.println("milieu");
		balance.setValue(0);
		Thread.sleep(speed);
		balance.setValue(1);	
		System.out.println("droite");
		Thread.sleep(speed);
		System.out.println("milieu");
		balance.setValue(0);
		Thread.sleep(speed);
	}
	public Clip getClip() {
		return this.clip;
	}
	/**
	 * Arrête l'effet en cours
	 */
	public void stopEffect() {
		this.effect = false;
	}
}
