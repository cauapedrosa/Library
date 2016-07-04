package view;

import javax.swing.JOptionPane;

public class GUI {
	public static int inputInt(String msg) {
		String input = JOptionPane.showInputDialog(msg);
		return Integer.parseInt(input);
	}

	public static String inputStr(String msg) {
		return JOptionPane.showInputDialog(msg);
	}

	public static void showMessage(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
}
