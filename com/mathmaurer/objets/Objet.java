package com.mathmaurer.objets;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.mathmaurer.jeu.Main;

public class Objet {
	
	//****VARIABLES ******//
	private int largeur, hauteur;//dimension de l'objet
	private int x, y; // position de l'objet
	
	protected Image imgObjet;
	protected ImageIcon icoObjet;
	
	//****CONSTRUCTEUR ******//
	public Objet(int x, int y, int largeur, int hauteur) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	
	//**** GETTERS ******//
	public int getLargeur() {return largeur;}
	public int getHauteur() {return hauteur;}
	public int getX() {return x;}
	public int getY() {return y;}	
	
	public Image getImgObjet() {return imgObjet;}

	//**** SETTERS ******//
	public void setLargeur(int largeur) {this.largeur = largeur;}
	public void setHauteur(int hauteur) {this.hauteur = hauteur;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	//**** METHODES ******//
	public void deplacement() {
		if(Main.scene.getxPos() >= 0) {
			this.x = this.x - Main.scene.getDx();
		}
	}

}
