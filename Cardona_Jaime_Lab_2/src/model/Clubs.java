package model;

import java.io.*;
import java.util.*;

public class Clubs implements Comparable<Clubs>, Comparator<Clubs> {

	private ArrayList<Owners> listO;

	private int ID;

	private String clubName;

	private String creationDate;

	private String typesOfPets;

	public Clubs(int iD, String clubName, String creationDate, String typesOfPets) {
		ID = iD;
		this.clubName = clubName;
		this.creationDate = creationDate;
		this.typesOfPets = typesOfPets;
		listO = new ArrayList<Owners>();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getTypesOfPets() {
		return typesOfPets;
	}

	public void setTypesOfPets(String typesOfPets) {
		this.typesOfPets = typesOfPets;
	}

	public ArrayList<Owners> getListO() {
		return listO;
	}

	public void setListO(ArrayList<Owners> listO) {
		this.listO = listO;
	}

	public String addOwner(int Cc, String name, String lastName, String birthDay, String petPreference)
			throws OwnersSameID {
		String text = "";
		Owners newOwner = new Owners(Cc, name, lastName, birthDay, petPreference);
		if (searchOwner(Cc) != null) {
			throw new OwnersSameID();
		} else {
			listO.add(newOwner);
			text = "Successfully added";
		}
		return text;
	}

	public Owners searchOwner(int Cc) {
		Owners found = null;
		for (int i = 0; i < listO.size(); i++) {
			if (listO.get(i) != null && listO.get(i).getCC() == Cc) {
				found = listO.get(i);
			}
		}
		return found;
	}

	public void ownerSerializable() {
		try {
			FileOutputStream fs = new FileOutputStream(".\\" + clubName);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(listO);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String text = "";
		text = ID + "-" + clubName + "-" + creationDate + "-" + typesOfPets+"\n";
		return text;
	}

	public String text() {
		String text = "";
		text = ID + "-" + clubName + "-" + creationDate + "-" + typesOfPets;
		return text;
	}

	public void search(int cc, int pid) {
		Owners o = searchOwner(cc);
		if (o != null) {
			o.search(pid);
		}

	}

	public void saveChanges() {
		ownerSerializable();
	}

	public ArrayList<Owners> readSerializableOwners() {
		ArrayList<Owners> obj = null;

		try {

			File f = new File(".\\" + clubName);
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (ArrayList<Owners>) ois.readObject();
			listO = obj;
			ois.close();
			fis.close();

		} catch (IOException | ClassNotFoundException ioe) {

		}
		return obj;
	}

	@Override
	public int compare(Clubs o1, Clubs o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Clubs o) {
		return this.clubName.compareToIgnoreCase(o.getClubName());
	}

	public int searchOwnerName(String no) {
		int pos = -1;
		boolean found = false;
		for (int i = 0; i < listO.size() && found == false; i++) {
			if (listO.get(i) != null && listO.get(i).getName().equalsIgnoreCase(no)) {
				found = true;
				pos = i;
			}
		}
		return pos;
	}

	public void bubbleOwnerName() {
		Owners temp;
		for (int i = 1; i < listO.size(); i++) {
			for (int j = 0; j < listO.size() - 1; j++) {
				if (listO.get(j).compareTo(listO.get(j + 1)) > 0) {
					temp = listO.get(j);
					listO.set(j, listO.get(j + 1));
					listO.set(j + 1, temp);
				}
			}
		}
	}

	public int searchBinaryOwnerName(String cn) {
		int inicio = 0;
		int fin = listO.size() - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (listO.get(pos).getName().equals(cn))
				return pos;
			else if (listO.get(pos).getName().compareTo(cn) < 0) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return -1;
	}

	public String searchOwnerNameComparation(String nc) {
		String text = "";
		long t1 = System.nanoTime();
		searchOwnerName(nc);
		long t2 = System.nanoTime();
		text += "Normal search time :" + (t2 - t1)+"\n";
		long t3 = System.nanoTime();
		searchBinaryOwnerName(nc);
		long t4 = System.nanoTime();
		text += "Binary search time :" + (t4 - t3)+"\n";
		return text;
	}

	public void bubbleOwnerID() {
		Owners temp;
		for (int i = 1; i < listO.size(); i++) {
			for (int j = 0; j < listO.size() - 1; j++) {
				if (listO.get(j).getCC() > listO.get(j + 1).getCC()) {
					temp = listO.get(j);
					listO.set(j, listO.get(j + 1));
					listO.set(j + 1, temp);
				}
			}
		}
	}

	public int searchOwnerIDBinary(int id) {
		int inicio = 0;
		int fin = listO.size() - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (listO.get(pos).getCC() == id)
				return pos;
			else if (listO.get(pos).getCC() < id) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return -1;
	}

	public String searchOwnerIDComparation(int cc) {
		String text = "";
		long t1 = System.nanoTime();
		searchOwner(cc);
		long t2 = System.nanoTime();
		text += "Normal search time :" + (t2 - t1)+"\n";
		long t3 = System.nanoTime();
		searchOwnerIDBinary(cc);
		long t4 = System.nanoTime();
		text += "Binary search time" + (t4 - t3)+"\n";
		return text;
		
	}

	public int searchOwnerLastName(String lno) {
		int pos = 0;
		boolean found = false;
		for (int i = 0; i < listO.size() && found == false; i++) {
			if (listO.get(i) != null && listO.get(i).getLastName().equalsIgnoreCase(lno)) {
				found = true;
				pos = i;
			}
		}
		return pos;
	}

	public void bubbleOwnerLastName() {
		Owners temp;
		for (int i = 1; i < listO.size(); i++) {
			for (int j = 0; j < listO.size() - 1; j++) {
				if (listO.get(j).compareToLastName(listO.get(j + 1)) > 0) {
					temp = listO.get(j);
					listO.set(j, listO.get(j + 1));
					listO.set(j + 1, temp);
				}
			}
		}
	}

	public int searchBinaryOwnerLastName(String lno) {
		int inicio = 0;
		int fin = listO.size() - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (listO.get(pos).getLastName().equals(lno))
				return pos;
			else if (listO.get(pos).getLastName().compareTo(lno) < 0) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return -1;
	}

	public String searchOwnerLastNameComparation(String lno) {
		String text = "";
		long t1 = System.nanoTime();
		searchOwnerLastName(lno);
		long t2 = System.nanoTime();
		text += "Normal search time :" + (t2 - t1)+"\n";
		long t3 = System.nanoTime();
		searchBinaryOwnerLastName(lno);
		long t4 = System.nanoTime();
		text += "Binary search time" + (t4 - t3)+"\n";
		return text;
	}

	public void bubbleOwnerNumberOfPets() {
		Owners temp;
		for (int i = 1; i < listO.size(); i++) {
			for (int j = 0; j < listO.size() - 1; j++) {
				if (listO.get(j).getListP().size() > listO.get(j + 1).getListP().size()) {
					temp = listO.get(j);
					listO.set(j, listO.get(j + 1));
					listO.set(j + 1, temp);
				}
			}
		}
	}

	public void insercionOwnerName() {
		int i;
		int j;
		Owners aux;
		for (i = 1; i < listO.size(); i++) {
			aux = listO.get(i);
			j = i - 1;
			while ((j >= 0) && (aux.compareTo(listO.get(j)) < 0)) {
				listO.set(j + 1, listO.get(j));
				j--;
			}
			listO.set(j + 1, aux);
		}
	}
	public String deleteOwnerWithID(int id) {
		String text = "";
		boolean found = false;
		for(int i=0;i<listO.size() && found == false;i++) {
			if(listO.get(i).getCC() == id) {
				listO.remove(listO.get(i));
				found = true;
				text = "Successfully removed";
			}
			else {
				text = "The club you wish to delete does not exist";
			}
		}
		return text;
	}
	public String deleteOwnerWithName(String name,String lastName) {
		String text = "";
		boolean found = false;
		for(int i=0;i<listO.size() && found == false;i++) {
			if(listO.get(i).getName().equals(name) && listO.get(i).getLastName().equals(lastName)) {
				listO.remove(listO.get(i));
				found = true;
				text = "Successfully removed";
			}
			else {
				text = "The club you wish to delete does not exist";
			}
		}
		return text;
	}
}
