package com.progra.chessalgos.chess.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.Square;

import java.util.Set;

import static com.progra.chessalgos.chess.chessboard.pieces.Color.WHITE;

public abstract class Piece {
    private final Color color;

    public Piece(Color color){
        this.color = color;
    }


    public Piece(){
        this.color = WHITE;
    }
    //only used by the empty class, which has no sepcific color so its default on white
    //Important:
    //functions like "count number of white pieces" should always check if the square is occupied
    //before considering this square into the counting

    public Color getColor(){
        return color;
    };

    public abstract boolean isOccupied();

    public abstract Set<Move> getLegalMoves(Position position, Square square);
}
