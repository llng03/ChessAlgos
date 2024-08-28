package com.progra.chessalgos.chess.chessboard.pieces;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color);
    }
    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "Q";
        }
        else{
            return "q";
        }
    }
}
