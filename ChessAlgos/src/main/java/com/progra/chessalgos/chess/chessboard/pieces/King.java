package com.progra.chessalgos.chess.chessboard.pieces;

import org.springframework.security.core.parameters.P;

public class King extends Piece {
    public King(Color color){
        super(color);
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "K";
        }
        else{
            return "k";
        }
    }
}
