package ui;

import java.util.*;
import model.*;

public class Main {
	private static Investor PetsSA;

	public Main() {
		PetsSA = new Investor();
		boolean paso = false;
		while (paso == false) {
			try {

				menu();
				paso = true;

				paso = true;
			} catch (InputMismatchException e) {
				System.out.println("please check the value you are entering is causing an error");
			}
		}

	}

	static void menu() {
		int option;
		do {
			Scanner scn = new Scanner(System.in);
			System.out.println("MENU\n");
			System.out.println("1.Add Club\n");
			System.out.println("2.Add Owner\n");
			System.out.println("3.Add Pet\n");
			System.out.println("4.Delete\n");
			System.out.println("5.Search\n");
			System.out.println("6.Save Changes\n");
			System.out.println("7.Read\n");
			System.out.println("8.Organize lists\n");
			System.out.println("9.Exit\n");
			option = scn.nextInt();
			if (option == 1) {
				System.out.println("Enter the club ID\n");
				int ID;
				ID = scn.nextInt();
				System.out.println("Enter the name of the club\n");
				String n;
				scn.nextLine();
				n = scn.nextLine();
				System.out.println("Enter the creation date\n");
				String f;
				f = scn.nextLine();
				System.out.println("Enter the types of pets the club manages\n");
				String t;
				t = scn.nextLine();
				System.out.println(PetsSA.addClub(ID, n, f, t));
			}
			if (option == 2) {
				System.out.println("Enter the name of the club to which the owner's pet or pets belong\n");
				String c;
				scn.nextLine();
				c = scn.nextLine();
				System.out.println("Enter the owner's CC\n");
				int cc;
				cc = scn.nextInt();
				System.out.println("Enter the name of the owner\n");
				String n;
				scn.nextLine();
				n = scn.nextLine();
				System.out.println("Enter the owner's last name\n");
				String ln;
				ln = scn.nextLine();
				System.out.println("Enter the owner's birth day\n");
				String na;
				na = scn.nextLine();
				System.out.println("Enter the owner's preferred pet type\n");
				String t;
				t = scn.nextLine();
				System.out.println(PetsSA.addOwners(c, cc, n, ln, na, t));

			}
			if (option == 3) {
				System.out.println("Enter the name of the club to which the pet belongs\n");
				String c;
				scn.nextLine();
				c = scn.nextLine();
				System.out.println("Enter the CC of the owner of the pet\n");
				int cc;
				cc = scn.nextInt();
				System.out.println("Enter the pet's identification number\n");
				int pID;
				pID = scn.nextInt();
				System.out.println("Enter the pet's name\n");
				String pn;
				scn.nextLine();
				pn = scn.nextLine();
				System.out.println("Enter the pet's birth day\n");
				String bp;
				bp = scn.nextLine();
				System.out.println("Enter the pet's gender\n");
				char g;
				g = scn.nextLine().charAt(0);
				System.out.println("Enter the pet's type\n");
				char t;
				t = scn.nextLine().charAt(0);
				System.out.println(PetsSA.addPet(c, cc, pID, pn, bp, g, t));
			}
			if (option == 4) {
				menudeleted();
				System.exit(0);
			}
			if (option == 5) {
				menuSearch();
				System.exit(0);
			}
			if (option == 6) {
				PetsSA.saveChanges();
			}
			if (option == 7) {
				PetsSA.read();
			}
			if (option == 8) {
				menuOrganize();
				System.exit(0);;
			}
			if (option == 9) {
				System.exit(0);
			}
		} while (option <= 9);
	}

