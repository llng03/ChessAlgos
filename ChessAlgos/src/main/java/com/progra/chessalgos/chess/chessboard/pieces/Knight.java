package com.progra.chessalgos.chess.chessboard.pieces;

public class Knight extends Piece{

    public Knight(Color color){
        super(color);
    }
    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "N";
        }
        else{
            return "n";
        }
    }
}
