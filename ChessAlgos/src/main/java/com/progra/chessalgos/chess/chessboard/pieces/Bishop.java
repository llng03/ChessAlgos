package com.progra.chessalgos.chess.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.Square;

import java.util.HashSet;
import java.util.Set;

import static com.progra.chessalgos.Constants.BOARD_SIZE;

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
    public Set<Move> getLegalMoves(Position position, Square square) {
        Set<Move> moves = new HashSet<>();
        int cRank = square.rank(); //current rank position of Bishop
        int cFile = square.file(); //current file position of Bishop

        //We can move over the diagonal in 4 directions(upper left, upper right, down left, down right)
        //Directions: (-1 -1), (-1,+1), (+1,-1), (+1,+1)
        int[] dRank = {-1, -1, 1, 1};
        int[] dFile = {-1, 1, -1, 1};

        for (int direction = 0; direction < dRank.length; direction++){
            int step = 1;
            while(true){
                int newRank = cRank + step * dRank[direction];
                int newFile = cFile + step * dFile[direction];

                //Test if new position is still on the board
                if(newRank < 0 || newRank >= BOARD_SIZE || newFile < 0 || newFile >= BOARD_SIZE){
                    break; //we are outside the board
                }

                Square targetSquare = Square.getSquare(newRank, newFile);
                Piece target = position.getPieceOn(targetSquare);

                if(!target.isOccupied()){
                    //Square is Empty, so we can move there
                    moves.add(new Move(this, square, targetSquare, false, false, false, false));
                }else if (!target.getColor().equals(getColor())){
                    //there is an enemy piece on the square, we can capture
                    moves.add(new Move(this, square, targetSquare, true, false, false, false));
                    break; //Bishop cannot move past an enemy piece
                } else{
                    //there is an ally piece on the square, we can not move past
                    break;
                }

                step++;
            }
        }
        return moves;
    }
}
