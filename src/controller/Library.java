package controller;

import model.Book;
import model.DVD;
import model.Item;
import model.Magazine;
import model.User;
import view.GUI;

public class Library {
	private Item[] items;

	public Library(int size) {
		items = new Item[size];
	}

	public Item[] search(String name) {
		int count = 0;

		for (Item item : items) {
			if (item != null && item.getTitle().toLowerCase()
					.contains(name.toLowerCase())) {
				count++;
			}
		}

		Item[] found = new Item[count];

		count = 0;

		for (Item item : items) {
			if (item != null && item.getTitle().toLowerCase()
					.contains(name.toLowerCase())) {
				found[count] = item;
				count++;
			}
		}

		return found;
	}

	public Item get(String name) {
		for (Item item : items) {
			if (item != null && item.getTitle().equals(name)) {
				return item;
			}
		}
		return null;
	}

	public Item get(int id) {
		for (Item item : items) {
			if (item != null && item.getID() == id) {
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

	public Item[] rentedBy(User user) {
		int count = 0;

		for (Item item : items) {
			if (item != null && item.isRentedBy(user)) {
				count++;
			}
		}

		Item[] rented = new Item[count];

		count = 0;

		for (Item item : items) {
			if (item != null && item.isRentedBy(user)) {
				rented[count] = item;

				count++;
			}
		}

		return rented;
	}

	public void registerDVD() {
		String name = GUI.inputStr("Titulo: ");
		int quantityOwned = GUI.inputInt("Quantidade Total: ");
		int length = GUI.inputInt("Duração (min): ");

		add(new DVD(name, quantityOwned, length));

	}

	public void registerMagazine() {
		String name = GUI.inputStr("Titulo: ");
		int quantityOwned = GUI.inputInt("Quantidade Total: ");
		String publisher = GUI.inputStr("Editora: ");

		add(new Magazine(name, quantityOwned, publisher));

	}

	public void registerBook() {
		String name = GUI.inputStr("Nome: ");
		int quantityOwned = GUI.inputInt("Quantidade Total: ");
		String author = GUI.inputStr("Autor: ");
		int releaseYear = GUI.inputInt("Data de lançamento: ");

		add(new Book(name, quantityOwned, author, releaseYear));
	}

	public Item getItemByNameOrID() {
		Item item = null;

		int flag = GUI.inputInt(
				"Deseja procurar o item por nome ou ID?\n1 - Nome\n2 - ID");

		if (flag == 1) {
			String name = GUI.inputStr("Digite o nome do item");
			item = get(name);
		} else if (flag == 2) {
			int id = GUI.inputInt("Digite a ID do item");
			item = get(id);
		} else {
			GUI.showMessage("Operação Cancelada\n");
		}

		return item;
	}

}
