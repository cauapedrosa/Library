package model;

public abstract class Item {
	private String name;
	private int id;
	private int quantityOwned;
	private int quantityAvailable;
	private User[] clients;
	private static int idGenerator = 0;

	public Item(String name, int quantityOwned) {
		this.name = name;
		this.quantityOwned = quantityOwned;
		quantityAvailable = quantityOwned;
		id = idGenerator;
		idGenerator++;
		clients = new User[quantityOwned];
	}

	public boolean rent(User user) {
		if (quantityAvailable == 0) {
			return false;
		}

		for (int i = 0; i < clients.length; i++) {
			if (clients[i] == null) {
				clients[i] = user;
				quantityAvailable--;
				return true;
			}
		}
		return false;
	}

	public boolean deliver(User user) {
		if (quantityAvailable == quantityOwned) {
			return false;
		}
		for (int i = 0; i < clients.length; i++) {
			if (clients[i] == user) {
				clients[i] = null;
				quantityAvailable++;
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	@Override
	public String toString() {
		return String.format(
				"Name: %s\nID: %d\nQuantity Owned: %d\nQuantity Available: %d\n",
				name, id, quantityOwned, quantityAvailable);
	}

}