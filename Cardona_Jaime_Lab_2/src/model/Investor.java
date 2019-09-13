package model;

import java.util.*;

import java.io.*;

public class Investor {

	private ArrayList<Clubs> listC;

	public Investor() {
		listC = new ArrayList<Clubs>();
		addClub2(1, "Real Perruno", "21/07/1999", "DOG");
		addClub2(2, "Barcelona Gatos", "2/08/1997", "CAT");
		addClub2(3, "Borussia Aves", "4/08/1889", "DOG");
		addClub2(4, "America de Perros", "1/01/1927", "DOG");
		readTextScene();
	}

	public String addClub(int iD, String clubName, String creationDate, String typesOfPets) {
		String text = "";
		try {
			if (searchClubName(clubName) == null) {
				Clubs newClub = new Clubs(iD, clubName, creationDate, typesOfPets);
				listC.add(newClub);
				text = "Added correctly";
			} else {
				text = "There cannot be two clubs with the same name";
			}
		} catch (InputMismatchException e) {
			text = "Please only enter numbers in this part";
		}
		return text;
	}

	public String addOwners(String clubName, int cC, String name, String lastName, String birthDay,
			String petPreference) {
		boolean end = false;
		String text = "";
		while (end == false) {
			try {
				if (searchClubName(clubName) != null) {
					text = searchClubName(clubName).addOwner(cC, name, lastName, birthDay, petPreference);
					text = "Added correctly\n";
				} else {
					text = "Could not add a new owner";
				}
				end = true;
			} catch (OwnersSameID e) {
				text = "You already made an owner with that CC";
				end = true;
			}
		}
		return text;
	}

	public Clubs searchClubName(String nc) {
		Clubs found = null;
		for (int i = 0; i < listC.size() && found == null; i++) {
			if (listC.get(i) != null && nc.equals(listC.get(i).getClubName())) {
				found = listC.get(i);
			}
		}
		return found;
	}

	public void bubbleClubName() {
		Clubs temp;
		for (int i = 1; i < listC.size(); i++) {
			for (int j = 0; j < listC.size() - 1; j++) {
				if (listC.get(j).compareTo(listC.get(j + 1)) > 0) {
					temp = listC.get(j);
					listC.set(j, listC.get(j + 1));
					listC.set(j + 1, temp);
				}
			}
		}
	}

	public int searchBinaryClubName(String cn) {
		int inicio = 0;
		int fin = listC.size() - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (listC.get(pos).getClubName().equals(cn))
				return pos;
			else if (listC.get(pos).getClubName().compareTo(cn) < 0) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return -1;
	}

	public String searchClubNameComparation(String nc) {
		String text = "";
		long t1 = System.nanoTime();
		searchClubName(nc);
		long t2 = System.nanoTime();
		text += "Normal search time :" + (t2 - t1)+"\n";
		long t3 = System.nanoTime();
		searchBinaryClubName(nc);
		long t4 = System.nanoTime();
		text += "Binary search time :" + (t4 - t3)+"\n";
		return text;
	}

	public int searchClubID(int id) {
		int pos = 0;
		boolean found = false;
		for (int i = 0; i < listC.size() && found == false; i++) {
			if (listC.get(i) != null && listC.get(i).getID() == id) {
				pos = i;
				found = true;
			}
		}
		return pos;
	}

	public void bubbleClubID() {
		Clubs temp;
		for (int i = 1; i < listC.size(); i++) {
			for (int j = 0; j < listC.size() - 1; j++) {
				if (listC.get(j).getID() > listC.get(j + 1).getID()) {
					temp = listC.get(j);
					listC.set(j, listC.get(j + 1));
					listC.set(j + 1, temp);
				}
			}
		}
	}

	public int searchClubIDBinary(int id) {
		int inicio = 0;
		int fin = listC.size() - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (listC.get(pos).getID() == id)
				return pos;
			else if (listC.get(pos).getID() < id) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return -1;
	}

	public String searchClubIDComparation(int id) {
		String text = "";
		long t1 = System.nanoTime();
		searchClubID(id);
		long t2 = System.nanoTime();
		text += "Normal search time: " + (t2 - t1)+"\n";
		long t3 = System.nanoTime();
		searchClubIDBinary(id);
		long t4 = System.nanoTime();
		text += "Binary search time: " + (t4 - t3)+"\n";
		return text;
	}

	public String addPet(String nc, int cc, int pID, String name, String birthDay, char gender, char type) {
		String text = "";
		try {
			if (searchOwnerId(nc, cc) != null) {
				text = searchOwnerId(nc, cc).addPet(pID, name, birthDay, gender, type);
			}
		} catch (PetsSameName e) {
			text = "The pet already exists";

		}
		return text;
	}

