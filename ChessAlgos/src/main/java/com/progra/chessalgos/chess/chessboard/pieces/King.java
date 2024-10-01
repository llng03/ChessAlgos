package com.progra.chessalgos.chess.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.Square;
import org.springframework.security.core.parameters.P;

import java.util.HashSet;
import java.util.Set;

import static com.progra.chessalgos.Constants.BOARD_SIZE;
import static com.progra.chessalgos.chess.chessboard.Square.*;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.WHITE;

public class King extends Piece {
    public King(Color color){
        super(color);
    }

    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public String toString(){
        if(getColor() == WHITE){
            return "K";
        }
        else{
            return "k";
        }
    }

    @Override
    public Set<Move> getLegalMoves(Position position, Square square) {
        Set<Move> moves = new HashSet<>();

        int cRank = square.rank();
        int cFile = square.file();

        //Directions: (1, 0), (1, 1), (0, 1), (-1, 1), (-1, 0), (-1, -1), (0, -1), (1, -1)

        int[] dRank = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dFile = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int direction = 0; direction < dRank.length; direction++) {
            int newRank = cRank + dRank[direction];
            int newFile = cFile + dFile[direction];

            //test if new Square is still on the board
            if (newRank < 0 || newRank >= BOARD_SIZE || newFile < 0 || newFile >= BOARD_SIZE) {
                continue; //ignore the rest of the code - this move cant be added
            }

            Square targetSquare = Square.getSquare(newRank, newFile);
            Piece target = position.getPieceOn(targetSquare);

            if (!target.isOccupied()) {
                //the square is empty and we can move the knight there
                moves.add(new Move(this, square, targetSquare, false, false, false, false));
            } else if (!target.getColor().equals(this.getColor())) {
                //There is an enemy piece on the square - we can capture
                moves.add(new Move(this, square, targetSquare, true, false, false, false));
            } //Else there is an ally piece on the square so we do nothing
        }
        if(position.kingsideCastleRight(this.getColor())){
            moves.add(new Move(this, square, (this.getColor().equals(WHITE)? G1: G8), false, false, true, false));
        }
        if(position.queensideCastleRight(this.getColor())){
            moves.add(new Move(this, square, (this.getColor().equals(WHITE)? C1: C8), false, false, true, false));
        }
        return moves;
    }
}
