package main;

import java.util.ArrayList;

public class Graph {
	
	int AnzahlKnoten;
	Object[][] ObjectArray;
	Boolean[][] BooleanArray;
	Färbungsmöglichkeit Färbungsmöglichkeit;
	
	
	 public Graph(int AnzahlKnoten) {
		this.AnzahlKnoten = AnzahlKnoten;
		ObjectArray = new Object[AnzahlKnoten][AnzahlKnoten];
		BooleanArray = new Boolean[AnzahlKnoten][AnzahlKnoten];
	 }


	public Object[][] getGraphObjectArray() {
		return ObjectArray;
	}
	 
	public Boolean[][] getGraphBooleanArray() {
		return BooleanArray;
	} 
	
	public boolean sindVerbunden(int Knoten1, int Knoten2){

        return BooleanArray[Knoten1][Knoten2];
    }

}
