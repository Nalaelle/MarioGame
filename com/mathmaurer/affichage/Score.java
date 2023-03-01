package com.mathmaurer.affichage;

public class Score {

	//****VARIABLES ******//
	private final int NBR_TOTAL_PIECE = 10;
	private int nbrPieces;

	//****CONSTRUCTEUR ******//
	public Score() {
		this.nbrPieces = 0;
	}
	

	//**** GETTERS ******//
	public int getNbrPieces() {return nbrPieces;}
	public int getNBR_TOTAL_PIECE() {return NBR_TOTAL_PIECE;}
	//**** SETTERS ******//
	public void setNbrPieces(int nbrPieces) {this.nbrPieces = nbrPieces;}
	//**** METHODES ******//

}
