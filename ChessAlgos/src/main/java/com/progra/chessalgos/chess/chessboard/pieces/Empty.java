package com.progra.chessalgos.chess.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.Square;
import com.progra.chessalgos.chess.chessboard.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class Empty extends Piece {
    //This is a filler for empty sqaures and there is only one final object of it in the field of the
    //PositionBuilder Class which is used to setup chessboards.
    public Empty(){
        super();
    }

    @Override
    public boolean isOccupied(){
        return false;
    }

    @Override
    public String toString(){
        return "-";
    }

    @Override
    public Set<Move> getLegalMoves(Position position, Square square){
        return new HashSet<>();
    }


}
