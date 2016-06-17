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

	public String getLogin() {
		return login;
	}
}
