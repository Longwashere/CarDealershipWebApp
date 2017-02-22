package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Automobile implements Serializable {
	private double basePrice;
	private int size;
	private int capacity;
	private String name;
	private ArrayList<OptionSet> optionSets;
	private String make;
	private String model;

	// constructor
	public Automobile(int size, double price) {
		this.size = size;
		setOptionSets(new ArrayList(size));
		this.setBasePrice(price);
	}

	/**
	 * @return the optionSets
	 */
	// returns array of options
	public ArrayList<OptionSet> getOptionSets() {
		return optionSets;
	}

	/**
	 * @param optionSets
	 *            the optionSets to set
	 */
	public synchronized void setOptionSets(ArrayList<OptionSet> optionSets) {
		this.optionSets = optionSets;
	}
	public synchronized int getOptionSetSize1(int i)
	{
		return optionSets.get(i).getSize();
		
	}
	public synchronized String getModel() {
		return model;
	}

	public synchronized void setModel(String model) {
		this.model = model;
	}
	public synchronized String getOptionName(int i,int j, String setName)
	{
		return optionSets.get(i).getOption(j).getName();
	}
	public synchronized String getOptionSetName(int i)
	{
		return optionSets.get(i).getName();
	}
	public synchronized int getOptionSetSize(int i)
	{
		return optionSets.get(i).getSize();
	}
	/*
	 * Sets arrays in optionSets using name, size and index
	 */
	public void setOptionSet(String name, int size, int n) {
		optionSets.add(n, new OptionSet(size));
		optionSets.get(n).setName(name);
	}
	/*
	 * set name of an optionset using an index.
	 */
	public void setOptionSetName(String name, int index)
	{
		optionSets.get(index).setName(name);
	}
	/*
	 * adds a new option if it didnt exsist already
	 */
	public synchronized void setOptionWithOptionSet(String opSet,String Option, double price)
	{
		OptionSet a=findOptionSet(opSet);
		if(a==null)
		{
			System.out.println("couldn't foind set");
			return;
		}
		a.addOption(Option,price);
		System.out.println("added success");
		return;
		
	}
	/*
	 * 
 * gets the option's choice
 */
	public synchronized String getOptionChoice(String setName)
	{
			return findOptionSet(setName).getChoice().getName();
	}
	public synchronized String getMake() {
		return make;
	}

	public synchronized void setMake(String make) {
		this.make = make;
	}

	/*
	 * gets the options price
	 */
	public synchronized double getOptionPrice(String setName)
	{
		return findOptionSet(setName).getChoice().getPrice();
	}
	/*
	 * Sets the Option choice of a car
	 */
	public synchronized void setOptionChoice(String setName, String choice)
	{
		Option temp = findOption(choice,setName);
		findOptionSet(setName).setChoice(temp);
	}
	/*
	 * sums up choices and returns its price
	 */
	public synchronized double getTotalPrice()
	{
		double price=0;
		for(int i=0;i<optionSets.size();i++)
		{
			if(optionSets.get(i).getChoice()!=null)
			{
			price=price + optionSets.get(i).getChoice().getPrice();
			}
		}
		return price;
	}
	/*
	 * updating/setting name of a optionSet using an index and name
	 */
	public synchronized void setOptionSet(String name, int n) {
		optionSets.get(n).setName(name);
	}

	/**
	 * @return the basePrice
	 */
	public synchronized double getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice
	 *            the basePrice to set
	 */
	public synchronized void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	/*
	 * setting/updating an option price/name using an index of optionSet and
	 * index of option
	 */
	public synchronized void setOption(String name, double price, int indexOptionSet,
			int indexOption) {
		optionSets.get(indexOptionSet).getOption(indexOption)
		.setOption(price, name);
	}

	/*
	 * setting/updating an option name using an index of optionSet and index of
	 * option
	 */
	public synchronized void setOption(String name, int indexOptionSet, int indexOption) {
		optionSets.get(indexOptionSet).getOption(indexOption).setOption(name);
	}

	/*
	 * set an option's price using an index of option set and index of option
	 */
	public synchronized void setOption(double price, int indexOptionSet, int indexOption) {
		optionSets.get(indexOptionSet).getOption(indexOption).setOption(price);
	}
	public synchronized void updateOption(String optionSetName, String optionName, double price)
	{
		findOption(optionName, optionSetName).setOption(price);
	}

	/*
	 * prints every element and subelement of automobile using buffers
	 */
	public synchronized void print() {
		StringBuffer buffer = new StringBuffer("Car Name"+ name +"double basePrice " + basePrice
				+ " int size " + size + " OptionSets[] \n");
		for (int i = 0; i < size; i++) {
			buffer.append(optionSets.get(i).print());
		}
		System.out.println(buffer.toString());
	}
	/*
	 * set an options name and price
	 */
	public synchronized void setOption(String find, double price, String newName)
	{
		findOption(find).setOption(price, newName);
	}
	/*
	 * sets and option price
	 */
	public synchronized void setOption(String find, double price)
	{
		findOption(find).setOption(price);
	}
	/*
	 * find options and sets an option name
	 */
	public synchronized void setOption(String find, String newName)
	{
		findOption(find).setOption(newName);
	}

	/*
	 * finds the option by it's name checks everything for that option
	 */
	public synchronized Option findOption(String find) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < optionSets.get(i).getSize(); j++) {
				if (find.equals(optionSets.get(i).getOption(j).getName())) {
					return optionSets.get(i).getOption(j);
				}
			}
		}
		System.out.println("couldnt find");
		return null;
	}
	/*
	 * finds option with name and the option's set name
	 */
	public synchronized Option findOption(String find, String optionSetName)
	{
		for(int i = 0; i < size; i++)
		{
			if(optionSets.get(i).getName().equals(optionSetName))
			{
				for(int j = 0; j<optionSets.get(i).getSize();j++)
				{
					if (find.equals(optionSets.get(i).getOption(j).getName())) {
						return optionSets.get(i).getOption(j);
				}
			}
		}
	}
		System.out.println("couldn't find");
		return null;
	}
	/*
	 * finds an optionset by its name
	 */
	public synchronized OptionSet findOptionSet(String find) {
		for (int i = 0; i < size; i++) {
			if (optionSets.get(i).getName().equals(find)) {
				return optionSets.get(i);
			}
		}
		return null;
	}
	/*
	 * replaces an option's set names
	 */
	public synchronized void updateOptionSet(String find, String newName)
	{
		findOptionSet(find).setName(newName);
	}
	/*
	 * returns name
	 */
	public synchronized String getName() {
		return name;
	}
	/*
	 * sets name
	 */
	public synchronized void setName(String name) {
		this.name = name;
		setMake(name.substring(0,name.indexOf(" ")));
		setModel(name.substring(name.indexOf(" ")));
	}
                public synchronized double webGetOptionPrice(String find)
        {
            return findOption(find).getPrice();
        }
}
