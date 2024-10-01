package com.progra.chessalgos.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import com.progra.chessalgos.chess.chessboard.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.progra.chessalgos.chess.chessboard.Square.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {
    @Test
    @DisplayName("c8 - f6")
    void test1(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.setupStartingPosition().build();
        assertThat(pos.getPieceOn(Square.G8).getLegalMoves(pos, Square.G8)).anyMatch(move -> move.getTo().equals(F6));
    }

    @Test
    @DisplayName("can capture")
    void test2(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putWhiteKnightOn(C4).putBlackQueenOn(E3).build();
        assertThat(pos.getPieceOn(C4).getLegalMoves(pos, C4)).anyMatch(move -> move.getTo().equals(E3) && move.isCapture());
    }

    @Test
    @DisplayName("ally piece on square -> no move")
    void test3(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.setupStartingPosition().build();
        assertThat(pos.getPieceOn(C8).getLegalMoves(pos, C8)).noneMatch(move -> move.getTo().equals(E7));
    }
}
