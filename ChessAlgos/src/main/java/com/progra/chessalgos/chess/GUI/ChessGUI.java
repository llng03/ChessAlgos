package com.progra.chessalgos.chess.GUI;


import com.progra.chessalgos.chess.chessboard.Position;
import com.progra.chessalgos.chess.chessboard.PositionBuilder;
import com.progra.chessalgos.chess.chessboard.Square;
import com.progra.chessalgos.chess.chessboard.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.progra.chessalgos.Constants.BOARD_SIZE;
import static com.progra.chessalgos.chess.chessboard.pieces.Color.WHITE;

public class ChessGUI extends JFrame {
    //The JFrame is the Main Window of the Chess Programm

    //GUI Components
    private JButton startButton = new JButton("Start Game");
    private JButton endButton = new JButton("Quit Game");
    private JLabel infoLabel = new JLabel("Your Move: ");
    private JPanel chessBoardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
    private ImageIcon whitePawnIcon, blackPawnIcon, whiteKnightIcon, blackKnightIcon,
            whiteBishopIcon, blackBishopIcon, whiteRookIcon, blackRookIcon, whiteQueenIcon,
            blackQueenIcon, whiteKingIcon, blackKingIcon;

    //Chess Position
    private Position position = new PositionBuilder().setupStartingPosition().build();

    private JPanel[][] squares = new JPanel[BOARD_SIZE][BOARD_SIZE]; //Array for the Board-Squares
    private Square selectedStartSquare = null; //First picked Square
    private boolean isFirstClick = true; //State of Click

