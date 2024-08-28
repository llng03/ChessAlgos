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
}
