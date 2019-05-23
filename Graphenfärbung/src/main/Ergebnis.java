package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

public class Ergebnis extends JFrame {

	private JPanel contentPane;
	private JTable Ergebnistabelle;
	private JTable TabelleNorth;
	private JTable TabelleWest;
	private Object[][] ObjectArray;
	private Object[][] ErgebnisArray;


	public Ergebnis(Object[][] ObjectArray, int[] knotenfarben) {
		this.ObjectArray = ObjectArray;
		setTitle("Ergebnis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ErgebnisArray = new Object[main.landingWindow.KnotenAnzahl * 3][main.landingWindow.KnotenAnzahl];

		DefaultTableModel tableModel = new DefaultTableModel(ErgebnisArray, main.landingWindow.KnotenAnzahl);
		
		Ergebnistabelle = new JTable(tableModel);
		
		class ColorRenderer extends DefaultTableCellRenderer {
			private int i;
			   public ColorRenderer(int i) {
				this.i = i-1;
			}

			public Component getTableCellRendererComponent(
			            JTable table, Object value, boolean isSelected,
			            boolean hasFocus, int row, int column)
			   {
			      
				   if(ObjectArray[0][i].equals("1")) {
						setBackground(Color.GREEN);
					}
					else {
						setBackground(Color.RED);
					}
			  
			      return super.getTableCellRendererComponent(table, value, isSelected,
			                                                 hasFocus, row, column);
			   }
		}
	
		int j = -3;
		int x = -2;
		int y = -1;
	    for (int i = 0; i < main.landingWindow.KnotenAnzahl; i++) {
	    	j = j + 3; x = x + 3; y = y + 3;
			for(int a = 0; a < main.landingWindow.KnotenAnzahl; a++) {
				Ergebnistabelle.setValueAt(ObjectArray[i][a], a, j);
				if(ObjectArray[i][a].equals("1") && a != i) {
				Ergebnistabelle.setValueAt(knotenfarben[a], a, x);
				Ergebnistabelle.setValueAt(knotenfarben[i], a, y);
				}
				}
			}
		
		DefaultTableModel tableModelWest = new DefaultTableModel(main.landingWindow.KnotenAnzahl, 1);
		DefaultTableModel tableModelNorth = new DefaultTableModel(1, main.landingWindow.KnotenAnzahl+ 1);
		
		TabelleWest = new JTable(tableModelWest);
		TabelleWest.setBorder(new LineBorder(new Color(0, 0, 0)));
		TabelleNorth = new JTable(tableModelNorth);
		TabelleNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
			
		TableColumn column = null;
		TableColumn columnNorth = null;
		TableColumn columnWest = null;
		for (int i = 0; i < main.landingWindow.KnotenAnzahl * 3; i++) {
		    column = Ergebnistabelle.getColumnModel().getColumn(i);
		    column.setMaxWidth(15);
		    
		}
		for (int i = 0; i < main.landingWindow.KnotenAnzahl; i++) {
		    TabelleWest.setValueAt(i, i, 0);
		}
		for (int i = 0; i < main.landingWindow.KnotenAnzahl+1; i++) {
		    columnNorth = TabelleNorth.getColumnModel().getColumn(i);
		    columnNorth.setMaxWidth(45);
		}
		for (int i = 0; i < main.landingWindow.KnotenAnzahl; i++) {
		    TabelleNorth.setValueAt(i, 0, i+1);
		}
		
		
		columnWest = TabelleWest.getColumnModel().getColumn(0);
	    columnWest.setMaxWidth(45);
	    		
		contentPane.add(Ergebnistabelle, BorderLayout.CENTER);
		contentPane.add(TabelleNorth, BorderLayout.NORTH);
		contentPane.add(TabelleWest, BorderLayout.WEST);
		
		pack();
	}

}
