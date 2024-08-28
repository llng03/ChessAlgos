package com.progra.chessalgos.chess.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean isOccupied() {
        return true;
    }
    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "R";
        }
        else{
            return "r";
        }
    }
    @Override
    public Set<Move> getLegalMoves(Position position){
        Set<Move> set = new HashSet<>();

        return set;
    }
}
