package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.BoxLayout;
import java.awt.Component;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;

public class Hauptfenster extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tableNorth;
	private JTable tableWest;
	public Graph Graph = new Graph(main.landingWindow.KnotenAnzahl);
	
	public Hauptfenster() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
	
		setTitle("Graphenfärbung");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Object Array[][] = new Object[main.frame.KnotenAnzahl][main.frame.KnotenAnzahl];	
		
		//Graph = new Graph(main.landingWindow.KnotenAnzahl);
		
		DefaultTableModel tableModel = new DefaultTableModel(Graph.getGraphObjectArray(), main.landingWindow.KnotenAnzahl);
		
		table = new JTable(tableModel);
		
		
		
		DefaultTableModel tableModelWest = new DefaultTableModel(main.landingWindow.KnotenAnzahl, 1);
		DefaultTableModel tableModelNorth = new DefaultTableModel(1, main.landingWindow.KnotenAnzahl + 1);
		
		tableWest = new JTable(tableModelWest);
		tableWest.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableNorth = new JTable(tableModelNorth);
		tableNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
			
		TableColumn column = null;
		for (int i = 0; i < main.landingWindow.KnotenAnzahl; i++) {
		    column = table.getColumnModel().getColumn(i);
		    column.setPreferredWidth(20);
		    tableNorth.setValueAt(i, 0, i+1);
		    tableWest.setValueAt(i, i, 0);
		}	
		for (int i = 0; i < main.landingWindow.KnotenAnzahl +1; i++) {
		    column = tableNorth.getColumnModel().getColumn(i);
		    column.setPreferredWidth(20);
		    
		}
		
		
		
		column = tableWest.getColumnModel().getColumn(0);
	    column.setPreferredWidth(20);
		
		JPanel TabellenPanel = new JPanel();
		panel_1.add(TabellenPanel);
		TabellenPanel.setLayout(new BorderLayout(0, 0));
		
		TabellenPanel.add(table, BorderLayout.CENTER);
		TabellenPanel.add(tableWest, BorderLayout.WEST);
		TabellenPanel.add(tableNorth, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(5, 0, 0, 0));
		
		JButton btnBerechneMglicheFrbungen = new JButton("Berechne m\u00F6gliche F\u00E4rbungen");
		btnBerechneMglicheFrbungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				
				table.setEnabled(false); //TODO
				
				for (int i = 0; i < main.landingWindow.KnotenAnzahl; i++) {
					for(int a = 0; a < main.landingWindow.KnotenAnzahl; a++) {
						if(table.getValueAt(i, a) == null) table.setValueAt("0", i, a);			
					}
				}
				
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			    for (int i = 0 ; i < main.landingWindow.KnotenAnzahl ; i++)
			        for (int j = 0 ; j < main.landingWindow.KnotenAnzahl ; j++)
			        	Graph.getGraphObjectArray()[i][j] = dtm.getValueAt(i,j);
			    
			    // Übertragung des Inhalts des Object Array in das Boolean Array
			    
			    for (int i = 0; i < main.landingWindow.KnotenAnzahl; i++) {
					for(int a = 0; a < main.landingWindow.KnotenAnzahl; a++) {
						if(table.getValueAt(i, a).equals("1")) {
							Graph.getGraphBooleanArray()[i][a] = true;
						}
						else {
							Graph.getGraphBooleanArray()[i][a] = false;
						}
					}
				}
			    
			    System.out.println(Arrays.deepToString(Graph.getGraphObjectArray()));
			    System.out.println(Arrays.deepToString(Graph.getGraphBooleanArray()));
			    
			    
				
				// Färbung
				
			    Färbungsmöglichkeit Färbungsmöglichkeit = new Färbungsmöglichkeit();
				Objektorientiert ObjektorientierteFärbung = new Objektorientiert();
				Färbungsmöglichkeit.Knotenfarben = ObjektorientierteFärbung.FÃ¤rben(Graph);
				System.out.println(Arrays.toString(Färbungsmöglichkeit.Knotenfarben));
	    
			    
			    try {
					Ergebnis frame = new Ergebnis(Graph.getGraphObjectArray(),Färbungsmöglichkeit.Knotenfarben);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			    
			}
			
		});
		panel_3.add(btnBerechneMglicheFrbungen);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(2);
		panel_3.add(panel);
		
		JButton button = new JButton("Berechne m\u00F6gliche F\u00E4rbungen");
		panel_3.add(button);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setHgap(2);
		flowLayout_1.setVgap(2);
		panel_3.add(panel_2);
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		JLabel lblGenutzteFarben = new JLabel("genutzte Farben:");
		panel_7.add(lblGenutzteFarben);
		
		JLabel lblNewLabel = new JLabel("-");
		panel_7.add(lblNewLabel);
		
		pack();
	}

}
