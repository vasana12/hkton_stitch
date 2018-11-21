package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Control.Control;
import database.ClothesInfo;
import main.Constants.State;
import main.Constants.Type;
import model.Clothes;
import view.CFrame;
import view.CPanel;

public class ColorSet extends CPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JColorChooser chooser;

	private JLabel colorview;
	private JComboBox<String> typeBox;
	private JButton okButton;
	private JButton backButton;
	
	private ClothesInfo clothesInfo;
	
	
	public ColorSet () {
		
		this.chooser = new JColorChooser();
		this.chooser.setPreviewPanel(new JPanel());
		this.chooser.getSelectionModel().addChangeListener(new ColorListener());

		AbstractColorChooserPanel[] panels = chooser.getChooserPanels();

		for (AbstractColorChooserPanel accp : panels) {
		   if(!accp.getDisplayName().equals("RGB(G)")) {
			   chooser.removeChooserPanel(accp);
		   }
		}
		
		this.colorview = new JLabel("■　");
		this.colorview.setForeground(chooser.getColor());
		this.colorview.setFont(new Font("Serif", Font.BOLD, 30));
		
		ArrayList<String> type = new ArrayList<String>();
		Type[] temp = Type.values();
		for(int i = 0; i < temp.length; i++) {
			type.add(temp[i].name());
		}
		
		String[] array = type.toArray(new String[type.size()]);
		this.typeBox = new JComboBox<String>(array);

		this.okButton = new JButton("등록");
		this.okButton.addActionListener(this);
		this.okButton.setActionCommand("clothAdd");
		
		this.backButton = new JButton("돌아가기");
		this.backButton.addActionListener(this);
		this.backButton.setActionCommand("backList");
		
	}
	
	
	@Override
	public void init(CFrame frame, Control control) {
		super.init(frame, control);
		
		this.add(colorview);
		this.add(typeBox);
		this.add(okButton);
		this.add(backButton);
		
		this.frame.getContentPane().add(chooser, BorderLayout.CENTER);
		this.frame.getContentPane().add(this, BorderLayout.SOUTH);
	}
	

	public void clothAdd() {
		
		Clothes clothes = new Clothes();
		
		clothes.setId(this.control.getUserId());
		clothes.setType(typeBox.getSelectedItem().toString());;
		clothes.setRed(chooser.getColor().getRed());
		clothes.setGreen(chooser.getColor().getGreen());
		clothes.setBlue(chooser.getColor().getBlue());
		clothes.setAlpha(chooser.getColor().getAlpha());
		
		this.clothesInfo = new ClothesInfo();		
		this.clothesInfo.addClothes(clothes);
		
		this.frame.setPanel(State.List);
	}

	
	public void backList() {
		this.frame.setPanel(State.List);
	}
	
	
	private class ColorListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
            Color color = chooser.getColor();
            colorview.setForeground(color);
        }
	}
	
}
