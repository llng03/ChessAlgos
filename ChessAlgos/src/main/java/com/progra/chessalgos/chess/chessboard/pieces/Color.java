package com.progra.chessalgos.chess.chessboard.pieces;

public enum Color {
    WHITE,BLACK;

    public Color change() {
        return this == WHITE ? BLACK : WHITE;
    }
}
