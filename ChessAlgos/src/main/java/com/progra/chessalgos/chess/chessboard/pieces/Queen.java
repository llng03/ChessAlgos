package com.progra.chessalgos.chess.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.Square;

import java.util.HashSet;
import java.util.Set;

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

    @Override
    public Set<Move> getLegalMoves(Position position, Square square){
        Set<Move> moves = new HashSet<>();
        //We use Composition here to prevent redundant code

        //Rook logic
        Rook rook = new Rook(this.getColor());
        Set<Move> rookMoves = rook.getLegalMoves(position, square);
        moves.addAll(reassignMovesToQueen(rookMoves, square));

        //Bishop logic
        Bishop bishop = new Bishop(this.getColor());
        Set<Move> bishopMoves = bishop.getLegalMoves(position, square);
        moves.addAll(reassignMovesToQueen(bishopMoves, square));

        return moves;
    }

    private Set<Move> reassignMovesToQueen(Set<Move> moves, Square from){
        Set<Move> reassignedMoves = new HashSet<>();
        for (Move move : moves) {
            //Create a new Move, but substitute the actor Piece with this Queen
            Move queenMove = new Move(this, from, move.getTo(), move.isCapture(), move.isPawnMove(), move.isCastle(), move.isCheck());
            reassignedMoves.add(queenMove);
        }
        return reassignedMoves;
    }
}
