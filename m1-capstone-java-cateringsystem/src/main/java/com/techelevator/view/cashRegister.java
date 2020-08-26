package com.techelevator.view;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.techelevator.*;

public class cashRegister extends Menu {
	private double currentBalance = 0.0;
	double totalItemPrice = 0;
	double totalPrice = 0;

	Date date = new Date();
	List<ItemObject> receiptList = new ArrayList<ItemObject>();
	List<Audit> auditList = new ArrayList<Audit>();

	public double addMoney(cashRegister register) {
		System.out.println("Enter amount to add: ");
		selection = getDoubleInput();
		if (selection <= 0 || selection >= 5001) {
			System.out.println("Please enter a valid integer between 1 and 5000");
			System.out.println();

		} else if (selection + currentBalance > 5000) {
			System.out.println("We will not hold anything above $5000.00");
			System.out.println();

		} else {
			currentBalance += selection;
			Audit addedAudit = new Audit(date + " ADD MONEY ", selection, currentBalance);
			auditList.add(addedAudit);
			printLog(auditList);
		}
		return currentBalance;
	}

	public List<ItemObject> selectReduceProduct(List<ItemObject> listArray) {

		System.out.println("Enter the product code of the desired item ");
		String productCodeInput = getStringInput(listArray);
		productCodeInput = productCodeInput.toUpperCase();
		printOneItem2read(listArray, productCodeInput);

		// TODO:If the product code does not exist, the customer is informed and
		// returned to the Purchase menu.
		System.out.printf("Your current balance: $%.2f\n", getBalance());
		System.out.println("Enter how many you would like to purchase");
		int purchasingAmount = (int) getDoubleInput();

		for (ItemObject i : listArray) {
			if (i.getProductCode().equals(productCodeInput)) {
				if (i.getCurrentStock() == 0) {
					System.out.println("SOLD OUT");
				} else if (purchasingAmount * i.getItemPrice() > currentBalance) {
					System.out.println("You do not have enough right now");
				} else if (i.getCurrentStock() - purchasingAmount > i.getCurrentStock()
						|| i.getCurrentStock() < purchasingAmount) {
					System.out.println("Insufficient stock --- " + i.getCurrentStock());
				} else {
					currentBalance -= (purchasingAmount * i.getItemPrice());
					System.out.printf("Charging: $%.2f\n", purchasingAmount * i.getItemPrice());
					int currentStock = i.getCurrentStock() - purchasingAmount;
					i.setCurrentStock(currentStock);
					Audit addedAuditItem = new Audit(
							date.toString() + " " + purchasingAmount + " " + i.getName() + " " + i.getProductCode(),
							(purchasingAmount * i.getItemPrice()), currentBalance);
					auditList.add(addedAuditItem);
					printLog(auditList);
					receiptList.add(i);
				}
			}
		}
		return receiptList;
	}

	public double getBalance() {
		return currentBalance;
	}

	public void reset(List<ItemObject> receiptList) {
		currentBalance = 0;
			 receiptList.removeAll(receiptList);
	}

	public List<ItemObject> getReceiptList() {
		return receiptList;
	}

//	public void printReceipt(List<ItemObject> listArray) {
//
//		try (PrintWriter receipt = new PrintWriter("receipt.txt")) {
//			for (ItemObject i : listArray) {
//
//				double amountOfItems = ((i.getCurrentStock()) * -1) + 50;
//				totalItemPrice = amountOfItems * i.getItemPrice();
//				receipt.printf("%s\t%s\t%s\t$%.2f\t$%.2f\n", amountOfItems, i.getProductType(), i.getName(),
//						i.getItemPrice(), totalItemPrice);
//				totalPrice += totalItemPrice;
//			}
//			receipt.printf("Total: $%.2f\n", totalPrice);
//		} catch (Exception e) {
//			e.getMessage();
//		}
//	}

	public void printLog(List<Audit> listArray) {
		try (PrintWriter logList = new PrintWriter("log.txt")) {
			for (Audit a : listArray) {
				logList.printf("%s $%.2f $%.2f\n", a.getAuditPhrase(), a.getUserInputValue(), a.getAuditTotal());
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void returnChange(double currentBalance) {
		Audit addedAudit = new Audit(date + " GIVE CHANGE: ", currentBalance, (currentBalance * 0.0));
		auditList.add(addedAudit);
		printLog(auditList);
		System.out.println();
		System.out.printf("Your change is: $%.2f\n",currentBalance);
		Integer num100 = (int) (currentBalance / 100);
		currentBalance -= Math.round(num100 * 100);
		Integer num20 = (int) (currentBalance / 20);
		currentBalance -= Math.round(num20 * 20);
		Integer num10 = (int) currentBalance / 10;
		currentBalance -= Math.round(num10 * 10);
		Integer num5 = (int) currentBalance / 5;
		currentBalance -= Math.round(num5 * 5);
		Integer nums1 = (int) currentBalance / 1;
		currentBalance -= Math.round(nums1 * 1);
		Integer numQ = (int) (currentBalance / .25);
		currentBalance -= (numQ * .25);
		Integer num10cents = (int) (currentBalance / .10);
		currentBalance -= (num10cents * .10);
		Integer num5cents = (int) (currentBalance / .5);
		currentBalance -= (num5cents * .5);
		Integer num1cents = (int) (currentBalance / .01);
		currentBalance -= (num1cents * .01);
		if(num100>= 1){
			System.out.print(num100 + " : Hundreds .. ");	
		}
		if (num20 >= 1) {
			System.out.print(num20 + " : Twenties .. ");
		}
		if (num10 >= 1) {
			System.out.print(num10 + " : Tens .. ");
		}
		if (num5 >= 1) {
			System.out.print(num5 + " : Fives .. ");
		}
		if (nums1 >= 1) {
			System.out.print(nums1 + " : Ones .. ");
		}
		if (numQ >= 1) {
			System.out.print(numQ + " : Quarters .. ");
		}
		if (num10cents >= 1) {
			System.out.print(num10cents + " : Dimes .. ");
		}
		if (num5cents >= 1) {
			System.out.print(num5cents + " : Nickels .. ");
		}
		if (num1cents >= 1) {
			System.out.print(num1cents + " : Pennies");
		}
		System.out.println();
		System.out.println();
	}
}
