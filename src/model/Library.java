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

	public void add(Item toAdd) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				items[i] = toAdd;
				break;
			}
		}
	}

}
