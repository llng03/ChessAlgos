package com.progra.chessalgos.chess.chessboard;

import com.progra.chessalgos.chess.chessboard.pieces.Piece;

public class Move {
    private Piece actor;
    private Square from;
    private Square to;
    private boolean check = false;
    private boolean capture = false;
    private boolean pawnMove = false;
    private boolean castle = false;

    public Move(Piece actor, Square from, Square to, boolean capture, boolean pawnMove, boolean castle, boolean check) {
        this.actor = actor;
        this.from = from;
        this.to = to;
        this.capture = capture;
        this.pawnMove = pawnMove;
        this.castle = castle;
        this.check = check;
    }

    public Piece getActor(){
        return actor;
    }

    public Square getFrom(){
        return from;
    }

    public Square getTo(){
        return to;
    }

    public boolean isCheck(){
        return check;
    }
    public boolean isCapture(){
        return capture;
    }
    public boolean isPawnMove(){
        return pawnMove;
    }
    public boolean isCastle(){
        return castle;
    }
}
