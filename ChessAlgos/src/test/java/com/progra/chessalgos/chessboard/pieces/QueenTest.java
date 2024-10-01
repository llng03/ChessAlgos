package com.progra.chessalgos.chessboard.pieces;

import com.progra.chessalgos.chess.chessboard.Move;
import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import com.progra.chessalgos.chess.chessboard.Square;
import com.progra.chessalgos.chess.chessboard.pieces.Bishop;
import com.progra.chessalgos.chess.chessboard.pieces.Piece;
import com.progra.chessalgos.chess.chessboard.pieces.Queen;
import com.progra.chessalgos.chess.chessboard.pieces.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.progra.chessalgos.chess.chessboard.pieces.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTest {

    //Test, if queen has the same moves than rook and bishop combined
    @Test
    @DisplayName("bishop and rook moves combined")
    void test1(){
        PositionBuilder positionBuilder = new PositionBuilder();
        Position pos = positionBuilder.takeEmptyBoard().build();

        Rook rook = new Rook(WHITE);
        Bishop bishop = new Bishop(WHITE);
        Queen queen = new Queen(WHITE);


        //Legal Move Sets
        Set<Square> rookMoves = rook.getLegalMoves(pos, Square.C4).stream().map(Move::getTo).collect(Collectors.toSet());
        Set<Square> bishopMoves = bishop.getLegalMoves(pos, Square.C4).stream().map(Move::getTo).collect(Collectors.toSet());
        Set<Square> queenMoves = queen.getLegalMoves(pos, Square.C4).stream().map(Move::getTo).collect(Collectors.toSet());
        Set<Square> combinedMoves = new HashSet<>(rookMoves);
        combinedMoves.addAll(bishopMoves);

        System.out.println(rook.getLegalMoves(pos, Square.C4).toString());
        System.out.println(queen.getLegalMoves(pos, Square.C4).toString());

        assertEquals(rookMoves.size() + bishopMoves.size(), queenMoves.size());
        assertTrue(queenMoves.containsAll(combinedMoves));
    }
}
