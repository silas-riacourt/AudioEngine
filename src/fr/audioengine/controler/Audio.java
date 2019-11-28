package fr.audioengine.controler;


public class Audio {


	public static void makeSound() {
		int numbeeps = 10;
		System.out.println("hdjd");
		for(int x=0;x<numbeeps;x++)
		{
		  java.awt.Toolkit.getDefaultToolkit().beep();
		}
	}
}
