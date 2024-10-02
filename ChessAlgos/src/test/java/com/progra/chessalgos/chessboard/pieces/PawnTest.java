package com.progra.chessalgos.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import com.progra.chessalgos.chess.chessboard.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.progra.chessalgos.chess.chessboard.Square.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @Test
    @DisplayName("one move ahead")
    void test1(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.setupStartingPosition().build();
        assertThat(pos.getPieceOn(Square.E2).getLegalMoves(pos,Square.E2)).anyMatch(move -> move.getTo().equals(E3));
    }

    @Test
    @DisplayName("two move ahead starting")
    void test2(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.setupStartingPosition().build();
        assertThat(pos.getPieceOn(Square.E7).getLegalMoves(pos, E7)).anyMatch(move -> move.getTo().equals(E5));
    }

    @Test
    @DisplayName("no two moves ahead anywhere else")
    void test3(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putBlackPawnOn(E3).build();
        assertThat(pos.getPieceOn(E4).getLegalMoves(pos,Square.E4)).noneMatch(move -> move.getTo().equals(E2));
    }

    @Test
    @DisplayName("no capture forward")
    void test4(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putBlackPawnOn(E4).putBlackPawnOn(E5).build();
        assertThat(pos.getPieceOn(E4).getLegalMoves(pos,Square.E4)).noneMatch(move -> move.getTo().equals(E5));
    }

    @Test
    @DisplayName("capture diagonally")
    void test5(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putBlackPawnOn(D5).putWhitePawnOn(E4).build();
        assertThat(pos.getPieceOn(D5).getLegalMoves(pos,Square.D5)).anyMatch(move -> move.getTo().equals(E4) && move.isCapture());
    }

    @Test
    @DisplayName("en passent")
    void test6(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putWhitePawnOn(E5).putBlackPawnOn(D5).epPossibleOn(D6).build();
        assertThat(pos.getPieceOn(D5).getLegalMoves(pos, Square.D5)).anyMatch(move -> move.getTo().equals(D6));
    }

    @Test
    @DisplayName("promotion possible")
    void test7(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putWhitePawnOn(D7).build();
        assertThat(pos.getPieceOn(D7).getLegalMoves(pos, Square.D7)).anyMatch(move -> move.getTo().equals(D8));
    }

    @Test
    @DisplayName("cant move two squares ahead when blocked")
    void test8(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().putWhitePawnOn(E2).putBlackQueenOn(E3).build();
        assertThat(pos.getPieceOn(E2).getLegalMoves(pos,E2)).noneMatch(move -> move.getTo().equals(E4));
    }

}
