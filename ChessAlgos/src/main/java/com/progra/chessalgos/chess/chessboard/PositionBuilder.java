package com.progra.chessalgos.chess.chessboard;

import com.progra.chessalgos.chess.chessboard.pieces.*;

import static com.progra.chessalgos.Constants.BOARD_SIZE;
import static com.progra.chessalgos.chess.chessboard.Square.*;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.BLACK;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.WHITE;

public class PositionBuilder {
    private Piece[][] position;
    private Color toMove = WHITE;
    private boolean[] castlingRights = {true, true, true, true}; //white kingside, white queenside, black kingside, black queenside
    private Square ep;
    private int halfMovesSinceCaptureOrPawn = 0;
    private int moveNumber = 1;
    private static final Empty empty = new Empty();

    private void changeToMove(){
        this.toMove = toMove == WHITE? BLACK : WHITE;
    }

    private void putPiece(Piece piece, Square square){
        this.position[square.rank()][square.file()] = piece;
    }
    public PositionBuilder takeEmptyBoard(){
        Piece[][] emptyBoard = new Piece[BOARD_SIZE][BOARD_SIZE];
        for(int rank = 0; rank < BOARD_SIZE; rank++){
            for(int file = 0; file < BOARD_SIZE; file++){
                emptyBoard[rank][file] = empty;
            }
        }

        this.position = emptyBoard;
        return this;
    }

    public PositionBuilder toMove(Color color){
        this.toMove = color;
        return this;
    }
    public PositionBuilder epPossibleOn(Square square){
        this.ep = square;
        return this;
    }
    public PositionBuilder takeKingsideCastlingRightsFrom(Color color){
        if(color == WHITE){
            this.castlingRights[0] = false;
        }
        else{
            this.castlingRights[2] =false;
        }
        return this;
    }

    public PositionBuilder takeQueensideCastlingRightsFrom(Color color){
        if(color == WHITE){
            this.castlingRights[1] = false;
        }
        else{
            this.castlingRights[3] = false;
        }
        return this;
    }

    public PositionBuilder setHalfMovesSinceCaptureOrPawn(int number){
        this.halfMovesSinceCaptureOrPawn = number;
        return this;
    }

    public PositionBuilder setMoveNumber(int number){
        this.moveNumber = number;
        return this;
    }

    public PositionBuilder putWhiteRookOn(Square square){
        putPiece(new Rook(WHITE), square);
        return this;
    }

    public PositionBuilder putBlackRookOn(Square square){
        putPiece(new Rook(BLACK), square);
        return this;
    }

    public PositionBuilder putWhitePawnOn(Square square){
        putPiece(new Pawn(WHITE), square);
        return this;
    }

    public PositionBuilder putBlackPawnOn(Square square){
        putPiece(new Pawn(BLACK), square);
        return this;
    }

    public PositionBuilder putWhiteQueenOn(Square square){
        putPiece(new Queen(WHITE), square);
        return this;
    }

    public PositionBuilder putBlackQueenOn(Square square){
        putPiece(new Queen(BLACK), square);
        return this;
    }

    public PositionBuilder putWhiteKingOn(Square square){
        putPiece(new King(WHITE), square);
        return this;
    }

    public PositionBuilder putBlackKingOn(Square square){
        putPiece(new King(BLACK), square);
        return this;
    }

    public PositionBuilder putWhiteKnightOn(Square square){
        putPiece(new Knight(WHITE), square);
        return this;
    }
    public PositionBuilder putBlackKnightOn(Square square){
        putPiece(new Knight(BLACK), square);
        return this;
    }

    public PositionBuilder putWhiteBishopOn(Square square){
        putPiece(new Bishop(WHITE), square);
        return this;
    }

    public PositionBuilder putBlackBishopOn(Square square){
        putPiece(new Bishop(BLACK), square);
        return this;
    }

    public PositionBuilder setupStartingPosition(){
        takeEmptyBoard();

        putWhiteRookOn(A1);
        putWhiteKnightOn(B1);
        putWhiteBishopOn(C1);
        putWhiteQueenOn(D1);
        putWhiteKingOn(E1);
        putWhiteBishopOn(F1);
        putWhiteKnightOn(G1);
        putWhiteRookOn(H1);
        putWhitePawnOn(A2);
        putWhitePawnOn(B2);
        putWhitePawnOn(C2);
        putWhitePawnOn(D2);
        putWhitePawnOn(E2);
        putWhitePawnOn(F2);
        putWhitePawnOn(G2);
        putWhitePawnOn(H2);

        putBlackRookOn(A8);
        putBlackKnightOn(B8);
        putBlackBishopOn(C8);
        putBlackQueenOn(D8);
        putBlackKingOn(E8);
        putBlackBishopOn(F8);
        putBlackKnightOn(G8);
        putBlackRookOn(H8);
        putBlackPawnOn(A7);
        putBlackPawnOn(B7);
        putBlackPawnOn(C7);
        putBlackPawnOn(D7);
        putBlackPawnOn(E7);
        putBlackPawnOn(F7);
        putBlackPawnOn(G7);
        putBlackPawnOn(H7);

        return this;
    }


    public PositionBuilder changePosition(Piece[][] oldPosition,
                                          Piece piece, Square from, Square target){
        Piece[][] newPosition = new Piece[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i< BOARD_SIZE; i++){
            System.arraycopy(oldPosition[i], 0,
                    newPosition[i], 0, BOARD_SIZE);
        }

        newPosition[from.rank()][from.file()] = empty;
        newPosition[target.rank()][target.file()] = piece;

        this.position = newPosition;
        return this;
    }

    public Position build(){
        return new Position(position, toMove, castlingRights, ep, halfMovesSinceCaptureOrPawn, moveNumber);
    }
}
