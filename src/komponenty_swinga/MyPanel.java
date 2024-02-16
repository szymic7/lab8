package komponenty_swinga;

import elementy_graficzne.Kwadrat;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MyPanel extends JPanel {

    private Kwadrat kwadrat = null;

    public Kwadrat getKwadrat() {
        return kwadrat;
    }

    public void setKwadrat(Kwadrat kwadrat) {
        this.kwadrat = kwadrat;
    }

    public MyPanel() {
        initialize();
    }

    public void initialize() {
        this.setSize(new Dimension(800, 600));
        this.setBackground(new Color(229, 221, 195));
        this.setBorder(new LineBorder(Color.BLACK, 3, false));
        this.setLayout(null);
        this.setFocusable(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.ORANGE);
        try {
            if(kwadrat != null) g2d.fillRect(kwadrat.getX(), kwadrat.getY(), kwadrat.getA(), kwadrat.getA());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
