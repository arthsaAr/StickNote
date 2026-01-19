package com.sticknotes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NoteWindow extends JFrame {
    private JTextArea textArea;
    private int mouseX, mouseY;

    public NoteWindow(String title){
        super(title);

        //setting window properties
        this.setSize(300, 300);
        this.setMinimumSize(new Dimension(300, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);  
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(37,37,37)); //dark themed background
        this.setContentPane(mainPanel);

        //after removing the default title bar(the X, minimize buttons), i will add a custom titlebar
        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(45, 45,45));
        titleBar.setLayout(new BorderLayout());
        titleBar.setPreferredSize(new Dimension(this.getWidth(), 40));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        titleBar.add(titleLabel, BorderLayout.WEST);

        JButton closeButton = new JButton("X");
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(45, 45,45));
        closeButton.setBorder(null);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setOpaque(true);
        closeButton.setPreferredSize(new Dimension(45,40));

        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                closeButton.setBackground(Color.RED);
            }
            public void mouseExited(MouseEvent e){
                closeButton.setBackground(new Color(30, 30, 30));
            }
        });

        closeButton.addActionListener(e -> dispose());
        titleBar.add(closeButton, BorderLayout.EAST);

        mainPanel.add(titleBar, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(50, 50, 50)); // dark text area
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        //making the note draggable
        titleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                mouseX = e.getXOnScreen() - getX();
                mouseY = e.getYOnScreen() - getY();
            }
        });

        titleBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e){
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                setLocation(x, y);
            }
        });


        setResizable(true);
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text){
        textArea.setText(text);
    }
}