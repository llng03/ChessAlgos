package com.progra.chessalgos.chess.GUI;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {
    public SquarePanel(LayoutManager layout) {
        super(layout);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        int size = Math.min(d.width, d.height);
        return new Dimension(size, size);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        int size = Math.min(width, height);
        super.setBounds(x, y, size, size);
    }
}

