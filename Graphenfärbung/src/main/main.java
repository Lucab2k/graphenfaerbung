package main;

import java.text.ParseException;

public class main {

	public static landingWindow landingWindow;
	
	public static void main(String[] args) {
		landingWindow = null;
		try {
			landingWindow = new landingWindow();
			landingWindow.setVisible(true);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
