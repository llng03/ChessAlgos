package com.progra.chessalgos.chess.chessboard.pieces;

public class Pawn extends Piece{

    public Pawn(Color color){
        super(color);
    }
    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "P";
        }
        else{
            return "p";
        }
    }
}
