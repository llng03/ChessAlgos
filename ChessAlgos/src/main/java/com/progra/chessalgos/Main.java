package com.progra.chessalgos;

import com.progra.chessalgos.chess.GUI.ChessGUI;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		//Create and show the GUI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ChessGUI().setVisible(true);
			}
		});
	}

}
