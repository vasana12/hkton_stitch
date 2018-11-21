package database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SignUpActionListener implements ActionListener {
	JTable table;
	JTextField id;
	
	SignUpActionListener() {}
	
	SignUpActionListener(JTable table, JTextField id) {
		this.table = table;
		this.id = id;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("�쉶�썝�벑濡�"))
			signUpUser();
	}
	
	public DefaultTableModel signUpUser() {

		String id;
		
		UserInfo info = new UserInfo(); 
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		id = this.id.getText().trim();
		
		if (info.searchUser(id)) {
			System.out.println("媛숈� �씠由꾩쓽 硫뗫궓�씠 �씠誘� �엳�꽕�슂~ ");
			return model;
		}else {
			Object[] idArray = new Object[1];
			idArray[0] = id;
			model.addRow(idArray);
			System.out.println("�꽭怨꾩턀怨좊찇�궓 " + id + "�뼱�꽌�삤�꽭�슂~!!");
			return model;
		}
	}
}
