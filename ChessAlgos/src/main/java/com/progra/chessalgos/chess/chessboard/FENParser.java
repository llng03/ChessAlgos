package com.progra.chessalgos.chess.chessboard;

import static com.progra.chessalgos.Constants.BOARD_SIZE;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.BLACK;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.WHITE;

//Reads a FEN Document and makes a position out of it
public class FENParser {
    private PositionBuilder setupPosition(PositionBuilder builder, String pos){
        int counter = 0;
        for(int rank = BOARD_SIZE - 1; rank >= 0; rank--){
            int file = 0;

            while(counter < pos.length() && pos.charAt(counter) != '/'){
                if(file >= BOARD_SIZE){
                    throw new IllegalArgumentException("This is not a correct FEN: The position is not correct");
                }
                char c = pos.charAt(counter);
                switch(c){
                    case 'r': builder = builder.putBlackRookOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'R': builder = builder.putWhiteRookOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'q': builder = builder.putBlackQueenOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'Q': builder = builder.putWhiteQueenOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'k': builder = builder.putBlackKingOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'K': builder = builder.putWhiteKingOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'n': builder = builder.putBlackKnightOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'N': builder = builder.putWhiteKnightOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'b': builder = builder.putBlackBishopOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'B': builder = builder.putWhiteBishopOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    case 'p': builder = builder.putBlackPawnOn(Square.getSquare(rank,file));
                    file++; counter++;
                        break;
                    case 'P': builder = builder.putWhitePawnOn(Square.getSquare(rank,file));
                        file++; counter++;
                        break;
                    default:
                        //numbers of empty sqaures
                        //if c is not a number between 1 and 8
                        if (c < 49 || c > 56){
                            throw new IllegalArgumentException("This is not a correct FEN: the position is not correct");
                        }
                        //add the number of empty sqaures to the file counter, so the next loop can continue
                        //with the next piece
                        file += (c - 48);
                        counter++;
                }
            }
            counter++;
        }

        return builder;
    }

    private PositionBuilder setToMove(PositionBuilder builder, String color){
        return builder.toMove(color.equals("w")? WHITE : BLACK);
    }

    //this method is only called if the ep-part of the file is not "-" so the content always has to be 2 chars long
    private PositionBuilder setEp(PositionBuilder builder, String ep){
        if(ep.length() != 2){
            throw new IllegalArgumentException("The ep part of this fen is no valid square");
        }
        if(ep.charAt(0) < 97 || ep.charAt(0) > 104){
            throw new IllegalArgumentException("The ep part of this fen is no valid square");
        }
        int file = ep.charAt(0) - 97;

        if(ep.charAt(1) < 49 || ep.charAt(1) > 56){
            throw new IllegalArgumentException("The ep part of this fen is no valid square");
        }
        int rank = ep.charAt(1) - 49;

        return builder.epPossibleOn(Square.getSquare(rank, file));
    }

    private PositionBuilder setCastlingRights(PositionBuilder builder, String castlingRights){
        if(! castlingRights.contains("K")){
            builder.takeKingsideCastlingRightsFrom(WHITE);
        }

        if(! castlingRights.contains("k")){
            builder.takeKingsideCastlingRightsFrom(BLACK);
        }

        if(! castlingRights.contains("Q")){
            builder.takeQueensideCastlingRightsFrom(WHITE);
        }

        if(! castlingRights.contains("q")){
            builder.takeQueensideCastlingRightsFrom(BLACK);
        }
        return builder;
    }
    public Position parse(String fen){
        String[] parts = fen.split(" ");

        if(parts.length != 6){
            throw new IllegalArgumentException("This is not a correct fen");
        }

        String pos = parts[0];
        String toMove = parts[1];
        String castlingRights = parts[2];
        String ep = parts[3];
        String halfMovesSinceCaptureOrPawn = parts[4];
        String moveNumber = parts[5];

        PositionBuilder builder = new PositionBuilder().takeEmptyBoard();
        builder = setupPosition(builder, pos);
        builder = setToMove(builder, toMove);
        if(!ep.equals("-")){
            builder = setEp(builder, ep);
        }
        if(!castlingRights.equals("KQkq")){
            builder = setCastlingRights(builder, castlingRights);
        }

        try{Integer.parseInt(halfMovesSinceCaptureOrPawn);
            Integer.parseInt(moveNumber);
        } catch(IllegalArgumentException e) {
            System.out.println("Half Moves or Moves are not valid in this fen");
        };

        return builder
                .setHalfMovesSinceCaptureOrPawn(Integer.parseInt(halfMovesSinceCaptureOrPawn))
                .setMoveNumber(Integer.parseInt(moveNumber))
                .build();

    }


}
