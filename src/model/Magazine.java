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

}