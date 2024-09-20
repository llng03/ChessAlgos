package com.progra.chessalgos.chess.chessboard;

import com.progra.chessalgos.chess.chessboard.pieces.Color;
import com.progra.chessalgos.chess.chessboard.pieces.Piece;

import static com.progra.chessalgos.Constants.BOARD_SIZE;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.WHITE;

//every information about a chess position is stored here
public class Position {
    //every object of this class should be immutable
    /*how do we change the position then? - every position is an own object itself - this is why I
    did not want to call the class "chessboard" because this would imply that we can change the position on it like we want
     */

    //the field contains all information we can get from a FEN-document
    private final Piece[][] position;

    private final Color toMove;
    private final boolean[] castlingRights;
    private final Square ep; //is there any en passant move possible?
    private final int halfMovesSinceCaptureOrPawn;
    private final int moveNumber;

    Position(Piece[][] position, Color toMove, boolean[] castlingRights, Square ep, int move, int moveNumber) {
        this.position = position;
        this.toMove = toMove;
        this.castlingRights = castlingRights;
        this.ep = ep;
        this.halfMovesSinceCaptureOrPawn = move;
        this.moveNumber = moveNumber;
    }

    //Output of a position (especially helpful for testing)
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int rank = BOARD_SIZE - 1; rank >= 0; rank--){
            for(int file=0; file < BOARD_SIZE; file++){
                s.append(position[rank][file].toString()).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    //Moves a piece and creates a new position
   //TODO: how about the other variables of a position that change when a piece moves?
    public Position resetPiece(Piece piece, Square from, Square target){
        return new PositionBuilder()
                .changePosition(this.position, piece, from, target).build();
    }

    //getter methods of the variables

    public Color getToMove() {
        return toMove;
    }

    public Piece getPieceOn(Square square){
        return position[square.rank()][square.file()];
    }

    public Square getEp() {
        return this.ep;
    }

    public boolean kingsideCastleRight(Color color){
        return color == WHITE ? castlingRights[0] : castlingRights[2];
    }

    public boolean queensideCastleRight(Color color){
        return color == WHITE ? castlingRights[1] : castlingRights[3];
    }

    public int getMoveNumber(){
        return moveNumber;
    }

    public int getHalfmovesSinceCaptureOrPawn(){
        return halfMovesSinceCaptureOrPawn;
    }
}
