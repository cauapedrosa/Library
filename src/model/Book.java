package model;

public class Book extends Item {

<<<<<<< HEAD
	private final int releaseYear;
	private final String author;

	public Book(String name, int quantityOwned, String author,
			int releaseYear) {
		super(name, quantityOwned);
		this.releaseYear = releaseYear;
		this.author = author;
	}
=======
    private final int releaseYear;
    private final String author;

    public Book(String name, int quantityOwned, String author, int releaseYear) {
        super(name, quantityOwned);
        this.releaseYear = releaseYear;
        this.author = author;
    }
>>>>>>> 71617cbf317e6e11d02ed4b2d3f1b1d718a3d312

    public int getRelease() {
        return releaseYear;
    }

    public String getAuthor() {
        return author;
    }
}