	static void menuSearch() {
		int option2;
		Scanner scn2 = new Scanner(System.in);
		System.out.println("MENU\n");
		System.out.println("1.Search club with name\n");
		System.out.println("2.Search club with ID\n");
		System.out.println("3.Search owner with last name\n");
		System.out.println("4.Search owner with name\n");
		System.out.println("5.Search Pet witn name\n");
		System.out.println("6.Search pet with ID\n");
		System.out.println("7.Exit to principal menu\n");
		option2 = scn2.nextInt();
		if (option2 == 1) {
			System.out.println("Put the club name if you wich search");
			String nc;
			scn2.nextLine();
			nc = scn2.nextLine();
			PetsSA.bubbleClubName();
			System.out.println(PetsSA.searchClubNameComparation(nc));
			menu();
		}
		if (option2 == 2) {
			System.out.println("Put the club ID if you wich search");
			int id;
			id = scn2.nextInt();
			PetsSA.bubbleClubID();
			System.out.println(PetsSA.searchClubIDComparation(id));
			menu();
		}
		if (option2 == 3) {
			System.out.println("Enter the name of the club to which the owner you wish to search belongs");
			String nc;
			scn2.nextLine();
			nc = scn2.nextLine();
			System.out.println("Enter the last name of the owner you want to search");
			String lno;
			lno = scn2.nextLine();
			PetsSA.organizeOwnerLastName(nc);
			System.out.println(PetsSA.searchOwnerLastNameComparation(nc, lno));
			menu();

		}
		if (option2 == 4) {
			System.out.println("Enter the name of the club to which the owner you wish to search belongs");
			String nc;
			scn2.nextLine();
			nc = scn2.nextLine();
			System.out.println("Enter the name of the owner you want to search");
			String n;
			n = scn2.nextLine();
			PetsSA.organizeOwnerName(nc);
			System.out.println(PetsSA.searchOwnerNameComparation(nc, n));
			menu();
		}
		if (option2 == 5) {
			System.out.println("Enter the name of the club to which the pet you wish to search belongs");
			String nc;
			scn2.nextLine();
			nc = scn2.nextLine();
			System.out.println("Enter the ID of the owner of which the pet you wish to search belongs");
			int id;
			id = scn2.nextInt();
			System.out.println("Enter the name of the pet you want to search");
			String np;
			scn2.nextLine();
			np = scn2.nextLine();
			PetsSA.organizePetName(nc, id);
			System.out.println(PetsSA.searchPetName(nc, id, np));
			menu();
		}
		if (option2 == 6) {
			System.out.println("Enter the name of the club to which the pet you wish to search belongs");
			String nc;
			scn2.nextLine();
			nc = scn2.nextLine();
			System.out.println("Enter the ID of the owner of which the pet you wish to search belongs");
			int id;
			id = scn2.nextInt();
			System.out.println("Enter the pet ID you want to search");
			int pid;
			pid = scn2.nextInt();
			PetsSA.organizePetID(nc, pid);
			System.out.println(PetsSA.searchPetID(nc, id, pid));
			menu();
		}
		if (option2 == 7) {
			menu();
		}
	}

	static void menuOrganize() {
		int option3;
		Scanner scn3 = new Scanner(System.in);
		System.out.println("MENU\n");
		System.out.println("1.Organize list club with name\n");
		System.out.println("2.Organize list club with ID\n");
		System.out.println("3.Organize list club with number of owners\n");
		System.out.println("4.Organize list owner with last name\n");
		System.out.println("5.Organize list owner with name\n");
		System.out.println("6.Organize list owner with number of pets\n");
		System.out.println("7.Organize list pet with name\n");
		System.out.println("8.Organize list pet with ID\n");
		System.out.println("9.Exit to principal menu\n");
		option3 = scn3.nextInt();
		if (option3 == 1) {
			PetsSA.bubbleClubName();
			System.out.println(PetsSA.getListC());
			menu();
		}
		if (option3 == 2) {
			PetsSA.insercionClubID();
			System.out.println(PetsSA.getListC());
			menu();
		}
		if (option3 == 3) {
			PetsSA.bubbleNumberOfOwners();
			System.out.println(PetsSA.getListC());
			menu();
		}
		if (option3 == 4) {
			System.out.println("Enter the name of the club that owns the list of owners you want to organize");
			String nc;
			scn3.nextLine();
			nc = scn3.nextLine();
			PetsSA.organizeOwnerLastName(nc);
			menu();
		}
		if (option3 == 5) {
			System.out.println("Enter the name of the club that owns the list of owners you want to organize");
			String nc;
			scn3.nextLine();
			nc = scn3.nextLine();
			PetsSA.organizeOwnerName(nc);
			menu();
		}
		if (option3 == 6) {
			System.out.println("Enter the name of the club that owns the list of owners you want to organize");
			String nc;
			scn3.nextLine();
			nc = scn3.nextLine();
			PetsSA.organizeOwnerNumberPets(nc);
			menu();
		}
		if (option3 == 7) {
			System.out.println("Enter the name of the club of which the owner of the list you wish to order belongs");
			String nc;
			scn3.nextLine();
			nc = scn3.nextLine();
			System.out.println("Enter the ID of the owner who has the list you want to order");
			int id;
			id = scn3.nextInt();
			PetsSA.organizePetName(nc, id);
			menu();
		}
		if (option3 == 8) {
			System.out.println("Enter the name of the club of which the owner of the list you wish to order belongs");
			String nc;
			scn3.nextLine();
			nc = scn3.nextLine();
			System.out.println("Enter the ID of the owner who has the list you want to order");
			int id;
			id = scn3.nextInt();
			PetsSA.organizePetID(nc, id);
			menu();
		}
		if (option3 == 9) {
			menu();
		}
	}

