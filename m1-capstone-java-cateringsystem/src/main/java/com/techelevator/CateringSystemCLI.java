package com.techelevator;

import com.techelevator.view.ItemObject;
import com.techelevator.view.Menu;
import com.techelevator.view.cashRegister;
import com.techelevator.view.menuDisplayer;

import java.util.List;

public class CateringSystemCLI {

	private Menu menu;

	public CateringSystemCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		menu.displayMainMenuToRead();
		menuDisplayer mainMenuDisplay = new menuDisplayer();
		cashRegister register = new cashRegister();
		List<ItemObject> itemsBeingDisplayed = mainMenuDisplay.displayCateringItems();
		while (true) {
			/*
			 * Display the Starting Menu and get the users choice IF the User Choice is
			 * Display Vending Machine Items, THEN display vending machine items ELSE IF the
			 * User's Choice is Purchase, THEN go to the purchase menu
			 */

			double selection = menu.getDoubleInput();
			if (selection == 1) {
				menu.printItems2read(itemsBeingDisplayed);
				menu.displayMainMenuToRead();
			} else if (selection == 3) {
				break;
			} else if (selection >3 || selection < 1) {
				menu.displayMainMenuToRead();
			}
			while (selection == 2) {
				if (!(menu.executed)) {
					menu.displayRegister(register);
				}
				double selectionMoney = menu.getDoubleInput();
				if (selectionMoney == 1) {
					register.addMoney(register);
					menu.displayRegister(register);
				} else if (selectionMoney == 2) {
					menu.printItems2read(itemsBeingDisplayed);
					register.selectReduceProduct(itemsBeingDisplayed);
					menu.displayRegister(register);
				} else if (selectionMoney == 3) {
//					register.printReceipt(register.getReceiptList());
					menu.displayReceipt(register.getReceiptList());
					register.returnChange(register.getBalance());
					menu.displayMainMenuToRead();
					register.reset(register.getReceiptList());
					menu.executed = false;
					break;
				}
				else {
					System.out.println("Enter a valid option");
					menu.displayRegister(register);
				}
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();

	}

}