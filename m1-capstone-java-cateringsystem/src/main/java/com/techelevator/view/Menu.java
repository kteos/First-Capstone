package com.techelevator.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

	Scanner input = new Scanner(System.in);
	double selection = 0.0;
	public boolean executed = false;
	public boolean verifyIsAnItem = true;
	double totalItemPrice = 0;
	double totalReceiptPrice = 0;
	String selectedProduct = "";

	public double getDoubleInput() {
		do {
			try {
				selection = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please input a valid integer");
			}
			input.nextLine();
			return selection;
		} while (selection > 0 || selection <= 5000);
	}

	public String getStringInput(List<ItemObject> listArray) {
		do {
			try {
				selectedProduct = input.nextLine();
				printOneItem2read(listArray,selectedProduct);
			} catch (InputMismatchException e) {
				System.out.println("Enter a valid product code");
			}
			return selectedProduct;
		} while (verifyIsAnItem);
	}

	public void displayMainMenuToRead() {
		System.out.println("(1) Display Catering Items");
		System.out.println("(2) Order");
		System.out.println("(3) Quit");
	}

	public void printItems2read(List<ItemObject> listArray) {
		for (ItemObject i : listArray) {
			System.out.printf("%s\t%s\t$%.2f\t%s\t%s\n", i.getProductCode(), i.getName(), i.getItemPrice(),
					i.getProductType(), i.getCurrentStock());
		}
		System.out.println();
	}

	public void printOneItem2read(List<ItemObject> listArray, String product) {
		for (ItemObject i : listArray) {
			if (i.getProductCode().equals(product)) {
				System.out.printf("%s\t%s\t$%.2f\t%s\t%s\n", i.getProductCode(), i.getName(), i.getItemPrice(),
						i.getProductType(), i.getCurrentStock());
				verifyIsAnItem = true;
				break;
			}
		}
		verifyIsAnItem = false;
	}

	public void displayRegister(cashRegister register) {
		System.out.println();
		System.out.println("(1) Add Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Transaction");
		System.out.printf("Current Account Balance: $%.2f\n", register.getBalance());
		executed = true;
	}

	public void displayReceipt(List<ItemObject> listArray) {
		System.out.printf("Thank you for your business!\nItems purchased:\n");
		for (ItemObject i : listArray) {

			int amountOfItems = ((i.getCurrentStock()) * -1) + 50;
			totalItemPrice = amountOfItems * i.getItemPrice();
			System.out.printf("%s\t%s\t%s\t$%.2f\t$%.2f\n", amountOfItems, i.getProductType(), i.getName(),
					i.getItemPrice(), totalItemPrice);
			totalReceiptPrice += totalItemPrice;
			i.setCurrentStock(50);
		}
		System.out.printf("Your total is: $%.2f\n", totalReceiptPrice);
		totalReceiptPrice = 0;
	}
}