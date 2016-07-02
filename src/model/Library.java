package model;

public class Library {
	private Item[] items;

	public Library() {
	}

	public Item get(String name) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].getName().equals(name)) {
				return items[i];
			}
		}
		return null;
	}

	public void add(String name) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				items[i] = name;
				break;
			}
		}
	}

}
