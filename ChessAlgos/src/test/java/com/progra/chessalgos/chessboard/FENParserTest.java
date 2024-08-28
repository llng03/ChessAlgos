package com.progra.chessalgos.chessboard;

import com.progra.chessalgos.chess.chessboard.FENParser;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import com.progra.chessalgos.chess.chessboard.Square;
import com.progra.chessalgos.chess.chessboard.pieces.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.progra.chessalgos.chess.chessboard.pieces.Color.BLACK;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

public class FENParserTest {
    private void testPattern(String fen, String pos) {
        FENParser parser = new FENParser();
        Position position = parser.parse(fen);
        assertThat(position.toString()).isEqualTo(pos);
    }

    @Test
    @DisplayName("Parses the StartingPosition correctly")
    void test1() {
        testPattern("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new PositionBuilder().setupStartingPosition().build().toString());
    }

    @Test
    @DisplayName("Parses my tournament position correctly")
    void test2() {
        testPattern("r3k1r1/pp1bppQ1/2np4/q1p4R/4P1p1/2NP1P2/PPP3B1/2KR4 b - - 4 22",
                "r - - - k - r - " + "\n" +
                        "p p - b p p Q - " + "\n" +
                        "- - n p - - - - " + "\n" +
                        "q - p - - - - R " + "\n" +
                        "- - - - P - p - " + "\n" +
                        "- - N P - P - - " + "\n" +
                        "P P P - - - B - " + "\n" +
                        "- - K R - - - - " + "\n");
    }

    @Test
    @DisplayName("makes black to move")
    void test3(){
        FENParser parser = new FENParser();
        Position position = parser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1");
        assertThat(position.getToMove()).isEqualTo(BLACK);
    }

    @Test
    @DisplayName("scan position with ep correctly")
    void test4(){
        FENParser parser = new FENParser();
        Position position = parser.parse("r1bqk2r/ppp2ppp/2n2n2/2bpP3/2Bp4/2P2N2/PP3PPP/RNBQK2R w KQkq d6 0 7");
        assertThat(position.getEp()).isEqualTo(Square.D6);
    }

    @Test
    @DisplayName("castling Rights")
    void test5(){
        FENParser parser = new FENParser();
        Position position = parser.parse("r1bqk2r/ppp2ppp/2n2n2/2bpP3/2Bp4/2P2N2/PP3PPP/RNBQK2R w Kkq d6 0 7");
        assertThat(position.queensideCastleRight(WHITE)).isFalse();
     }
}
