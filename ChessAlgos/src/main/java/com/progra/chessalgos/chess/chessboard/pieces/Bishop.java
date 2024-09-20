package com.progra.chessalgos.chess.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.Square;

import java.util.HashSet;
import java.util.Set;

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

    @Override
    public Set<Move> getLegalMoves(Position position, Square square){
        Set<Move> set = new HashSet<>();

        return set;
    }
}
