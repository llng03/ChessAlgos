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
    public Set<Move> getLegalMoves(Position position, Square square) {
        Set<Move> moves = new HashSet<>();

        int cRank = square.rank();
        int cFile = square.file();

        //The rook can move in 4 directions: up, down, left and right
        //Directions: (-1, 0), (1, 0), (0, -1), (0, 1)

        int[] dRank = {-1, 1, 0, 0};
        int[] dFile = {0, 0, -1, 1};

        for(int direction = 0; direction < dRank.length; direction++){
            int step = 1;
            while(true){
                int newRank = cRank + step * dRank[direction];
                int newFile = cFile + step * dFile[direction];

                //Test if square is still on the board:
                if(newRank < 0 || newRank >= BOARD_SIZE || newFile < 0 || newFile >= BOARD_SIZE){
                    break; //we are not on the chessboard anymore
                }

                Square targetSquare = Square.getSquare(newRank, newFile);
                Piece target = position.getPieceOn(targetSquare);

                if(!target.isOccupied()){
                    //Square is empty so we can move there
                    moves.add(new Move(this, square, targetSquare, false, false, false, false));
                } else if(!target.getColor().equals(this.getColor())){
                    //there is an enemy piece on the square we can capture
                    moves.add(new Move(this, square, targetSquare, true, false, false, false));
                    break; //We cannot move past an enemy piece
                } else{
                    //There is an ally piece in the way, we cannot move ahead
                    break;
                }

                step++;
            }
        }
        return moves;
    }
}
