package komponenty_swinga;

import elementy_graficzne.Kwadrat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements ActionListener, KeyListener {

    private JPanel panel;
    private MyPanel myPanel;
    private JButton dodajFigure;
    private int ruchFigury = 0;
    private Thread thread;
    public static final Font font = new Font("Arial", Font.BOLD, 14);


    public MyFrame() {
        initialize();
    }

    public void initialize() {

        // Frame
        this.setTitle("Tytuł");
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setFocusable(false);
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(panel);


        // MyPanel - płótno
        myPanel = new MyPanel();
        myPanel.setBounds(20, 20, myPanel.getWidth(), myPanel.getHeight());
        myPanel.setFocusable(false);
        panel.add(myPanel);


        // dodajFigure - JButton
        dodajFigure = new JButton("Dodaj figurę");
        dodajFigure.setFont(font);
        dodajFigure.setFocusable(false);
        dodajFigure.setBounds(840, 50, 120, 50);
        panel.add(dodajFigure);
        dodajFigure.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(myPanel.getKwadrat() == null) {
            myPanel.setKwadrat(new Kwadrat(myPanel.getWidth() / 2, myPanel.getHeight() / 2));
            dodajFigure.setEnabled(false);
            thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    while(myPanel.getKwadrat() != null) {

                        switch (ruchFigury) {
                            case 1: // ruch w prawo
                                myPanel.getKwadrat().setX(myPanel.getKwadrat().getX() + 10);
                                break;
                            case 2: // ruch w lewo
                                myPanel.getKwadrat().setX(myPanel.getKwadrat().getX() - 10);
                                break;
                            case 3: // ruch w gore
                                myPanel.getKwadrat().setY(myPanel.getKwadrat().getY() - 10);
                                break;
                            case 4: // ruch w dol
                                myPanel.getKwadrat().setY(myPanel.getKwadrat().getY() + 10);
                                break;
                            default:
                                break;
                        }
                        myPanel.repaint();

                        if(myPanel.getKwadrat().getX() <= -40 || myPanel.getKwadrat().getX() >= 840 ||
                            myPanel.getKwadrat().getY() <= -40 || myPanel.getKwadrat().getY() >= 640) {
                            myPanel.setKwadrat(null);
                            dodajFigure.setEnabled(true);
                            ruchFigury = 0;
                        }

                        try {
                            Thread.sleep(30);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            });
            thread.start();
            myPanel.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if(myPanel.getKwadrat() != null) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    ruchFigury = 1;
                    break;
                case KeyEvent.VK_LEFT:
                    ruchFigury = 2;
                    break;
                case KeyEvent.VK_UP:
                    ruchFigury = 3;
                    break;
                case KeyEvent.VK_DOWN:
                    ruchFigury = 4;
                    break;
                default:
                    break;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame().setVisible(true);
            }
        });
    }

}
