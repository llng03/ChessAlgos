package com.progra.chessalgos.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import com.progra.chessalgos.chess.chessboard.pieces.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.progra.chessalgos.chess.chessboard.Square.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    @Test
    @DisplayName("can move")
    public void test1() {
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putWhiteKingOn(C8).build();
        assertThat(pos.getPieceOn(C8).getLegalMoves(pos,C8)).anyMatch(move -> move.getTo().equals(C7));
    }

    @Test
    @DisplayName("can castle kingside")
    public void test2() {
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putWhiteKingOn(E1).putWhiteRookOn(H1).build();
        assertThat(pos.getPieceOn(E1).getLegalMoves(pos,E1)).anyMatch(move-> move.isCastle());
    }

    @Test
    @DisplayName("can castle queenside")
    public void test3() {
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putWhiteKingOn(E1).putWhiteRookOn(A1).build();
        assertThat(pos.getPieceOn(E1).getLegalMoves(pos,E1)).anyMatch(move-> move.isCastle());
    }

    @Test
    @DisplayName("no castling without castling rights")
    public void test4() {
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putWhiteKingOn(E1).takeKingsideCastlingRightsFrom(Color.WHITE)
        .takeQueensideCastlingRightsFrom(Color.WHITE).build();
        assertThat(pos.getPieceOn(E1).getLegalMoves(pos,E1)).noneMatch(move-> move.isCastle());
    }

    @Test
    @DisplayName("castles correctly")
    public void test5() {
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putWhiteKingOn(E1).putWhiteRookOn(H1).build();
        assertThat(pos.getPieceOn(E1).getLegalMoves(pos,E1)).anyMatch(move-> move.getTo().equals(G1));
    }

}
