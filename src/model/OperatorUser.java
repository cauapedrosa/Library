package model;

public class OperatorUser extends User {

	public OperatorUser(String name, String login, String password) {
		super(name, login, password);
	}

	@Override
	public String toString() {
		return super.toString() + "Tipo de usuário: Operador\n";
	}
}
