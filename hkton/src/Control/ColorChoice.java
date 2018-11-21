package Control;
import java.util.ArrayList;

import model.Clothes;


public class ColorChoice {

	public Clothes match (Clothes one, ArrayList<Clothes> Clothes) {
		
		Clothes rs = null;
		
		int current = 0;
		int next = 0;
		
		for(Clothes other : Clothes) {
			
			if (!one.getType().equals(other.getType())) {
				next = (int) (2*(Math.pow(one.getRed()-other.getRed(), 2) +
						4*Math.pow(one.getGreen()-other.getGreen(), 2) +
						3*Math.pow(one.getBlue()-other.getBlue(), 2)));

				if (current == 0 || current > next) {
					current = next;
					rs = other;
				}		
			}
		}
		
		
		if (current > 150000) {return null;}
	
		return rs;
	}
	
/*
	public static void main(String[] args) {
		
		int r1,g1,b1;
		int r2,g2,b2;
		int r3,g3,b3;
		int r4,g4,b4;
		int r5,g5,b5;
		int D2,D3,D4,D5;
		
		r1=color1.getRed();
		g1=color1.getGreen();
		b1=color1.getBlue();
		
		
		r2=color2.getRed();
		g2=color2.getGreen();
		b2=color2.getBlue();
		D2= (int) (2*(Math.pow(r2-r1, 2) + 4*Math.pow(g2-g1, 2)+ 3*Math.pow(b2-b1, 2)));
		System.out.println(D2);
		
		r3=color3.getRed();
		g3=color3.getGreen();
		b3=color3.getBlue();
		D3= (int) (2*(Math.pow(r3-r1, 2) + 4*Math.pow(g3-g1, 2)+ 3*Math.pow(b3-b1, 2)));
		System.out.println(D3);
		
		r4=color4.getRed();
		g4=color4.getGreen();
		b4=color4.getBlue();
		D4= (int) (2*(Math.pow(r4-r1, 2) + 4*Math.pow(g4-g1, 2)+ 3*Math.pow(b4-b1, 2)));
		System.out.println(D4);
		
		r5=color5.getRed();
		g5=color5.getGreen();
		b5=color5.getBlue();
		D5= (int) (2*(Math.pow(r5-r1, 2) + 4*Math.pow(g5-g1, 2)+ 3*Math.pow(b5-b1, 2)));
		System.out.println(D5);
		
		int min = Math.min(Math.min(D2, D3),Math.min(D4,D5)); 
		System.out.println(min);
		if(min==D2)
		{
			System.out.println("D2");
		}
		if(min==D3)
		{
			System.out.println("D3");
		}

		if(min==D4)
		{
			System.out.println("D4");
		}

		if(min==D5)
		{
			System.out.println("D5");
		}
*/
}





