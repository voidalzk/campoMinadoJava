import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sorriso extends JPanel {
    private ImageIcon deadSmiley;
    private ImageIcon smiley;
    private ImageIcon sunglasses;
    private JLabel smileyLabel;

    public Sorriso() {
        deadSmiley = new ImageIcon("deadsmiley.png");
        smiley = new ImageIcon("smiley.png");
        sunglasses = new ImageIcon("sunglasses.png");
        smileyLabel = new JLabel(smiley);

        add(smileyLabel);
    }

    public void setDeadSmiley() {
        smileyLabel.setIcon(deadSmiley);
    }

    public void setSmiley() {
        smileyLabel.setIcon(smiley);
    }

    public void setSunglasses() {
        smileyLabel.setIcon(sunglasses);
    }
}