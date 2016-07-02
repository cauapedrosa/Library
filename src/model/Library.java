package model;

public class Library {
	private Item[] items;

	public Library() {
	}

	public Item get(String name) {
		for (Item item : items) {
			if (item != null && item.getTitle().equals(name)) {
              return item;
			}
		}
		return null;
	}

	public void add(Item name) {
		for (Item item : items) {
			if (item == null) {
				item = name;
				break;
			}
		}
	}

}
