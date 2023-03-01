package com.mathmaurer.jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mathmaurer.audio.Audio;

public class Clavier implements KeyListener{

	// **** METHODES **** //
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		// Bloque les touche quand mario meurt
		if(Main.scene.mario.isVivant() == true && Main.scene.finDePartie() == false) {
		
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				
				// Annule le decalage de 1 créer par deplacement fond
				if(Main.scene.getxPos() == -1) {
					Main.scene.setxPos(0); // Reinitialisation de la valeur
					Main.scene.setXfond1(-50); // Reinitialisation de la valeur
					Main.scene.setXfond2(750);  // Reinitialisation de la valeur
				}
				
				//mouvement mario
				Main.scene.mario.setMarche(true);
				Main.scene.mario.setVersDroite(true);
				
				Main.scene.setDx(1);
				
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				
				// Annule le decalage de 1 créer par deplacement fond
				if(Main.scene.getxPos() == 4431) {
					Main.scene.setxPos(4430); // Reinitialisation de la valeur
					Main.scene.setXfond1(-50); // Reinitialisation de la valeur
					Main.scene.setXfond2(750);  // Reinitialisation de la valeur
				}
				
				//mouvement mario
				Main.scene.mario.setMarche(true);
				Main.scene.mario.setVersDroite(false);
				
				Main.scene.setDx(-1);
			}
			
			
			// Mario Saute
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				Main.scene.mario.setSaut(true);
				Audio.playSound("/audio/saut.wav");
			}
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT 
			|| e.getKeyCode() == KeyEvent.VK_LEFT) {
			Main.scene.mario.setMarche(false);
			Main.scene.setDx(0);
		}
	}

//	@Override
//	public void keyReleased(KeyEvent e) {
//			Main.scene.mario.setMarche(false);
//			Main.scene.setDx(0);
//		
//	}

	
	
	
	
	// cette methode ne sert pas à garder en bas de fichier
	@Override
	public void keyTyped(KeyEvent e) {}

}
