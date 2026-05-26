package chess_game;

public class Moves {
	
	//This is a class created for AI because tree needs backtracking so it needs to remember the past state
	//So this class is created mainly because instead of passing array to AI we pass objects 
	
	public int fromRow, fromCol , toRow , toCol , movedPiece , capturedPiece;
	boolean isPawnPromotion;


public Moves(int fromRow , int fromCol , int toRow , int toCol , int movedPiece , int capturedPiece , boolean isPawnPromotion) {
	
	this.fromRow = fromRow;
	this.fromCol = fromCol;
	this.toRow = toRow;
	this.toCol = toCol;
	this.movedPiece = movedPiece;
	this.capturedPiece = capturedPiece;
	this.isPawnPromotion = isPawnPromotion;
	
}









}