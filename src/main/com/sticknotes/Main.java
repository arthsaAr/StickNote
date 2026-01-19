package com.sticknotes;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NoteWindow note = new NoteWindow("Stick IT!");
            note.setVisible(true);
        });

        System.out.println("Starting the StickNote application...!");
    }
}