    //Constructor
    public ChessGUI() {
        setTitle("Chessgame");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        loadPieceIcons();

        //create Chessboard
        createChessBoard();


        //Panel for Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(endButton);

        //End Button am Anfang deaktivieren
        endButton.setVisible(false);

        //Info Label (middle)
        JPanel infoPanel = new JPanel();
        infoPanel.add(infoLabel);

        //add all Panels to the JFrame
        add(chessBoardPanel, BorderLayout.CENTER); //Chessboard in the Center
        add(infoPanel, BorderLayout.NORTH); //Info Label on Upper Side
        add(buttonPanel, BorderLayout.PAGE_END); //Buttons on the Bottom

        //ActionListener for the Start-Button
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateChessBoard();//update Chess Position
                infoLabel.setText("Your Move: ");
                startButton.setVisible(false);  //hide Start-Button
                endButton.setVisible(true); //make quitButton visible
            }
        });

        //ActionListener for Quit-Game-Button:
        endButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Closes the Game
                System.exit(0);
            }
        });

    }

    private void loadPieceIcons() {
        ImageIcon whitePawnIconOrig = new ImageIcon(getClass().getResource("/images/white-pawn.png"));
        ImageIcon blackPawnIconOrig = new ImageIcon(getClass().getResource("/images/black-pawn.png"));
        ImageIcon whiteKnightIconOrig = new ImageIcon(getClass().getResource("/images/white-knight.png"));
        ImageIcon blackKnightIconOrig = new ImageIcon(getClass().getResource("/images/black-knight.png"));
        ImageIcon whiteBishopIconOrig = new ImageIcon(getClass().getResource("/images/white-bishop.png"));
        ImageIcon blackBishopIconOrig = new ImageIcon(getClass().getResource("/images/black-bishop.png"));
        ImageIcon whiteRookIconOrig = new ImageIcon(getClass().getResource("/images/white-rook.png"));
        ImageIcon blackRookIconOrig = new ImageIcon(getClass().getResource("/images/black-rook.png"));
        ImageIcon whiteQueenIconOrig = new ImageIcon(getClass().getResource("/images/white-queen.png"));
        ImageIcon blackQueenIconOrig = new ImageIcon(getClass().getResource("/images/black-queen.png"));
        ImageIcon whiteKingIconOrig = new ImageIcon(getClass().getResource("/images/white-king.png"));
        ImageIcon blackKingIconOrig = new ImageIcon(getClass().getResource("/images/black-king.png"));


        //Scale Icons
        whitePawnIcon = new ImageIcon(whitePawnIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));
        blackPawnIcon = new ImageIcon(blackPawnIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));
        whiteKnightIcon = new ImageIcon(whiteKnightIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));
        blackKnightIcon = new ImageIcon(blackKnightIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));;
        whiteBishopIcon = new ImageIcon(whiteBishopIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));;
        blackBishopIcon = new ImageIcon(blackBishopIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));;;
        whiteRookIcon = new ImageIcon(whiteRookIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));;;
        blackRookIcon = new ImageIcon(blackRookIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));;;
        whiteQueenIcon = new ImageIcon(whiteQueenIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));;;
        blackQueenIcon = new ImageIcon(blackQueenIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));;;;
        whiteKingIcon = new ImageIcon(whiteKingIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));;;;
        blackKingIcon = new ImageIcon(blackKingIconOrig.getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH));;;;

    }

    private void createChessBoard() {
        boolean isWhite = true;
        for(int rank = BOARD_SIZE - 1; rank >= 0; rank--){
            for (int file = 0; file < BOARD_SIZE; file++) {
                JPanel square = new JPanel();
                if(isWhite){
                    square.setBackground(Color.WHITE);
                }
                else{
                    square.setBackground(Color.GRAY);
                }

                Piece piece = position.getPieceOn(Square.getSquare(rank, file));

                //Pick Icon for Piece Type
                if(piece != null){
                    JLabel pieceLabel = new JLabel();
                    pieceLabel.setOpaque(false); //make it transparent

                    if(piece instanceof Pawn){
                        if(piece.getColor() == WHITE){
                            pieceLabel.setIcon(whitePawnIcon);
                        } else{
                            pieceLabel.setIcon(blackPawnIcon);
                        }
                    } else if (piece instanceof Rook) {
                        if (piece.getColor() == WHITE) {
                            pieceLabel.setIcon(whiteRookIcon);
                        } else {
                            pieceLabel.setIcon(blackRookIcon);
                        }
                    } else if (piece instanceof Knight) {
                        if (piece.getColor() == WHITE) {
                            pieceLabel.setIcon(whiteKnightIcon);
                        } else {
                            pieceLabel.setIcon(blackKnightIcon);
                        }
                    } else if (piece instanceof Bishop) {
                        if (piece.getColor() == WHITE) {
                            pieceLabel.setIcon(whiteBishopIcon);
                        } else {
                            pieceLabel.setIcon(blackBishopIcon);
                        }
                    } else if (piece instanceof Queen) {
                        if (piece.getColor() == WHITE) {
                            pieceLabel.setIcon(whiteQueenIcon);
                        } else {
                            pieceLabel.setIcon(blackQueenIcon);
                        }
                    } else if (piece instanceof King) {
                        if (piece.getColor() == WHITE) {
                            pieceLabel.setIcon(whiteKingIcon);
                        } else {
                            pieceLabel.setIcon(blackKingIcon);
                        }
                    }

                    square.add(pieceLabel);
                }

                //Mouse Listener for Squares
                final int currentRank = rank;
                final int currentFile = file;

                square.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        handleSquareClick(currentRank, currentFile);
                    }
                });

                chessBoardPanel.add(square);
                squares[rank][file] = square;

                isWhite = !isWhite; //change Color

            }
            isWhite = !isWhite;
        }
    }

    private void handleSquareClick(int rank, int file) {
        Square clickedSquare = Square.getSquare(rank, file);
        if(isFirstClick){
            //First Click: Pick Square
            selectedStartSquare = clickedSquare;
            if(position.getPieceOn(selectedStartSquare).isOccupied()){
                infoLabel.setText("Selected start: " + selectedStartSquare.toString());
                isFirstClick = false; //Next Click is targetSquare
            } else{
                infoLabel.setText("This Square is not occupied. Pick another Square");
            }
        } else {
            //Second Click: Choose target square and make move
            Square selectedTargetSquare = clickedSquare;
            infoLabel.setText("Moving to: " + selectedTargetSquare.toString());

            try{
                Position newPosition = position.move(selectedStartSquare, selectedTargetSquare);
                position = newPosition; //Update Position
                updateChessBoard();
                isFirstClick = true;
                infoLabel.setText("Your Move: ");
            } catch(IllegalArgumentException iae){
                infoLabel.setText("Illegal move, try again.");
                isFirstClick = true;
            }
        }
    }

    private void updateChessBoard() {
        chessBoardPanel.removeAll();
        createChessBoard();
        chessBoardPanel.revalidate();
        chessBoardPanel.repaint();
    }



}
