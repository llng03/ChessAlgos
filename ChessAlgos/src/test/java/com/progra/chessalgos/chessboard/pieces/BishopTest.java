package com.progra.chessalgos.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import com.progra.chessalgos.chess.chessboard.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.progra.chessalgos.chess.chessboard.Square.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BishopTest {
    @Test
    @DisplayName("can move over long diagonal")
    void test1(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteBishopOn(A1).build();
        Assertions.assertThat(pos.getPieceOn(A1).getLegalMoves(pos, A1)).anyMatch(move -> move.getTo().equals(H8));
    }

    @Test
    @DisplayName("Can move over a short diagonal")
    void test2(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteBishopOn(B3).build();
        Assertions.assertThat(pos.getPieceOn(B3).getLegalMoves(pos,B3)).anyMatch(move -> move.getTo().equals(D1));
    }

    @Test
    @DisplayName("can not move past an own piece")
    void test3(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteBishopOn(F7).putWhiteRookOn(C4).build();
        Assertions.assertThat(pos.getPieceOn(F7).getLegalMoves(pos, F7)).noneMatch(move -> move.getTo().equals(B3));
    }

    @Test
    @DisplayName("can capture an enemy piece in its way")
    void test4(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteBishopOn(F7).putBlackRookOn(B3).build();
        Assertions.assertThat(pos.getPieceOn(F7).getLegalMoves(pos, F7)).anyMatch(move -> move.getTo().equals(B3) && move.isCapture());
    }

    @Test
    @DisplayName("cannot move past an enemy piece")
    void test5(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteBishopOn(F7).putBlackRookOn(B3).build();
        Assertions.assertThat(pos.getPieceOn(F7).getLegalMoves(pos,F7)).noneMatch(move -> move.getTo().equals(A2));
    }

}
