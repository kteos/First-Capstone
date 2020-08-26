package com.techelevator.view;

public class ItemObject {

	int currentStock = 0;
	String name = "";
	double itemPrice = 0.0;
	String productCode = "";
	String productType = "";

	public ItemObject(String productCode, String name, double itemPrice, String productType, int currentStock) {
		this.productCode = productCode;
		this.name = name;
		this.itemPrice = itemPrice;
		this.productType = productType;
		this.currentStock = currentStock;
	}

	public String getName() {
		return name;
	}

	public String getProductCode() {
		return productCode;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public String getProductType() {
		return productType;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

}
