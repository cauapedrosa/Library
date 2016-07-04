package model;

public class Book extends Item {

	private final int releaseYear;
	private final String author;

	public Book(String name, int quantityOwned, String author,
			int releaseYear) {
		super(name, quantityOwned);
		this.releaseYear = releaseYear;
		this.author = author;
	}

	public int getRelease() {
		return releaseYear;
	}

	public String getAuthor() {
		return author;
	}
}