	public Owners searchOwnerId(String nc, int cc) {
		Clubs found = searchClubName(nc);
		Owners found2 = null;
		if (found != null) {
			found2 = found.searchOwner(cc);
		}
		return found2;
	}

	public ArrayList<Clubs> getListC() {
		return listC;
	}

	public void setListC(ArrayList<Clubs> listC) {
		this.listC = listC;
	}

	public void textClub() {
		try {
			PrintWriter writer = new PrintWriter(".\\plano.txt");
			for (int i = 0; i < listC.size(); i++) {
				writer.println(listC.get(i).text());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Check the route please");
		}
	}

	public void search(String nc, int cc, int pid) {
		Clubs c = searchClubName(nc);
		if (c != null) {
			c.search(cc, pid);
		}
	}

	public void saveChanges() {
		textClub();
		for (int i = 0; i < listC.size(); i++) {
			listC.get(i).saveChanges();
		}
	}

	public void read() {
		readTextClub();
		for (int i = 0; i < listC.size(); i++) {
			listC.get(i).readSerializableOwners();
		}
	}

	public void readTextClub() {
		try {
			FileReader fr = new FileReader(".\\plano.txt");
			BufferedReader br = new BufferedReader(fr);
			String listCn[] = new String[4];
			String bfRead;
			while ((bfRead = br.readLine()) != null) {
				listCn = bfRead.split("-");
				int id = Integer.parseInt(listCn[0]);
				String clubName = listCn[1];
				String creationDate = listCn[2];
				String typePets = listCn[3];
				Clubs newClub = new Clubs(id, clubName, creationDate, typePets);
				listC.add(newClub);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}

	public void bubbleNumberOfOwners() {
		Clubs temp;
		for (int i = 1; i < listC.size(); i++) {
			for (int j = 0; j < listC.size() - 1; j++) {
				if (listC.get(i).getListO().size() > listC.get(j + 1).getListO().size()) {
					temp = listC.get(j);
					listC.set(j, listC.get(j + 1));
					listC.set(j + 1, temp);
				}
			}
		}
	}

	public String searchOwnerLastNameComparation(String nc, String lno) {
		String text = "Could not be found";
		Clubs club = searchClubName(nc);
		if (club != null) {
			text = club.searchOwnerLastNameComparation(lno);
		}
		return text;
	}

	public String searchOwnerNameComparation(String nc, String no) {
		String text = "Could not be found";
		Clubs clubs = searchClubName(nc);
		if (clubs != null) {
			text = clubs.searchOwnerNameComparation(no);
		}
		return text;
	}

	public String searchPetName(String nc, int cc, String pn) {
		String text = "Could not be found";
		Owners owner = searchOwnerId(nc, cc);
		if (owner != null) {
			text = owner.searchPetNameComparation(pn);
		}
		return text;
	}

	public String searchPetID(String nc, int cc, int pid) {
		String text = "Could not be found";
		Owners owner = searchOwnerId(nc, cc);
		if (owner != null) {
			text = owner.search(pid);
		}
		return text;
	}

	public void readTextScene() {
		try {
			FileReader fr = new FileReader(".\\escenario.txt");
			BufferedReader br = new BufferedReader(fr);
			String listE[] = new String[3];
			String bfRead;

			while ((bfRead = br.readLine()) != null) {
				listE = bfRead.split(",");
				int id = Integer.parseInt(listE[0]);
				String nameOwner = listE[1];
				String lastNameOwner = listE[2];
				double idc = (Math.random() * 4) + 1;
				int idc2 = (int) idc;
				double tp = (Math.random() * 3) + 1;
				int tp2 = (int) tp;
				String typePet = "";
				if (tp2 == 1) {
					typePet = "DOG";
				}
				if (tp2 == 2) {
					typePet = "CAT";
				}
				if (tp2 == 3) {
					typePet = "BIRD";
				}
				double bd = ((Math.random() * 5) + 1);
				int bd2 = (int) bd;
				String birthday = "";
				if (tp2 == 1) {
					birthday = "1/01/1999";
				}
				if (tp2 == 2) {
					birthday = "3/04/2003";
				}
				if (tp2 == 3) {
					birthday = "5/12/1989";
				}
				if (tp2 == 1) {
					birthday = "21/07/1978";
				}
				if (tp2 == 2) {
					birthday = "26/06/2002";
				}
				addOwners2(idc2, id, nameOwner, lastNameOwner, birthday, typePet);
				for(int i = 0;i<listE.length;i++) {
					addPet("Real Perruno", i+1, i, "Pepito", "21/07/2019", 'M', 'D');
					addPet("Barcelona Gatos", i+1, i, "Pepito", "21/07/2019", 'M', 'D');
					addPet("Borussia Aves", i+1, i, "Pepito", "21/07/2019", 'M', 'D');
					addPet("America Perros", i+1, i, "Pepito", "21/07/2019", 'M', 'D');
				}
			}
			br.close();
		} catch (Exception e) {

		}
	}

	public Clubs searchClubID2(int id) {
		Clubs club = null;
		boolean found = false;
		for (int i = 0; i < listC.size() && found == false; i++) {
			if (listC.get(i) != null && listC.get(i).getID() == id) {
				club = listC.get(i);
				found = true;
			}
		}
		return club;
	}

	public void addOwners2(int clubID, int cC, String name, String lastName, String birthDay, String petPreference) {
		if (searchClubID2(clubID) != null) {
			searchClubID2(clubID).addOwner(cC, name, lastName, birthDay, petPreference);
		}
	}

	public void addClub2(int iD, String clubName, String creationDate, String typesOfPets) {
		Clubs newClub = new Clubs(iD, clubName, creationDate, typesOfPets);
		listC.add(newClub);
		bubbleClubName();
		bubbleClubID();
	}

	public void insercionClubID() {
		int i;
		int j;
		Clubs aux;
		for (i = 1; i < listC.size(); i++) {
			aux = listC.get(i);
			j = i - 1;
			while ((j >= 0) && (aux.getID() < listC.get(j).getID())) {
				listC.set(j + 1, listC.get(j));
				j--;
			}
			listC.set(j + 1, aux);
		}
	}

	public void organizeOwnerLastName(String nc) {
		Clubs club = searchClubName(nc);
		if (club != null) {
			club.bubbleOwnerLastName();
			System.out.println(club.getListO());
		}
	}

	public void organizeOwnerName(String nc) {
		Clubs club = searchClubName(nc);
		if (club != null) {
			club.insercionOwnerName();
			System.out.println(club.getListO());
		}
	}

	public void organizeOwnerNumberPets(String nc) {
		Clubs club = searchClubName(nc);
		if (club != null) {
			club.bubbleOwnerNumberOfPets();
			System.out.println(club.getListO());
		}
	}

	public void organizePetName(String nc, int id) {
		Clubs club = searchClubName(nc);
		if (club != null) {
			Owners owner = club.searchOwner(id);
			if (owner != null) {
				owner.bubbleNamePet();
				System.out.println(owner.getListP());
			}
		}
	}

	public void organizePetID(String nc, int id) {
		Clubs club = searchClubName(nc);
		if (club != null) {
			Owners owner = club.searchOwner(id);
			if (owner != null) {
				owner.seleccionPetID();
				System.out.println(owner.getListP());
			}
		}
	}

	public String deleteClubWithID(int id) {
		String text = "The club you wish to delete does not exist"; 
		boolean found = false;
		for (int i = 0; i < listC.size() && found == false; i++) {
			if (listC.get(i).getID() == id) {
				listC.remove(listC.get(i));
				found = true;
				text = "Successfully removed";
			} 
		}
		return text;
	}

	public String deleteClubWithName(String name) {
		String text = "The club you wish to delete does not exist";
		boolean found = false;
		for (int i = 0; i < listC.size() && found == false; i++) {
			if (listC.get(i).getClubName().equals(name)) {
				listC.remove(listC.get(i));
				found = true;
				text = "Successfully removed";
			}
		}
		return text;
	}

	public String deleteOwnerWithName(String nc, String name, String lastName) {
		String text = "Could not delete owner";
		Clubs club = searchClubName(nc);
		if (club != null) {
			text = club.deleteOwnerWithName(name, lastName);
		}
		return text;
	}

	public String deleteOwnerWithID(String nc, int id) {
		String text = "Could not delete owner";
		Clubs club = searchClubName(nc);
		if (club != null) {
			text = club.deleteOwnerWithID(id);
		}
		return text;
	}

	public String deletePetWithName(String nc, int id, String np) {
		String text ="The pet could not be deleted";
		Clubs club = searchClubName(nc);
		if (club != null) {
			Owners owner = club.searchOwner(id);
			if (owner != null) {
				text = owner.deletePetWithName(np);
			}
		}
		return text;
	}

	public String deletePetWithID(String nc, int id, int pid) {
		String text = "The pet could not be deleted";
		Clubs club = searchClubName(nc);
		if (club != null) {
			Owners owner = club.searchOwner(id);
			if (owner != null) {
				text = owner.deletePetWithID(pid);
			}
		}
		return text;
	}
}
