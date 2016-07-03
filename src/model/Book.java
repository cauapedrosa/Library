package model;

public class Book extends Item {
	private String author;
	private int releaseYear;//four digit year (eg 1998)

	public Book(String name, int quantityOwned) {
		super(name, quantityOwned);
		this.releaseYear = releaseYear;
	}

}
