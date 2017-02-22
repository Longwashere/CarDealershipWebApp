package model;

import java.io.Serializable;

public class Option implements Serializable {
	private String option;
	private double price;

	/*
	 * default constructor
	 */
	protected Option() {
	}

	/*
	 * contstructor that sets name of option
	 */
	protected Option(String option) {
		this.option = option;
	}
	protected Option(String option, double price)
	{
		this.price=price;
		this.option=option;
	}
	/*
	 * @returns name
	 */
	protected String getName() {
		return option;
	}

	/*
	 * @returns price
	 */
	protected double getPrice() {
		return price;
	}

	/*
	 * sets the option price and name
	 */
	protected void setOption(double price, String name) {
		this.option = name;
		this.price = price;
	}

	/*
	 * sets the option price
	 */
	protected void setOption(double price) {
		this.price = price;
	}

	/*
	 * sets the option name
	 */
	protected void setOption(String name) {
		option = name;
	}

	/*
	 * prints out all elements and subelements using buffers
	 */
	protected StringBuffer print() {
		StringBuffer buffer = new StringBuffer("String option:" + option
				+ " double price " + price + " \n");
		return buffer;
	}
}