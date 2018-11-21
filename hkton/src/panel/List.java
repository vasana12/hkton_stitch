package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Control.Control;
import database.ClothesInfo;
import main.Constants.State;
import model.Clothes;
import view.CFrame;
import view.CPanel;

public class List extends CPanel {
	
	private static final long serialVersionUID = 1L;

	private DefaultTableCellRenderer dtcr;
	
	private	JTable table;
	private JScrollPane scrollPane;

	private JButton addButton;
	private JButton searchButton;
	private JButton deleteButton;
	private JButton logoutButton;
	private JButton exitButton;
	
	private ClothesInfo clothesInfo;
	private ArrayList<Clothes> clothes;
	private ArrayList<Color> colorList;


	
	public List () {
		
		this.addButton = new JButton("옷 추가");
		this.addButton.addActionListener(this);
		this.addButton.setActionCommand("clothAdd");
		
		this.searchButton = new JButton("옷 추천");
		this.searchButton.addActionListener(this);
		this.searchButton.setActionCommand("clothSearch");
		
		this.deleteButton = new JButton("옷 버리기");
		this.deleteButton.addActionListener(this);
		this.deleteButton.setActionCommand("clothDelete");
		
		this.logoutButton = new JButton("로그아웃");
		this.logoutButton.addActionListener(this);
		this.logoutButton.setActionCommand("userLogout");
		
		this.exitButton = new JButton("종료");
		this.exitButton.addActionListener(this);
		this.exitButton.setActionCommand("exit");
		
	}
	
	
	@Override
	public void init(CFrame frame, Control control) {
		super.init(frame, control);
		
        this.clothesInfo = new ClothesInfo();
        this.clothes = clothesInfo.getClothes(this.control.getUserId());
        this.colorList = new ArrayList<Color>();
        
		String[] columnNames = {"순번", "종류", "색"};
		
		Object[] type = clothes.toArray();
		Object[][] data = {};
		
		if (type.length > 0) {
			data = new Object[type.length][3];
		}

		for (int i = 0; i < type.length; i++) {
			data[i][0] = i+1;
			data[i][1] = clothes.get(i).getType();
			colorList.add(new Color(clothes.get(i).getRed(), clothes.get(i).getGreen(), clothes.get(i).getBlue()));
			data[i][2] = createImage(colorList.get(i));
		}
		
		this.dtcr = new DefaultTableCellRenderer();
		this.dtcr.setHorizontalAlignment(JLabel.CENTER);
		
		this.table = new JTable(new Model(data, columnNames));
		this.table.addMouseListener(new MouseListener());
		this.table.getColumn("순번").setCellRenderer(dtcr);
		this.table.getColumn("종류").setCellRenderer(dtcr);
		 
		this.scrollPane = new JScrollPane(table);
		this.scrollPane.setPreferredSize(new Dimension(200, 200));
     
		
		this.add(addButton);
		this.add(searchButton);
		this.add(deleteButton);
		this.add(logoutButton);
		this.add(exitButton);
		
		this.frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.frame.getContentPane().add(this, BorderLayout.SOUTH);
	}
	
	public void clothAdd() {
		this.frame.setPanel(State.ColorSet);
	}

	
	public void clothSearch() {
		this.frame.setPanel(State.Wish);
	}
	
	public void clothDelete() {
		Clothes one = new Clothes();
		
		one.setId(control.getUserId());
		one.setType((String) table.getValueAt(table.getSelectedRow(), 1));
		one.setRed((colorList.get(table.getSelectedRow()).getRed()));
		one.setGreen((colorList.get(table.getSelectedRow()).getGreen()));
		one.setBlue((colorList.get(table.getSelectedRow()).getBlue()));
		one.setAlpha((colorList.get(table.getSelectedRow()).getAlpha()));
		
		clothesInfo = new ClothesInfo();
		clothesInfo.removeClothes(one);
		
		this.frame.setPanel(State.List);
	}

	public void userLogout() {
		this.frame.setPanel(State.Login);
	}


	
	private class Model extends DefaultTableModel {
		
		private static final long serialVersionUID = 1L;
		
		public Model(Object[][] rowData, String[] columnNames) {
			super(rowData, columnNames);
		}

		@Override
        public Class<? extends Object> getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }

		@Override
		public boolean isCellEditable(int rowIndex, int mColIndex) {
	        return false;
	    }
	}
	
	private class MouseListener extends MouseAdapter {
	 
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	
	    	if (e.getClickCount() > 1) {
	    		Clothes one = new Clothes();
	    		
	    		one.setId(control.getUserId());
	    		one.setType((String) table.getValueAt(table.getSelectedRow(), 1));
	    		one.setRed((colorList.get(table.getSelectedRow()).getRed()));
	    		one.setGreen((colorList.get(table.getSelectedRow()).getGreen()));
	    		one.setBlue((colorList.get(table.getSelectedRow()).getBlue()));
	    		one.setAlpha((colorList.get(table.getSelectedRow()).getAlpha()));
	    		
	    		control.setOne(one);
	    		control.setOther(clothes);
	    		frame.setPanel(State.Recommend);
	    	}
	    }
	}
	

	public ImageIcon createImage(Color c) {
		
		BufferedImage img = new BufferedImage(150, 15, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		g.setColor(c);
		g.fillRect(0, 0, 150, 15);
		
		return new ImageIcon(img);
	}
	
}
