package com.progra.chessalgos.chess.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.Square;

import java.util.HashSet;
import java.util.Set;

import static com.progra.chessalgos.Constants.BOARD_SIZE;
import static com.progra.chessalgos.chess.chessboard.Square.getSquare;
import static com.progra.chessalgos.chess.chessboard.Square.isInsideBoard;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.WHITE;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public String toString() {
        if (getColor() == WHITE) {
            return "P";
        } else {
            return "p";
        }
    }

    private boolean onSecondRank(Square pawnPos) {
        int secondRank = this.getColor().equals(WHITE) ? 1 : 6;
        return pawnPos.rank() == secondRank;
    }

    @Override
    public Set<Move> getLegalMoves(Position position, Square square) {
        Set<Move> moves = new HashSet<>();
        int direction = this.getColor().equals(WHITE) ? 1 : -1; //white pawns move the array down (+1), black pawns up(-1)
        int endRank = this.getColor().equals(WHITE) ? 0 : BOARD_SIZE; //end row for promotion

        int cRank = square.rank();
        int cFile = square.file();

        //Normal Move (one step forward)
        if (isInsideBoard(cRank + direction, cFile) && !position.getPieceOn(Square.getSquare(cRank + direction, cFile)).isOccupied()) {
            //One square ahead is empty
            moves.add(new Move(this, square, getSquare(cRank + direction, cFile), false, true, false, false));
            //First move (two steps farward if the path is clear)
            if (onSecondRank(square) && isInsideBoard(cRank + 2 * direction, cFile) && !position.getPieceOn(Square.getSquare(cRank + direction, cFile)).isOccupied()) {
                moves.add(new Move(this, square, Square.getSquare(cRank + 2 * direction, cFile), false, true, false, false));
            }
        }
        //capturing (diagonally forward)
        if (isInsideBoard(cRank + direction, cFile - 1) && isOpponentPiece(position, cRank + direction, cFile - 1)) {
            moves.add(new Move(this, square, getSquare(cRank + direction, cFile - 1), true, true, false, false));
        }
        if (isInsideBoard(cRank + direction, cFile + 1) && isOpponentPiece(position, cRank + direction, cFile + 1)) {
            moves.add(new Move(this, square, getSquare(cRank + direction, cFile + 1), true, true, false, false));
        }

        //TODO: En passent (not implemented here, needs special rules
        //moves.addAll(checkEnPassent());

        //Promotion (reaching the back rank)
        Set<Move> promotionMoves = checkForPromotion(moves, cRank + direction, endRank);
        moves.addAll(promotionMoves);

        return moves;
    }

    //Helper method to check if a piece is an opponents piece
    private boolean isOpponentPiece(Position pos, int rank, int file) {
        Piece piece = pos.getPieceOn(Square.getSquare(rank, file));
        return piece.isOccupied() && !piece.getColor().equals(this.getColor());
    }

    //Helper Method for checking pawn promotion
    private Set<Move> checkForPromotion(Set<Move> moves, int rank, int endRank) {
        Set<Move> promotionMoves = new HashSet<>();
        if (rank == endRank) {
            for (Move move : moves) {
                //Add promotion moves by creating new Move Objects for each promotion
                promotionMoves.add(new Move(this, move.getFrom(), move.getTo(), move.isCapture(), true, false, move.isCheck()));
            }
        }
        return promotionMoves;
    }
}