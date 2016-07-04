package model;

public abstract class User {
	private String name, login, password;

	public User(String name, String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
	}

	public boolean signIn(String password) {
		return this.password.equals(password);
	}

	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public String toString() {
		return String.format("Nome: %s\nLogin: %s\n", name, login);
	}
}
