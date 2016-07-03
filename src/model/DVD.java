package model;

public class DVD extends Item {

	private int length; // in minutes

	public DVD(String name, int quantityOwned, int length) {
		super(name, quantityOwned);
		this.length = length;
	}

	public int getLength() {
		return length;
	}

}