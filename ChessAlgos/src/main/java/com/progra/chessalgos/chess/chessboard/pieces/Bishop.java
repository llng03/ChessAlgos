package com.progra.chessalgos.chess.chessboard.pieces;

public class Bishop extends Piece{

    public Bishop(Color color){
        super(color);
    }
    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "B";
        }
        else{
            return "b";
        }
    }
}
