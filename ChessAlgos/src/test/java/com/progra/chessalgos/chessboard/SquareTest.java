package com.progra.chessalgos.chessboard;

import com.progra.chessalgos.chess.chessboard.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SquareTest {
    @Test
    @DisplayName("3-2 as c4")
    void test1(){
        assertThat(Square.getSquare(3,2)).isEqualTo(Square.C4);
    }

    @Test
    @DisplayName("output the correct square to c5")
    void test2(){
        assertThat(Square.getSquare("c5")).isEqualTo(Square.C5);
    }
}
