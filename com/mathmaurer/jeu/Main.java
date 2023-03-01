package com.mathmaurer.jeu;

import javax.swing.JFrame;

public class Main {
	
	// rend la class disponible globalement
	public static Scene scene; 

	public static void main(String[] args) {
		
		
		// Création de la fenetre de l'aplication
		JFrame fenetre = new JFrame("Jeu style Mario"); // nom du jeu
		// fermeture de la fenetre = fermeture de l'application
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		fenetre.setSize(700, 360); // taille de la fenetre
		fenetre.setLocationRelativeTo(null); // permet de centrer à l'ecran
		fenetre.setResizable(false); // interdiction de redimension
		fenetre.setAlwaysOnTop(true); // toujours au dessus des autres application en cour
	
		
		// instanciation de l'objet Scene
		scene = new Scene();
		
		// on associe la scene à la fenetre
		fenetre.setContentPane(scene);
		fenetre.setVisible(true);
		
	}

}
