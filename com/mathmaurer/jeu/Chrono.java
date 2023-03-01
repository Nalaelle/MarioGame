package com.mathmaurer.jeu;

public class Chrono implements Runnable{
	
	//Temps d'attente entre 2 tour de boucle en ms
	private final int PAUSE = 3; 
	

	@Override
	public void run() {
		while(true) {
			// appelle la fonction qui repaint la scene
			Main.scene.repaint(); 
			//System.out.println("ok");
			
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				//e.printStackTrace(); // --> SUPPR
			}
		}
	}
	

}
