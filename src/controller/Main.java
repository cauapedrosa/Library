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

			int flag = GUI
					.inputInt("============================================\n"
							+ "Digite o n�mero correspondente � opera��o desejada:\n"
							+ "============================================\n"
							+ "0 - Sair\n" + "1 - Procurar item\n"
							+ "2 - Verificar Disponibilidade de Item\n"
							+ "3 - Alugar Item\n" + "4 - Devolver Item\n"
							+ "5 - Checar seus itens alugados\n"
							+ "6 - Cadastrar Usu�rio\n"
							+ "============================================\n"
							+ "Op��es de Administrador:\n"
							+ "7 - Cadastrar Item\n");

			switch (flag) {
			case 0:
				loopFlag = false;
				break;

			case 1:
				searchItem();
				break;

			case 2:
				checkItemStatus();
				break;

			case 3:
				rentItem();
				break;

			case 4:
				returnItem();
				break;

			case 5:
				userStatus();
				break;

			case 6:
				registerUser();
				break;

			case 7:
				registerItem();
				break;

			default:
				loopFlag = false;
				break;
			}
		}
	}

	public static void registerUser() {
		int flag = GUI.inputInt("Qual tipo de Usu�rio deseja cadastrar?\n"
				+ "1 - Usu�rio Comum\n" + "2 - Usu�rio Operador");
		if (flag == 1) {
			userManager.createCommon();
		} else if (flag == 2) {

			String masterPassAttempt = GUI.inputStr("Digite a Senha Mestre\n");
			if ("123".equals(masterPassAttempt)) {
				userManager.createOperator();
			} else {
				GUI.showMessage("Senha Mestre Inv�lida\n");
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
			GUI.showMessage("Erro: Livro n�o encontrado\n");
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
			GUI.showMessage("Usu�rio n�o tem nenhum item alugado\n");
			return;
		}

		String msg = "";
		for (int i = 0; i < items.length; i++) {
			msg += String.format("%d - %s\n", i, items[i].getTitle());
		}

		int choice = GUI.inputInt("Escolha um item:\n" + msg);

		if (choice < 0 || choice >= items.length) {
			GUI.showMessage("Erro: Escolha Inv�lida\n");
			return;
		}

		if (items[choice].deliver(user)) {
			GUI.showMessage("Item devolvido com sucesso!\n");
		} else {
			GUI.showMessage("Erro: Escolha Inv�lida\n");
		}
	}

	public static void checkItemStatus() {
		Item item = library.getItemByNameOrID();

		if (item == null) {
			GUI.showMessage("Erro: Item n�o encontrado\n");
			return;
		}

		GUI.showMessage(item.toString());
	}

	public static void registerItem() {
		User user = userManager.login();

		if (user == null) {
			GUI.showMessage("Usu�rio Inv�lido\n");
			return;
		}

		if (!(user instanceof OperatorUser)) {
			GUI.showMessage("Voc� n�o tem permiss�o para fazer isso!\n");
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
					"Erro: N�mero Inv�lido (Digite um n�mero de 1 a 3)\n");
			return;
		}
	}

	public static void userStatus() {
		User user = userManager.login();

		if (user == null) {
			GUI.showMessage("Erro: Usu�rio Invalido\n");
			return;
		}

		Item[] items = library.rentedBy(user);
		String msg = user.toString();

		if (items.length == 0) {
			msg += "\nItens Alugados:\n---Nenhum---\n";
		}

		if (items.length > 0) {
			msg += "\nItens Alugados:\n";
		}

		for (int i = 0; i < items.length; i++) {
			msg += String.format("%s\n", items[i].getTitle());
		}

		GUI.showMessage(msg);
	}

	public static void searchItem() {
		String name = GUI
				.inputStr("Digite parte do nome do item que voc� procura");

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
