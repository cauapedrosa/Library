package controller;

import model.Item;
import model.OperatorUser;
import model.User;
import view.GUI;

public class Main {

	private static UserManager userManager = new UserManager(100);

	private static Library library = new Library(100);

	public static void main(String[] args) {
		boolean loopFlag = true;

		while (loopFlag) {
			int flag = GUI.inputInt(
					"Digite o número correspondente à operação desejada:\n"
							+ "1 - Cadastrar Usuário\n" + "2 - Alugar Item\n"
							+ "3 - Devolver Item\n"
							+ "4 - Verificar Disponibilidade de Item\n"
							+ "5 - Cadastrar Item\n"
							+ "6 - Checar itens alugados por um usuário\n"
							+ "7 - Procurar item");

			switch (flag) {
			case 1:
				registerUser();

				break;

			case 2:
				rentItem();
				break;

			case 3:
				returnItem();
				break;

			case 4:
				checkItemStatus();
				break;

			case 5:
				registerItem();
				break;

			case 6:
				userStatus();
				break;

			case 7:
				searchItem();
				break;

			default:
				loopFlag = false;
				break;
			}
		}
	}

	public static void registerUser() {
		int flag = GUI.inputInt("Qual tipo de Usuário deseja cadastrar?\n"
				+ "1 - Usuário Comum\n" + "2 - Usuário Operador");
		if (flag == 1) {
			userManager.createCommon();
		} else if (flag == 2) {
			if (GUI.inputInt("Digite a Senha Mestre\n") == 123) {
				userManager.createOperator();
			} else {
				GUI.showMessage("Senha Mestre Inválida\n");
			}

		}
	}

	public static void rentItem() {
		User user = userManager.login();

		if (user == null) {
			return;
		}

		Item item = library.getItemByNameOrID();

		if (item == null) {
			GUI.showMessage("Erro: Livro não encontrado\n");
			return;
		}

		if (item.rent(user)) {
			GUI.showMessage("Item Alugado com sucesso!\n");
		} else {
			GUI.showMessage("Erro: Nenhuma unidade em estoque\n");
		}

	}

	public static void returnItem() {
		User user = userManager.login();

		if (user == null) {
			return;
		}

		Item[] items = library.rentedBy(user);

		if (items.length == 0) {
			GUI.showMessage("Usuário não tem nenhum item alugado\n");
			return;
		}

		String msg = "";
		for (int i = 0; i < items.length; i++) {
			msg += String.format("%d - %s\n", i, items[i].getTitle());
		}

		int choice = GUI.inputInt("Escolha um item:\n" + msg);

		if (choice < 0 || choice >= items.length) {
			GUI.showMessage("Erro: Escolha Inválida\n");
			return;
		}

		if (items[choice].deliver(user)) {
			GUI.showMessage("Item devolvido com sucesso!\n");
		} else {
			GUI.showMessage("Erro: TODO \n");
		}
	}

	public static void checkItemStatus() {
		Item item = library.getItemByNameOrID();

		if (item == null) {
			GUI.showMessage("Erro: Item não encontrado\n");
			return;
		}

		GUI.showMessage(item.toString());
	}

	public static void registerItem() {
		User user = userManager.login();

		if (user == null) {
			GUI.showMessage("Usuário Inválido\n");
			return;
		}

		if (!(user instanceof OperatorUser)) {
			GUI.showMessage("Você não tem permissão para fazer isso!\n");
			return;
		}

		int type = GUI.inputInt("1 - Livro\n2 - DVD\n3 - Revista");

		switch (type) {
		case 1:
			library.registerBook();
			break;
		case 2:
			library.registerDVD();
			break;
		case 3:
			library.registerMagazine();
			break;
		default:
			GUI.showMessage(
					"Erro: Número Inválido (Digite um número de 1 a 3)\n");
			return;
		}
	}

	public static void userStatus() {
		User user = userManager.login();

		if (user == null) {
			return;
		}

		Item[] items = library.rentedBy(user);
		String msg = user.toString();

		if (items.length > 0) {
			msg += "\nRented items:\n";
		}

		for (int i = 0; i < items.length; i++) {
			msg += String.format("%s\n", items[i].getTitle());
		}

		GUI.showMessage(msg);
	}

	public static void searchItem() {
		String name = GUI
				.inputStr("Digite parte do nome do item que você procura");

		Item[] items = library.search(name);

		if (items.length == 0) {
			GUI.showMessage("Nenhum item encontrado\n");
			return;
		}

		String msg = "";
		for (int i = 0; i < items.length; i++) {
			msg += String.format("Item %d:\n%s\n", i + 1, items[i].toString());
		}

		GUI.showMessage(msg);
	}

}
