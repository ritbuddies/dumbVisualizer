
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

class View extends JFrame {

	public void build(Color background) {
		setLayout(new BorderLayout());
		setTitle("dumbVisualizer");
		setSize(450, 400);

		JPanel mainPanel = new DrawPanel(background);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel zikaPanel = new DrawPanel(Color.LIGHT_GRAY);
		Border border = BorderFactory.createTitledBorder("Zika Virus Infections - Existing reports");
		zikaPanel.setBorder(border);

		JButton graph1 = new JButton("Zika Virus Year Vs Count Graph");
		JButton graph2 = new JButton("Zika Virus World Distribution");
		JButton graph3 = new JButton("Zika Virus Internal Country Distribution");
		JButton graph4 = new JButton("Zika Virus Country specific report categories");

		graph1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RestAPIClient().getZikaYearCountReport("1", "1");
			}
		});

		graph2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RestAPIClient().getZikaWorldReport("1", "2");
				;
			}
		});

		graph3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RestAPIClient().getInternalCountryDistribution("1", "3", "United_States");
				;
			}
		});

		graph4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RestAPIClient().getCountrySpecificCategoryData("1", "3", "United_States");
				;
			}
		});
		zikaPanel.add(graph1);
		zikaPanel.add(graph2);
		zikaPanel.add(graph3);
		zikaPanel.add(graph4);

		mainPanel.add(zikaPanel);

		JPanel zikaPanel1 = new DrawPanel(Color.LIGHT_GRAY);
		Border border1 = BorderFactory.createTitledBorder("Zika Virus Infections - Create new reports");
		zikaPanel1.setBorder(border1);

		String[] report_types = new String[] { "Report type", "Line_Chart", "Pie Chart", "Interval Chart",
				"Time-Series chart" };

		JComboBox<String> comboLanguage = new JComboBox<String>(report_types);

		zikaPanel1.add(comboLanguage);

		String[] x_Values = new String[] { "X-Axis Component", "Report_Date", "Location", "Location_Type", "Data_field",
				"Time_Period" };

		JComboBox<String> comboLanguage1 = new JComboBox<String>(x_Values);

		zikaPanel1.add(comboLanguage1);

		String[] y_Values = new String[] { "Y-Axis Component", "Report_Date", "Location", "Location_Type", "Data_field",
				"Time_Period" };

		JComboBox<String> comboLanguage2 = new JComboBox<String>(y_Values);

		zikaPanel1.add(comboLanguage2);

		JButton jButton1 = new JButton("Create new reports");

		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		zikaPanel1.add(jButton1);

		mainPanel.add(zikaPanel1);

		setContentPane(mainPanel);
		setVisible(true);
	}

	public void buildDashBoard(Color background) {
		setLayout(new BorderLayout());
		setTitle("dumbVisualizer-Dashboard");
		setSize(450, 400);

		JPanel mainPanel = new DrawPanel(background);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel zikaPanel1 = new DrawPanel(Color.LIGHT_GRAY);
		Border border = BorderFactory.createTitledBorder("Existing datasets");
		zikaPanel1.setBorder(border);

		// define items in a String array:
		String[] datasets = new String[] { "ZICA_Virus Infections dataset", "HIV/AIDS dataset",
				"Cancer treatment dataset" };

		// create a combo box with the fixed array:
		JComboBox<String> comboLanguage = new JComboBox<String>(datasets);

		zikaPanel1.add(comboLanguage);

		JButton zikaButton1 = new JButton("Launch Reports");

		zikaButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				build(Color.WHITE);
			}
		});

		zikaPanel1.add(zikaButton1);
		mainPanel.add(zikaPanel1);

		JPanel zikaPanel2 = new DrawPanel(Color.LIGHT_GRAY);
		Border border1 = BorderFactory.createTitledBorder("Upload new dataset");
		zikaPanel2.setBorder(border1);

		JButton zikaButton2 = new JButton("Upload new dataset");

		zikaButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		zikaPanel2.add(zikaButton2);
		mainPanel.add(zikaPanel2);

		setContentPane(mainPanel);
		setVisible(true);
	}

}