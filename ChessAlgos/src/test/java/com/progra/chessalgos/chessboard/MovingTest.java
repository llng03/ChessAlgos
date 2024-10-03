package com.progra.chessalgos.chessboard;

import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import com.progra.chessalgos.chess.chessboard.Square;
import com.progra.chessalgos.chess.chessboard.pieces.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovingTest {

    @Test
    @DisplayName("King castles correctly to G1")
    void test1(){
        PositionBuilder builder = new PositionBuilder();
        Position pos = builder.takeEmptyBoard().putWhiteKingOn(Square.E1).putWhiteRookOn(Square.H1).build();
        Position newPos = pos.move(Square.E1, Square.G1);
        assertThat(newPos.getPieceOn(Square.G1)).isInstanceOf(King.class);
    }
}
