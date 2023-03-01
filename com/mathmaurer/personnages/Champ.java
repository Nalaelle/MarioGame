package com.mathmaurer.personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

//import com.mathmaurer.jeu.Main;
import com.mathmaurer.objets.Objet;

public class Champ extends Personnage implements Runnable{
	//****VARIABLES ******//
	private Image imgChamp;
	private ImageIcon icoChamp;
	
	private final int PAUSE = 15; // temps d'attente entre deux tour de boucle 
	private int dxChamp; // pas (de pied) de,deplacement du champignon

	//****CONSTRUCTEUR ******//
	public Champ (int x, int y) {
		super(x, y, 27, 30);
		super.setVersDroite(true);
		super.setMarche(true);
		this.dxChamp = 1;
		
		this.icoChamp = new ImageIcon(getClass().getResource("/images/champArretDroite.png"));
		this.imgChamp = this.icoChamp.getImage();
	
		Thread chronoChamp = new Thread(this);
		chronoChamp.start();
	}

	//**** GETTERS ******//
	public Image getImgChamp() {return imgChamp;}

	//**** SETTERS ******//

	//**** METHODES ******//	
	public void bouge() {
		if(this.isVivant() == true) {
			if(super.isVersDroite() == true) {
				this.dxChamp = 1;
			}else {
				this.dxChamp = -1;
			}
			super.setX(super.getX() + this.dxChamp);			
		}
	}
	
	
	public void contact(Objet objet) {
		// Contact horizontal
		if(super.contactAvant(objet) == true 
			&& this.isVersDroite() == true) {
			super.setVersDroite(false);
			this.dxChamp = -1;
		}else if(super.contactArriere(objet) == true 
				&& this.isVersDroite() == false) {
			super.setVersDroite(true);
			this.dxChamp = 1;
		}
	}
	
	

	public void contact(Personnage personnage) {
		// Contact horizontal
		if(super.contactAvant(personnage) == true 
			&& this.isVersDroite() == true) {
			super.setVersDroite(false);
			this.dxChamp = -1;
		}else if(super.contactArriere(personnage) == true 
				&& this.isVersDroite() == false) {
			super.setVersDroite(true);
			this.dxChamp = 1;
		}
	}
	
	
	// mort du champignon
	public Image meurt() {
		String str;
		ImageIcon ico;
		Image img;
		
		this.dxChamp = 0;
		if(this.isVersDroite() == true) {
			str = "/images/champEcraseDroite.png";
		}else {
			str = "/images/champEcraseGauche.png";
		}
		
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}
	
	
	@Override
	public void run() {
		try{Thread.sleep(20);} // attend que les objets soioent charger
		catch (InterruptedException e) {}
		
		while(true) {// boucle infinie
			if (this.isVivant() == true) { // enlever ??
				this.bouge();
				try{Thread.sleep(PAUSE);}
				catch (InterruptedException e) {}
			}	
		}
	}
	
	
	

}
