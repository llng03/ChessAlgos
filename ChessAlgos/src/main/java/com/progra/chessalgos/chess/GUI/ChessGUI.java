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
    private JPanel chessBoardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE)) {
        @Override
        public Dimension getPreferredSize() {
            Dimension size = getParent() != null ? getParent().getSize() : super.getSize();
            int squareSize = Math.min(size.width, size.height);
            return new Dimension(squareSize, squareSize);
        }

        @Override
        public void setBounds(int x, int y, int width, int height) {
            int size = Math.min(width, height);
            // centering the board in the panel
            int xOffset = (width - size) / 2;
            int yOffset = (height - size) / 2;
            super.setBounds(x + xOffset, y + yOffset, size, size);
        }
    };

    private Image whitePawnIcon, blackPawnIcon, whiteKnightIcon, blackKnightIcon,
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
        pack();
        setMinimumSize(new Dimension(600, 600));
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
        // Wrapper-Panel with GridBagLayout, das keeps chessboard at the center
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(chessBoardPanel);
        add(wrapperPanel, BorderLayout.CENTER); //Chessboard in the Center
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
        //Scale Icons
        whitePawnIcon = new ImageIcon(getClass().getResource("/images/white-pawn.png")).getImage();
        blackPawnIcon = new ImageIcon(getClass().getResource("/images/black-pawn.png")).getImage();
        whiteKnightIcon = new ImageIcon(getClass().getResource("/images/white-knight.png")).getImage();
        blackKnightIcon = new ImageIcon(getClass().getResource("/images/black-knight.png")).getImage();
        whiteBishopIcon = new ImageIcon(getClass().getResource("/images/white-bishop.png")).getImage();
        blackBishopIcon = new ImageIcon(getClass().getResource("/images/black-bishop.png")).getImage();
        whiteRookIcon = new ImageIcon(getClass().getResource("/images/white-rook.png")).getImage();
        blackRookIcon = new ImageIcon(getClass().getResource("/images/black-rook.png")).getImage();
        whiteQueenIcon = new ImageIcon(getClass().getResource("/images/white-queen.png")).getImage();
        blackQueenIcon = new ImageIcon(getClass().getResource("/images/black-queen.png")).getImage();;
        whiteKingIcon = new ImageIcon(getClass().getResource("/images/white-king.png")).getImage();;;
        blackKingIcon = new ImageIcon(getClass().getResource("/images/black-king.png")).getImage();;;

    }

    private Image getPieceImage(Piece piece) {
        if (piece instanceof Pawn) {
            return piece.getColor() == WHITE ? whitePawnIcon : blackPawnIcon;
        } else if (piece instanceof Rook) {
            return piece.getColor() == WHITE ? whiteRookIcon : blackRookIcon;
        } else if (piece instanceof Knight) {
            return piece.getColor() == WHITE ? whiteKnightIcon : blackKnightIcon;
        } else if (piece instanceof Bishop) {
            return piece.getColor() == WHITE ? whiteBishopIcon : blackBishopIcon;
        } else if (piece instanceof Queen) {
            return piece.getColor() == WHITE ? whiteQueenIcon : blackQueenIcon;
        } else if (piece instanceof King) {
            return piece.getColor() == WHITE ? whiteKingIcon : blackKingIcon;
        }
        return null;
    }

    private void createChessBoard() {
        boolean isWhite = true;
        for(int rank = BOARD_SIZE - 1; rank >= 0; rank--){
            for (int file = 0; file < BOARD_SIZE; file++) {
                JPanel square = new SquarePanel(new GridBagLayout());

                if(isWhite){
                    square.setBackground(Color.WHITE);
                }
                else{
                    square.setBackground(Color.GRAY);
                }

                Piece piece = position.getPieceOn(Square.getSquare(rank, file));

                //Pick Icon for Piece Type
                if (piece != null) {
                    JLabel pieceLabel = new JLabel();
                    pieceLabel.setOpaque(false);

                    // Dynamische Icongröße berechnen
                    square.addComponentListener(new java.awt.event.ComponentAdapter() {
                        @Override
                        public void componentResized(java.awt.event.ComponentEvent e) {
                            int size = Math.min(square.getWidth(), square.getHeight());
                            Image img = getPieceImage(piece);
                            if (img != null && size > 0) {
                                Image scaledImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
                                pieceLabel.setIcon(new ImageIcon(scaledImg));
                                square.revalidate();
                                square.repaint();
                            }
                        }
                    });

                    square.add(pieceLabel, new GridBagConstraints());
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
