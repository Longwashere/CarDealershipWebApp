package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class OptionSet implements Serializable {
	private String name;
	private int size;
	private int n;
	//private Option[] options;
	private ArrayList<Option> options = new ArrayList();
	private Option choice=null;

	

	/*
	 * constructor using size to form arrays
	 */
	protected OptionSet(int size) {
		n = size;
		options = new ArrayList(size);
		for (int i = 0; i < size; i++) {
			options.add(i, new Option("Not yet released"));
		}
	}
	
	protected void addOption(String Optiona, double price)
	{
		boolean found=false;
		for(int i = 0; i < size; i++)
		{
			if(options.get(i).getName().equals(Optiona))
			{
				found = true;
				System.out.println("This option already exist");
				return;
			}
			options.add(new Option(Optiona,price));
			return;
		}
		
	}
	/*
	 * @returns name
	 */
	protected String getName() {
		return name;
	}

	/*
	 * @sets name
	 */
	protected void setName(String name) {
		this.name = name;
	}
	/*
	 * retruns choice
	 */
	protected Option getChoice() {
		return choice;
	}

	protected void setChoice(Option choice) {
		this.choice = choice;
	}

	/*
	 * @returns size
	 */
	protected int getSize() {
		size=n;
		return size;
	}

	/*
	 * @set Size
	 */
	protected void setSize(int size) {
		this.size = size;
	}

	/*
	 * @returns options
	 */
	protected ArrayList getOptions() {
		return options;
	}

	/*
	 * @returns options
	 */
	protected Option getOption(int n) {
		return options.get(n);
	}

	/*
	 * loads option from another array
	 */
	protected void loadOptions(ArrayList options) {
		this.options = options;
	}

	/*
	 * setOption(name,price,n) pre: name price indexpost:set option of
	 * correspondant indext with price and name
	 */
	protected void setOption(String name, double price, int n) {
		options.get(n).setOption(price, name);
	} 				

	/*
	 * Uses an index to set the price of an option
	 */
	protected void setOption(double price, int n) {
		options.get(n).setOption(price);
	}

	/*
	 * uses an index to set the name of an option
	 */
	protected void setOption(String name, int n) {
		options.get(n).setOption(name);
	}

	/*
	 * prints out elements and subelements of optionset
	 */
	protected StringBuffer print() {
		StringBuffer buffer = new StringBuffer(name + " int size " + n
				+ " Options[] options\n");
		for (int i = 0; i < n; i++) {
			buffer.append(options.get(i).print());
		}
		return buffer;
	}
}
