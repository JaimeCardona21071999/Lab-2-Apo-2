package model;

import java.io.Serializable;
import java.util.Comparator;

public class Pets implements Serializable, Comparable<Pets>, Comparator<Pets> {

	
	private int PID;

	private String name;

	private String birthDay;

	private char gender;

	private char type;

	public Pets(int pID, String name, String birthDay, char gender, char type) {
		PID = pID;
		this.name = name;
		this.birthDay = birthDay;
		this.gender = gender;
		this.type = type;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Pets [PID=" + PID + ", name=" + name + ", birthDay=" + birthDay + ", gender=" + gender + ", type="
				+ type + "]";
	}

	@Override
	public int compare(Pets arg0, Pets arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Pets a) {
		return this.name.compareToIgnoreCase(a.name);
	}
}
