package com.mathmaurer.personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.mathmaurer.jeu.Main;
//import com.mathmaurer.jeu.Main;
import com.mathmaurer.objets.Objet;

public class Personnage {

	// ******VARIABLE******//
	private int largeur, hauteur; // dimension du personnage
	private int x, y; // position du personnage
	protected boolean marche; // vrai si le perso avance
	protected boolean versDroite; // vrai si orientation vers la droite
	public int compteur; // Compteur de pas du perso
	
	protected boolean vivant;// vrai si le personnage vivant
	
	// ****CONSTRUCTEUR********//
	public Personnage(int x, int y, int largeur, int hauteur) {

		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.compteur = 0;
		this.marche = false;
		this.versDroite = true;
		this.vivant = true;
	}

	// **** GETTERS ****//
	public int getX() {return x;}
	public int getY() {return y;}
	public int getLargeur() {return largeur;}
	public int getHauteur() {return hauteur;}
	public boolean isMarche() {return marche;}
	public boolean isVersDroite() {return versDroite;}
	public int getCompteur() {return compteur;}
	public boolean isVivant() {return vivant;}

	// **** SETTERS ****//
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}

	public void setMarche(boolean marche) {this.marche = marche;}
	public void setVersDroite(boolean versDroite) {this.versDroite = versDroite;}
	public void setCompteur(int compteur) {this.compteur = compteur;}
	public void setVivant(boolean vivant) {this.vivant = vivant;}
	
	// ****** METHODES *********//
	
	public void deplacement() {
		if(Main.scene.getxPos() >= 0) {
//			this.setX(this.getX() - Main.scene.getDx());
			this.x = this.x - Main.scene.getDx();
		}
	}

	public Image marche(String nom, int frequence) {
		String str;
		ImageIcon ico;
		Image img;
//		if (this.marche == false || Main.scene.getxPos() <= 0 || Main.scene.getxPos() > 4430) { --> SUPPR
		if (this.marche == false) {
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
	


	// Détection du contact à droite de mario
	protected boolean contactAvant(Objet objet) {
		if(this.isVersDroite() == true) {// ajout
			if(this.x + this.largeur < objet.getX() 
			|| this.x + this.largeur > objet.getX() + 5 
			|| this.y + this.hauteur <= objet.getY() 
			|| this.y >= objet.getY() + objet.getHauteur()) {
				return false;
			}else {return true;}
		}else {return false;} // ajout
		
	}
	
	
	// Détection du contact à gauche de mario
		protected boolean contactArriere(Objet objet) {
			if(this.x > objet.getX() + objet.getLargeur() 
			|| this.x + this.largeur < objet.getX() + objet.getLargeur() - 5 
			|| this.y + this.hauteur <= objet.getY() 
			|| this.y >= objet.getY() + objet.getHauteur()) {
				return false;
			}else {
				return true;
			}
		}
		
		
	// Détection du contact en dessous de mario
	protected boolean contactDessous(Objet objet) {
		if(this.x + this.largeur < objet.getX() + 5 
		|| this.x > objet.getX() + objet.getLargeur() - 5 
		|| this.y + this.hauteur < objet.getY() 
		|| this.y + this.hauteur > objet.getY() + 5 ) {
			return false;
		}else {
			return true;
		}
	}
		
		
		
	// Détection du contact au dessus de mario
	protected boolean contactDessus(Objet objet) {
		if(this.x + this.largeur < objet.getX() + 5 
		|| this.x > objet.getX() + objet.getLargeur() -5 
		|| this.y < objet.getY() + objet.getHauteur() 
		|| this.y > objet.getY() + objet.getHauteur() + 5) {
			return false;
		}else {
			return true;
		}
	}
	
	
	// Detection de l'objet le plus proche 
	public boolean proche(Objet objet) {
		if((this.x > objet.getX() - 10 
		&& this.x < objet.getX() + objet.getLargeur() + 10) 
		|| (this.x + this.largeur > objet.getX() -10 
		&& this.x + this.largeur < objet.getX() + objet.getLargeur() + 10 )) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/// contact personnage et personnage
	
	// Détection du contact à droite 
	protected boolean contactAvant(Personnage personnage) {
		if (this.isVersDroite() == true) { // ajout apres 
			if(this.x + this.largeur < personnage.getX() 
			|| this.x + this.largeur > personnage.getX() + 5 
			|| this.y + this.hauteur <= personnage.getY() 
			|| this.y >= personnage.getY() + personnage.getHauteur()) {
				return false;
			}else {
				return true;
			}	
		}else {return false;}
		
	}
	
	
	// Détection du contact à gauche 
		protected boolean contactArriere(Personnage personnage) {
			if(this.x > personnage.getX() + personnage.getLargeur() 
				|| this.x + this.largeur < personnage.getX() + personnage.getLargeur() - 5 
				|| this.y + this.hauteur <= personnage.getY() 
				|| this.y >= personnage.getY() + personnage.getHauteur()) {
				return false;
			}else {
				return true;
			}
		}
		
	// Détection du contact Dessous Personnage
	protected boolean contactDessous(Personnage personnage) {
		if(this.x + this.largeur < personnage.getX()
		|| this.x > personnage.getX() + personnage.getLargeur() 
		|| this.y + this.hauteur < personnage.getY() 
		|| this.y + this.hauteur > personnage.getY() ) {
			return false;
		}else {
			return true;
		}
	}
		
	// Detection du personage le plus proche 
	public boolean proche(Personnage personnage) {
		if((this.x > personnage.getX() - 10 
		&& this.x < personnage.getX() + personnage.getLargeur() + 10) 
		|| (this.x + this.largeur > personnage.getX() - 10 
		&& this.x + this.largeur < personnage.getX() + personnage.getLargeur() + 10 )) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
		
	
	
	
}