	static void menudeleted() {
		int option4;
		Scanner scn4 = new Scanner(System.in);
		System.out.println("MENU\n");
		System.out.println("1.Deleted club with name\n");
		System.out.println("2.Deleted club with ID\n");
		System.out.println("3.Deleted owner with  name\n");
		System.out.println("4.Deleted owner with ID\n");
		System.out.println("5.Deleted Pet witn name\n");
		System.out.println("6.Deleted pet with ID\n");
		System.out.println("7.Exit to principal menu\n");
		option4 = scn4.nextInt();
		if (option4 == 1) {
			System.out.println("Enter the name of the club you wish to remove");
			String nc;
			scn4.nextLine();
			nc = scn4.nextLine();
			System.out.println(PetsSA.deleteClubWithName(nc));
			menu();
		}
		if (option4 == 2) {
			System.out.println("Enter the ID of the club you wish to remove");
			int id;
			id = scn4.nextInt();
			System.out.println(PetsSA.deleteClubWithID(id));
			menu();
		}
		if (option4 == 3) {
			System.out.println("Enter the name of the club you wish to delete");
			String nc;
			scn4.nextLine();
			nc = scn4.nextLine();
			System.out.println("Enter the name of the owner to delete");
			String no;
			no = scn4.nextLine();
			System.out.println("Enter the last name of the owner to delete");
			String ln;
			ln = scn4.nextLine();
			System.out.println(PetsSA.deleteOwnerWithName(nc, no, ln));
			menu();
		}
		if (option4 == 4) {
			System.out.println("Enter the name of the club you wish to delete");
			String nc;
			scn4.nextLine();
			nc = scn4.nextLine();
			System.out.println("Enter the ID of the owner to deleted");
			int id;
			id = scn4.nextInt();
			System.out.println(PetsSA.deleteOwnerWithID(nc, id));
			menu();
		}
		if (option4 == 5) {
			System.out.println("Enter the name of the club to which the pet owner you wish to remove belongs");
			String nc;
			scn4.nextLine();
			nc = scn4.nextLine();
			System.out.println("Enter the id of the pet owner you wish to remove");
			int id;
			id = scn4.nextInt();
			System.out.println("Enter the name of the pet to deleted");
			String pn;
			scn4.nextLine();
			pn = scn4.nextLine();
			System.out.println(PetsSA.deletePetWithName(nc, id, pn));
			menu();
		}
		if (option4 == 6) {
			System.out.println("Enter the name of the club to which the pet owner you wish to remove belongs");
			String nc;
			scn4.nextLine();
			nc = scn4.nextLine();
			System.out.println("Enter the id of the pet owner you wish to remove");
			int id;
			id = scn4.nextInt();
			System.out.println("Enter the id of the pet to deleted");
			int pid;
			pid = scn4.nextInt();
			System.out.println(PetsSA.deletePetWithID(nc, id, pid));
			menu();
		}
		if (option4 == 7) {
			menu();
		}
	}

	public static void main(String[] args) {
		Main main = new Main();

	}
}
