package model;

public class CommonUser extends User {

	public CommonUser(String name, String login, String password) {
		super(name, login, password);
	}
	
	@Override
	public String toString() {
		return super.toString() + "Tipo de usuário: Comum\n";
	}
}
