package com.progra.chessalgos.chess.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.Square;

import java.util.HashSet;
import java.util.Set;

import static com.progra.chessalgos.Constants.BOARD_SIZE;

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
    public Set<Move> getLegalMoves(Position position, Square square){
        Set<Move> moves = new HashSet<>();
        //moving down
        for(int rank = square.rank() -1; rank >= 0; rank--){
            if(!position.getPieceOn(Square.getSquare(rank, square.file())).isOccupied()){
                moves.add(new Move(this, square, Square.getSquare(rank, square.file()), false, false, false, false));
            }
            else{
                if(position.getPieceOn(Square.getSquare(rank,square.file())).getColor() == this.getColor()){
                    break;
                }
                else{
                    moves.add(new Move(this, square, Square.getSquare(rank,square.file()), true, false, true, false));
                }
            }
        }
        //moving up
        for(int rank = square.rank() + 1; rank < BOARD_SIZE; rank++){
            if(!position.getPieceOn(Square.getSquare(rank, square.file())).isOccupied()){
                moves.add(new Move(this, square, Square.getSquare(rank, square.file()), false, false, false, false));
            }
            else{
                if(position.getPieceOn(Square.getSquare(rank,square.file())).getColor() == this.getColor()){
                    break;
                }
                else{
                    moves.add(new Move(this, square, Square.getSquare(rank,square.file()), true, false, true, false));
                }
            }
        }
        //moving left
        for(int file = square.file() - 1; file >= 0; file--){
            if(!position.getPieceOn(Square.getSquare(square.rank(), file)).isOccupied()){
                moves.add(new Move(this, square, Square.getSquare(square.rank(), file), false, false, true, false));
            }
            else{
                if(position.getPieceOn(Square.getSquare(square.rank(), file)).getColor() == this.getColor()){
                    break;
                }
                else{
                    moves.add(new Move(this, square, Square.getSquare(square.rank(), file), true, false, true, false));
                }
            }
        }
        //moving right
        for(int file = square.file() + 1; file <  BOARD_SIZE; file++){
            if(!position.getPieceOn(Square.getSquare(square.rank(), file)).isOccupied()){
                moves.add(new Move(this, square, Square.getSquare(square.rank(), file), false, false, true, false));
            }
            else{
                if(position.getPieceOn(Square.getSquare(square.rank(), file)).getColor() == this.getColor()){
                    break;
                }
                else{
                    moves.add(new Move(this, square, Square.getSquare(square.rank(), file), true, false, true, false));
                }
            }
        }

        return moves;
    }
}
