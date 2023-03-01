package com.mathmaurer.personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.mathmaurer.objets.Objet;

public class Tortue extends Personnage implements Runnable{
	//****VARIABLES ******//
	private Image imgTortue;
	private ImageIcon icoTortue;
	
	private final int PAUSE = 30; // temps d'attente entre deux tour de boucle 
	private int dxTortue; // pas (de pied) de,deplacement du champignon

	//****CONSTRUCTEUR ******//
	public Tortue (int x, int y) {
		super(x, y, 43, 50);// 27 , 30 ??
		super.setVersDroite(false); // false ??
		super.setMarche(true); 
		this.dxTortue = -1; // -1 ??
		
		this.icoTortue = new ImageIcon(getClass().getResource("/images/tortueArretGauche.png"));
		this.imgTortue = this.icoTortue.getImage();
	
		Thread chronoTortue = new Thread(this);
		chronoTortue.start();
	}

	//**** GETTERS ******//
	public Image getImgTortue() {return imgTortue;}

	//**** SETTERS ******//

	//**** METHODES ******//	
	public void bouge() {
		 if (super.isVivant() == true ) {
			if(super.isVersDroite() == true) {
				this.dxTortue = 1;
			}else {
				this.dxTortue = -1;
			}
			super.setX(super.getX() + this.dxTortue);
		 }
	}
	
	public void contact(Objet objet) {
		// Contact horizontal
		if(super.contactAvant(objet) == true 
			&& this.isVersDroite() == true) {
			super.setVersDroite(false);
			this.dxTortue = -1;
		}else if(super.contactArriere(objet) == true 
				&& this.isVersDroite() == false) {
			super.setVersDroite(true);
			this.dxTortue = 1;
		}
	}
	
	
	public void contact(Personnage personnage) {
		// Contact horizontal
		if(super.contactAvant(personnage) == true 
			&& this.isVersDroite() == true) {
			super.setVersDroite(false);
			this.dxTortue = -1;
		}else if(super.contactArriere(personnage) == true 
				&& this.isVersDroite() == false) {
			super.setVersDroite(true);
			this.dxTortue = 1;
		}
	}
	
	
	// mort de la tortue
	public Image meurt() {
		String str;
		ImageIcon ico;
		Image img;
		
		this.dxTortue = 0; // pas present
		str = "/images/tortueFermee.png";
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}
	
	@Override
	public void run() {
		try{Thread.sleep(20);} // attend que les objets soient charger
		catch (InterruptedException e) {}
		
		while(true) {// boucle infinie
			if(this.isVivant() == true) { // enlever ??
				this.bouge();
				try{Thread.sleep(PAUSE);}
				catch (InterruptedException e) {}
			}
			
		}
	}
	
	
	

}// fin de la classe
