package view;

import javax.swing.JFrame;

import Control.Control;
import main.Constants.State;

public class CFrame extends JFrame  {

	private static final long serialVersionUID = 1L;
	
	private CPanel cpanel;
	
	private Control control;
	
	public CFrame () {
		
		control = new Control();

		this.setUndecorated(true);
		this.setVisible(true);
		
	}


	public void init() {
		this.setPanel(State.Login);
	}
	
	
	public void setPanel(State state) {
		
		try {
			this.getContentPane().removeAll();

			Class<?> newPanel = Class.forName("panel." + state.name());
			this.cpanel = (CPanel) newPanel.newInstance();
			this.cpanel.init(this, control);
			
			this.pack();
			this.setLocationRelativeTo(null);
			
			//this.revalidate();
			//this.repaint();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
