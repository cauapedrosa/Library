package model;

public class Magazine extends Item {

	private String publisher;

	public Magazine(String name, int quantityOwned, String publisher) {
		super(name, quantityOwned);
		this.publisher = publisher;
	}

	public String getPublisher() {
		return publisher;
	}

	public String toString(){
		return "Revista:\n" + super.toString() + String
				.format("Editora: %s\n", publisher);
	}
}