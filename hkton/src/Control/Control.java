package Control;

import java.util.ArrayList;

import model.Clothes;

public class Control {
	
	private String userId;
	private Clothes one;
	private ArrayList<Clothes> other;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Clothes getOne() {
		return one;
	}

	public void setOne(Clothes one) {
		this.one = one;
	}

	public ArrayList<Clothes> getOther() {
		return other;
	}

	public void setOther(ArrayList<Clothes> other) {
		this.other = other;
	}
	
}
