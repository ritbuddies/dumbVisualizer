
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

class View extends JFrame {

  public void build (Color background)
  {
	setLayout(new BorderLayout());
	setTitle("dumbVisualizer");
	setSize(400,400);
    
	JPanel mainPanel = new DrawPanel(background);
   
	
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JPanel zikaPanel = new DrawPanel(Color.yellow);
    Border border = BorderFactory.createTitledBorder("Zika Virus");
    zikaPanel.setBorder(border);
    
    JButton graph1 = new JButton("Zika Virus Year Vs Count Graph");
    JButton graph2 = new JButton("Zika Virus World distribution");
    JButton graph3 = new JButton("Zika Virus 3");
    JButton graph4 = new JButton("Zika Virus 4");
   
    
    graph1.addActionListener(new ActionListener() { 
    	  public void actionPerformed(ActionEvent e) { 	  
    		 new RestAPIClient().getZikaYearCountReport();
    	  } 
    	});
    
    zikaPanel.add(graph1);
    zikaPanel.add(graph2);
    zikaPanel.add(graph3);
    zikaPanel.add(graph4);
    
    mainPanel.add(zikaPanel);
    
    JPanel zika2Panel = new DrawPanel(Color.LIGHT_GRAY);
    Border border1 = BorderFactory.createTitledBorder("Zika2 Virus");
    zika2Panel.setBorder(border1);
    
    JButton graph11 = new JButton("Zika Virus Year Vs Count Graph");
    JButton graph12 = new JButton("Zika Virus World distribution");
    JButton graph13 = new JButton("Zika Virus 3");
    JButton graph14 = new JButton("Zika Virus 4");
   
    zika2Panel.add(graph11);
    zika2Panel.add(graph12);
    zika2Panel.add(graph13);
    zika2Panel.add(graph14);
    
    mainPanel.add(zika2Panel);
    
    setContentPane(mainPanel);
    setVisible(true);
  }

}