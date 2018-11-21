package panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import Control.Control;
import database.UserInfo;
import main.Constants.State;
import view.CFrame;
import view.CPanel;

public class Login extends CPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField loginTextField;
	private JButton loginButton;
	private JButton joinButton;
	private JButton exitButton;
	
	private UserInfo user;

	
	public Login () {

		this.loginTextField = new JTextField(20);

		this.loginButton = new JButton("들어가기");
		this.loginButton.addActionListener(this);
		this.loginButton.setActionCommand("userLogin");
		
		this.joinButton = new JButton("회원 등록");
		this.joinButton.addActionListener(this);
		this.joinButton.setActionCommand("userJoin");
		
		this.exitButton = new JButton("종료");
		this.exitButton.addActionListener(this);
		this.exitButton.setActionCommand("exit");
		
	}
	
	@Override
	public void init(CFrame frame, Control control) {
		super.init(frame, control);
		
		this.add(loginButton);
		this.add(joinButton);
		this.add(exitButton);
		
		this.frame.getContentPane().add(loginTextField, BorderLayout.CENTER);
		this.frame.getContentPane().add(this, BorderLayout.SOUTH);
	}


	public void userLogin() {
		user = new UserInfo();
		
		if(user.searchUser(loginTextField.getText())) {
			this.control.setUserId(loginTextField.getText());
			this.frame.setPanel(State.List);
		}
	}

	
	public void userJoin() {
		user = new UserInfo();
		
		if(!user.searchUser(loginTextField.getText())) {
			user.signUpUser(loginTextField.getText());
		}
	}


}
