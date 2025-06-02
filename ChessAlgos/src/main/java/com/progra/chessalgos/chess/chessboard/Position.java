package com.progra.chessalgos.chess.chessboard;

import com.progra.chessalgos.chess.chessboard.pieces.Color;
import com.progra.chessalgos.chess.chessboard.pieces.King;
import com.progra.chessalgos.chess.chessboard.pieces.Piece;

import static com.progra.chessalgos.Constants.BOARD_SIZE;
import static com.progra.chessalgos.chess.chessboard.Square.*;
import static com.progra.chessalgos.chess.Util.findObject;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.BLACK;
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
    public Position move(Square start, Square target){
        //Find a legal move with the same start and target squares: if not found the method returns null
        Move move = findObject(getPieceOn(start).getLegalMoves(this, start), m -> m.getFrom().equals(start) && m.getTo().equals(target));

        if(move != null){
            Position newPos = new PositionBuilder().changePosition(this.position, this.getPieceOn(start), start, target)
                    .toMove(this.toMove.change())
                    .setMoveNumber(moveNumber + (toMove == BLACK? 1 : 0))
                    .setHalfMovesSinceCaptureOrPawn(this.halfMovesSinceCaptureOrPawn + (move.isPawnMove() || move.isCapture() ? 0 : 1))
                    .build();
            //Castle
            if(move.isCastle()) {
                //Queenside Castle
                //target = C1 or C8
                Square rookStart = toMove == WHITE ? A1 : A8;
                Square rookTarget = toMove == WHITE ? D1 : D8;

                //Kingside Castle
                if (target == (toMove == WHITE ? Square.G1 : G8)) {
                    rookStart = toMove == WHITE ? H1 : H8;
                    rookTarget = toMove == WHITE ? F1 : F8;
                }

                newPos = new PositionBuilder()
                        .changePosition(newPos.position, this.getPieceOn(rookStart), rookStart, rookTarget)
                        .takeKingsideCastlingRightsFrom(toMove)
                        .takeQueensideCastlingRightsFrom(toMove)
                        .build();
            }

            return newPos;

        } else {
            throw new IllegalArgumentException("Move is not legal");
        }
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

    public Square findOpponentKing(Color opponentColor){
        for(int rank = 0; rank < BOARD_SIZE; rank++){
            for(int file = 0; file < BOARD_SIZE; file++){
                Piece piece = this.position[rank][file];
                if(piece instanceof King && piece.getColor().equals(opponentColor)){
                    return Square.getSquare(rank, file);
                }
            }
        }
        return null; //Error case, king should always exist
    };
}
