package com.progra.chessalgos.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import com.progra.chessalgos.chess.chessboard.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.progra.chessalgos.chess.chessboard.Square.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

    @Test
    @DisplayName("The Rook can move vertically")
    void test1(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteRookOn(C4).build();
        assertThat(pos.getPieceOn(C4).getLegalMoves(pos, C4)).anyMatch(move -> move.getTo().equals(A4));
    }


    @Test
    @DisplayName("The Rook can move horizontally")
    void test2(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteRookOn(F7).build();
        assertThat(pos.getPieceOn(F7).getLegalMoves(pos, F7)).anyMatch(move -> move.getTo().equals(F1));
    }

    @Test
    @DisplayName("The Rook can not move past an own piece")
    void test3(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteRookOn(F7).putWhiteRookOn(F2).build();
        assertThat(pos.getPieceOn(F7).getLegalMoves(pos, F7)).noneMatch(move -> move.getTo().equals(F1));
    }

    @Test
    @DisplayName("The Rook can capture an enemy piece in its way")
    void test4(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteRookOn(F7).putBlackRookOn(F2).build();
        assertThat(pos.getPieceOn(F7).getLegalMoves(pos, F7)).anyMatch(move -> move.getTo().equals(F2) && move.isCapture());
    }
}
