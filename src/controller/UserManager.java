package controller;

import model.CommonUser;
import model.OperatorUser;
import model.User;
import view.GUI;

public class UserManager {
	private User[] users;

	public UserManager(int size) {
		users = new User[size];
	}

	public User get(String login) {
		for (User user : users) {
			if (user != null && user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;
	}

	public void createCommon() {
		String name = GUI.inputStr("Name: ");
		String login = GUI.inputStr("Login: ");
		String password = GUI.inputStr("Password: ");

		for (int i = 0; i < users.length; i++) {
			if (users[i] == null) {
				users[i] = new CommonUser(name, login, password);
			}
		}
	}

	public void createOperator() {
		String name = GUI.inputStr("Name: ");
		String login = GUI.inputStr("Login: ");
		String password = GUI.inputStr("Password: ");

		for (int i = 0; i < users.length; i++) {
			if (users[i] == null) {
				users[i] = new OperatorUser(name, login, password);
			}
		}
	}

}
