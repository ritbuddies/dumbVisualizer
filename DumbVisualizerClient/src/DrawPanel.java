
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class DrawPanel extends JPanel {
  private Color background;

  public DrawPanel (Color background)
  {
	  this.background = background;
  }
  
  public void paintComponent (Graphics g)
  {
    super.paintComponent(g);
    setBackground(background);
  }
}
