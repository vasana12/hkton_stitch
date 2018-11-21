package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.Control;
import database.WishInfo;
import main.Constants.State;
import main.Constants.Type;
import model.Clothes;
import view.CFrame;
import view.CPanel;

public class Wish extends CPanel {
	
	private static final long serialVersionUID = 1L;
		
	private JPanel imgPanel;
	private JButton backButton;
	
	private WishInfo wishInfo;

	
	public Wish () {

		this.imgPanel = new JPanel();
		this.imgPanel.setPreferredSize(new Dimension(500, 500));
		
		this.wishInfo = new WishInfo();

		
		this.backButton = new JButton("돌아가기");
		this.backButton.addActionListener(this);
		this.backButton.setActionCommand("backList");
		
	}
	
	@Override
	public void init(CFrame frame, Control control) {
		super.init(frame, control);
		
		Clothes clothes = wishInfo.mostRecommendedColor(control.getUserId());
		
		if(clothes != null) {
			if (Type.valueOf(clothes.getType()).ordinal() > 5) {
				imgPanel.add(new JLabel(this.createTop(new Color(255, 255, 255))));
				imgPanel.add(new JLabel(this.createBottom(new Color(clothes.getRed(), clothes.getGreen(), clothes.getBlue()))));
			} else {
				imgPanel.add(new JLabel(this.createTop(new Color(clothes.getRed(), clothes.getGreen(), clothes.getBlue()))));
				imgPanel.add(new JLabel(this.createBottom(new Color(255, 255, 255))));
			}
		}else {
			imgPanel.add(new JLabel("아직 필요한 옷이 없어요......"));

		}
	
		this.add(backButton);
		
		this.frame.getContentPane().add(imgPanel, BorderLayout.CENTER);
		this.frame.getContentPane().add(this, BorderLayout.SOUTH);
	}

	
	public void backList() {
		this.frame.setPanel(State.List);
	}
	

	private ImageIcon createTop(Color c) {
		
		BufferedImage img = new BufferedImage(500, 250, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		g.setColor(c);
		int[] x = {75, 180, 220, 240, 260, 280, 320, 425, 400, 325, 325, 175, 175, 100};
		int[] y = {125, 50, 50, 70, 70, 50, 50, 125, 170, 125, 250, 250, 125, 170};
		
		g.fillPolygon(x, y, 14);
		
		return new ImageIcon(img);
	}
	
	
	private ImageIcon createBottom(Color c) {
		
		BufferedImage img = new BufferedImage(500, 250, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		g.setColor(c);
		int[] x = {175, 325, 350, 260, 250, 240, 150};
		int[] y = {0, 0, 150, 160, 40, 160, 150};
		
		g.fillPolygon(x, y, 7);
		
		return new ImageIcon(img);
	}
}
