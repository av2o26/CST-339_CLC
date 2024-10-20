package com.gcu.model;

public class ProductModel 
{
	// Properties
	private String name;
	private double price;
	private String description;
	private int quantity;
	private int id;

	/**
	 * Constructor
	 * @param name
	 * @param price
	 * @param description
	 * @param quantity
	 * @param id
	 */
	public ProductModel(String name, double price, String description, int quantity, int id) 
	{
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}
	
	/**
	 * Default Constructor
	 */
	public ProductModel() {
		super();
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}