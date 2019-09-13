package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Owners implements Serializable, Comparable<Owners>, Comparator<Owners> {

	private ArrayList<Pets> listP;

	private int CC;

	private String name;

	private String lastName;

	private String birthDay;

	private String petPreference;

	public Owners(int cC, String name, String lastName, String birthDay, String petPreference) {
		CC = cC;
		this.name = name;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.petPreference = petPreference;
		listP = new ArrayList<Pets>();
	}

	public int getCC() {
		return CC;
	}

	public void setCC(int cC) {
		CC = cC;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getPetPreference() {
		return petPreference;
	}

	public void setPetPreference(String petPreference) {
		this.petPreference = petPreference;
	}

	public ArrayList<Pets> getListP() {
		return listP;
	}

	public void setListP(ArrayList<Pets> listP) {
		this.listP = listP;
	}

	public String addPet(int pID, String name2, String birthDay2, char gender, char type) throws PetsSameName {
		String text = "";
		Pets newPet = new Pets(pID, name2, birthDay2, gender, type);
		if (searchPetName(name2) != null) {
			throw new PetsSameName();

		} else {
			listP.add(newPet);
			text = "Successfully added";
		}
		return text;
	}

	public Pets searchPetName(String np) {
		Pets found = null;
		for (int i = 0; i < listP.size(); i++) {
			if (listP.get(i) != null && listP.get(i).getName().equals(np)) {
				found = listP.get(i);
			}
		}
		return found;
	}

	public int searchPetPID(int pid) {
		int found = -1;
		for (int i = 0; i < listP.size(); i++) {
			if (listP.get(i) != null && listP.get(i).getPID() == pid) {
				found = i;
			}
		}
		return found;
	}

	@Override
	public String toString() {
		return "Owners [CC=" + CC + ", name=" + name + ", lastName=" + lastName + ", birthDay=" + birthDay
				+ ", petPreference=" + petPreference + "] \n";
	}

	public void bubblePID() {
		Pets temp;
		for (int i = 1; i < listP.size(); i++) {
			for (int j = 0; j < listP.size() - 1; j++) {
				if (listP.get(j).getPID() > listP.get(j + 1).getPID()) {
					temp = listP.get(j);
					listP.set(j, listP.get(j + 1));
					listP.set(j + 1, temp);
				}
			}
		}
	}

	public void bubbleNamePet() {
		Pets temp;
		for (int i = 1; i < listP.size(); i++) {
			for (int j = 0; j < listP.size() - 1; j++) {
				if (listP.get(j).compareTo(listP.get(j + 1)) > 0) {
					temp = listP.get(j);
					listP.set(j, listP.get(j + 1));
					listP.set(j + 1, temp);
				}
			}
		}
	}

	public int searchBinaryPetID(int pid) {
		bubblePID();
		int inicio = 0;
		int fin = listP.size() - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (listP.get(pos).getPID() == pid)
				return pos;
			else if (listP.get(pos).getPID() < pid) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return -1;
	}

	public String search(int pid) {
		String text = "";
		long t1 = System.nanoTime();
		searchBinaryPetID(pid);
		long t2 = System.nanoTime();
		text += "Binary search time :" + (t2 - t1)+"\n";
		long t3 = System.nanoTime();
		searchPetPID(pid);
		long t4 = System.nanoTime();
		text += "Normal search time :" + (t4 - t3)+"\n";
		return text;
	}

	@Override
	public int compare(Owners o1, Owners o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int searchBinaryPetName(String pn) {
		bubbleNamePet();
		int inicio = 0;
		int fin = listP.size() - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (listP.get(pos).getName().equals(pn))
				return pos;
			else if (listP.get(pos).getName().compareTo(pn) < 0) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return -1;
	}

	@Override
	public int compareTo(Owners a) {
		return this.name.compareToIgnoreCase(a.getName());
	}

	public int compareToLastName(Owners a) {
		return this.lastName.compareToIgnoreCase(a.getLastName());
	}

	public String searchPetNameComparation(String pn) {
		String text = "";
		long t1 = System.nanoTime();
		searchPetName(pn);
		long t2 = System.nanoTime();
		text += "Normal search time :" + (t2 - t1)+"\n";
		long t3 = System.nanoTime();
		searchBinaryPetName(pn);
		long t4 = System.nanoTime();
		text += "Binary search time :" + (t4 - t3)+"\n";
		return text;

	}

	public void seleccionPetID() {
		int i;
		int j;
		int menor;
		int pos;
		Pets temp;
		for (i = 0; i < listP.size() - 1; i++) {
			menor = listP.get(i).getPID();
			pos = i;
			for (j = i + 1; j < listP.size(); j++) {
				if (listP.get(j).getPID() < menor) {
					menor = listP.get(j).getPID();
					pos = j;
				}
			}
			if (pos != i) {
				temp = listP.get(i);
				listP.set(i, listP.get(pos));
				listP.set(pos, temp);
			}
		}
	}

	public String deletePetWithID(int id) {
		String text = "";
		boolean found = false;
		for (int i = 0; i < listP.size() && found == false; i++) {
			if (listP.get(i).getPID() == id) {
				listP.remove(listP.get(i));
				found = true;
				text = "Successfully removed";
			} else {
				text = "The club you wish to delete does not exist";
			}
		}
		return text;
	}

	public String deletePetWithName(String name) {
		String text = "";
		boolean found = false;
		for (int i = 0; i < listP.size() && found == false; i++) {
			if (listP.get(i).getName().equals(name)) {
				listP.remove(listP.get(i));
				found = true;
				text = "Successfully removed";
			} else {
				text = "The club you wish to delete does not exist";
			}
		}
		return text;
	}
}
