package com.progra.chessalgos.chessboard;

import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
    @Test
    @DisplayName("The starting position is correct")
    void test1(){
        Position starting = new PositionBuilder().setupStartingPosition().build();
        assertThat(starting.toString()).isEqualTo(
                "r n b q k b n r " + "\n" +
                        "p p p p p p p p " + "\n" +
                        "- - - - - - - - " + "\n" +
                        "- - - - - - - - " +"\n" +
                        "- - - - - - - - " +"\n" +
                        "- - - - - - - - " +"\n" +
                        "P P P P P P P P " +"\n" +
                        "R N B Q K B N R " +"\n"
        );

    }
}
