package com.techelevator.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class menuDisplayer {
	int currentStock = 50;
	String filePath = "cateringsystem.csv";
	File file = new File(filePath);
	List<ItemObject> returnList = new ArrayList<ItemObject>();
	
	public List<ItemObject> displayCateringItems() {
		try (Scanner itemList = new Scanner(file)) {
			while (itemList.hasNextLine()) {
				String lineToDisplay = itemList.nextLine();
				String[] splitLines = lineToDisplay.split("\\|");
				if(splitLines[3].equals("E")) {
					splitLines[3] = "Entree";
				}
				if(splitLines[3].equals("A")) {
					splitLines[3] = "Appetizer";
				}
				if(splitLines[3].equals("D")) {
					splitLines[3] = "Dessert";
				}	
				if(splitLines[3].equals("B")) {
					splitLines[3] = "Beverage";
				}	

				ItemObject itemObjectASDF = new ItemObject(splitLines[0], splitLines[1],
						Double.parseDouble(splitLines[2]), splitLines[3], currentStock);
				returnList.add(itemObjectASDF);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return returnList;

	}
}
