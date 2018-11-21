package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JPanel;

import Control.Control;

public class CPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	protected CFrame frame;
	
	protected Control control;
	
	
	public void init(CFrame frame, Control control) {
		this.frame = frame;
		this.control = control;
	}

	
	public void exit() {
		System.exit(1);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		invoke(this, e.getActionCommand(), null);
	}

	
	public static Object invoke(Object obj, String methodName, Object[] parameter) {
		Method[] methods = obj.getClass().getMethods();
		
		for(Method method: methods) {
			if(method.getName().equals(methodName)) {
				try {
					if (method.getReturnType().getName().equals("void")) {
						method.invoke(obj, parameter);        
					} else {
						return method.invoke(obj, parameter);
					}
				} catch(IllegalAccessException | InvocationTargetException e) {}
			}
		}
		return null;
	}
	
}
