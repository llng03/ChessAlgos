package com.progra.chessalgos.chess.chessboard;

import static com.progra.chessalgos.Constants.BOARD_SIZE;

//every Square does exist one time
public enum Square {
    A1(0), B1(1), C1(2), D1(3), E1(4), F1(5), G1(6), H1(7),
    A2(8), B2(9), C2(10), D2(11), E2(12), F2(13), G2(14), H2(15),
    A3(16), B3(17), C3(18), D3(19), E3(20), F3(21), G3(22), H3(23),
    A4(24), B4(25), C4(26), D4(27), E4(28), F4(29), G4(30), H4(31),
    A5(32), B5(33), C5(34), D5(35), E5(36), F5(37), G5(38), H5(39),
    A6(40), B6(41), C6(42), D6(43), E6(44), F6(45), G6(46), H6(47),
    A7(48), B7(49), C7(50), D7(51), E7(52), F7(53), G7(54), H7(55),
    A8(56), B8(57), C8(58), D8(59), E8(60), F8(61), G8(62), H8(63);

    private final int coordinate;

    private Square (int coordinate){
        this.coordinate = coordinate;
    }

    public int rank(){
        return coordinate/ BOARD_SIZE;
    }

    public int file(){
        return coordinate % BOARD_SIZE;
    }

    //outputs the Square to the correct coordinates of the position-array
    public static Square getSquare(int rank, int file){
        int position = rank * BOARD_SIZE + file;
        if (position < 0 || position >= BOARD_SIZE * BOARD_SIZE) {
            throw new IllegalArgumentException("rank or file not valid");
        }
        for (Square sqaure : Square.values()) {
            if (sqaure.coordinate == position) {
                return sqaure;
            }
        }
        throw new IllegalArgumentException("rank or file not valid");
    }

    public static boolean isInsideBoard(int rank, int file){
        return !(rank < 0 || rank >= BOARD_SIZE || file < 0 || file >= BOARD_SIZE);
    }

    //outputs the Square to the correct String, which describes the Square
    public static Square getSquare(String string){
        if(string.length() != 2){
            throw new IllegalArgumentException("this is not a valid square");
        }

        char rankChar = string.charAt(1);
        char fileChar = string.charAt(0);

        int rank = 0;
        int file = 0;

        if(rankChar < '1' || rankChar > '8') {
            throw new IllegalArgumentException("this is not a valid square");
        } else { rank = rankChar - '1'; }

        if(fileChar < 'a' || fileChar > 'h') {
            throw new IllegalArgumentException("this is not a valid square");
        } else { file = fileChar - 'a'; }

        return Square.getSquare(rank, file);
    }
}
