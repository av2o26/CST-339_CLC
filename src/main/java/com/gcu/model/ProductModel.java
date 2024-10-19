package com.gcu.model;

public class ProductModel {
	private String Name;
	private double Price;
	private String Description;
	private int Quantity;
	private int ID;

	public ProductModel(String name, double price, String description, int quantity, int id) {
		super();
		this.ID = id;
		this.Name = name;
		this.Price = price;
		this.Description = description;
		this.Quantity = quantity;
	}
	public ProductModel() {
		super();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

}