package com.mathmaurer.personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.mathmaurer.audio.Audio;
import com.mathmaurer.jeu.Main;
import com.mathmaurer.objets.Objet;
import com.mathmaurer.objets.Piece;

public class Mario extends Personnage {
	
	//******* VARIABLES ********//
	
	private Image imgMario;
	private ImageIcon icoMario;
	
	private boolean saut; // vrai si mario saute
	private int compteurSaut; // gere la duréee et la hauteur du saut
	private int compteurMort;
	
	//******* CONSTRUCTEUR ********//

	public Mario(int x, int y) {
		super(x, y, 28, 50);
		//super.setVersDroite(true);
		//super.setMarche(false);
		this.icoMario = new ImageIcon(getClass().getResource("/images/marioMarcheDroite.png"));
		this.imgMario = this.icoMario.getImage();
		
		this.saut = false;
		this.compteurSaut = 0;
		this.compteurMort = 0;
		
	}

	
	//******* GETTERS ********//
	public Image getImgMario() {return imgMario;}
	public boolean isSaut() {return saut;}
	
	//******* SETTTERS ********//
	public void setSaut(boolean saut) {this.saut = saut;}
	
	//******* METHODES ********//
	
	@Override
	public Image marche(String nom, int frequence) {

		String str;
		ImageIcon ico;
		Image img;

		if (this.marche == false || Main.scene.getxPos() <= 0 || Main.scene.getxPos() > 4430) {
			if (this.versDroite) {str = "/images/" + nom + "ArretDroite.png";} 
			else {str = "/images/" + nom + "ArretGauche.png";}
		} else {
			
			this.compteur++;
			
			if (this.compteur / frequence == 0) {
				if (this.versDroite) {str = "/images/" + nom + "ArretDroite.png";}
				else {str = "/images/" + nom + "ArretGauche.png";}
			} else {
				if (this.versDroite) {str = "/images/" + nom + "MarcheDroite.png";} 
				else {str = "/images/" + nom + "MarcheGauche.png";}
			}
			
			if (this.compteur == 2 * frequence) {
				this.compteur = 0;
			}
		}
		
		// Affichage de l'image du perso
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}
	
	public Image saute() {
		String str;
		ImageIcon ico;
		Image img;
		
		this.compteurSaut++;
//		System.out.println("compteur de saut : ");
//		System.out.println(compteurSaut);
		// Montée du saut
		if(this.compteurSaut <= 39) {
			if(this.getY() >  Main.scene.getHautPlafond()) {this.setY(this.getY() - 4);}
			else {this.compteurSaut = 40;}
			if(this.isVersDroite()) {str = "/images/marioSautDroite.png";}
			else {str = "/images/marioSautGauche.png";}
		
		
			// Retombée du saut
		}else if(this.getY() + this.getHauteur() < Main.scene.getySol()) {
			this.setY(this.getY() + 1);
			if(this.isVersDroite()){str = "/images/marioSautDroite.png";}
			else {str = "/images/marioSautGauche.png";}
		
		
			//Saut Terminé
		}else {
			if(this.isVersDroite()){str = "/images/marioArretDroite.png";}
			else {str = "/images/marioArretGauche.png";}
			this.saut = false;
			this.compteurSaut = 0;
		}
				
		//Affichage de l'image de mario
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;		
		
	}
	
	
	
	public void contact(Objet objet) {
		// Contact horizontal
		if((super.contactAvant(objet) == true && this.isVersDroite() == true) || (super.contactArriere(objet) == true && this.isVersDroite() == false)) {
			Main.scene.setDx(0);
			this.setMarche(false);
		}
		
		//Contact avec un objet par en-dessous
		if(super.contactDessous(objet) == true && this.saut == true) { // Mario saute sur un objet
			Main.scene.setySol(objet.getY());
		}else if(super.contactDessous(objet) == false) {// mario tombe sur le sol initial
			Main.scene.setySol(293);// altitude du sol initial ----------293 ??
			if(this.saut == false) {// altitude initial de mario   // Instruction importante permet de redescendre au sol
				this.setY(243);
			}
		}
		
		
		//Contact avec un objet au dessus 
			if(super.contactDessus(objet) == true) { 
				Main.scene.setHautPlafond(objet.getY() + objet.getHauteur()); // le plafond devient le dessous de l'objet
			}else if(super.contactDessus(objet) == false && this.saut == false) {
				Main.scene.setHautPlafond(0);// altitude du plafond initial (ciel)
			}
			
			
	}// fin contact
	
	
	public boolean contactPiece(Piece piece) {
		// Le contact avec une pieces n'as pas de repercussion sur mario
		if(this.contactArriere(piece) == true || this.contactAvant(piece) == true || this.contactDessous(piece) == true || this.contactDessus(piece) == true) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public void contact(Personnage personnage) {
		if(super.contactAvant(personnage) == true || super.contactArriere(personnage) == true) {
			super.setMarche(false);
			this.setVivant(false);
		}else if(super.contactDessous(personnage) == true ) {
			personnage.setMarche(false);
			personnage.setVivant(false);
		}
	}
	
	
	//Mort de Mario
	public Image meurt() {
		String str;
		ImageIcon ico;
		Image img;
		
		str = "/images/boom.png";
		if(this.compteurMort == 0) {
			Audio.playSound("/audio/boum.wav");
		}
		if(this.compteurMort == 100) {
			Audio.playSound("/audio/partiePerdue.wav");
		}
		this.compteurMort++;
		if(this.compteurMort > 100) {
			str = "/images/marioMeurt.png";
			this.setY(this.getY() - 1);
		}
		
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}
	
	
	
	
	
}// fin classe
