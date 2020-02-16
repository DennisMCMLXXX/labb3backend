package consumeOne;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;

public class Meny {
	Scanner inputScanner = new Scanner(System.in);
	APIconn apiconn = new APIconn();
	JSONArray jsonArr = null;
	int counter = 0;

	public void startMenu() {
		if (counter > 1) {
			String temp = "";
			System.out.println("Press 'y' key and press enter to continue...");
			temp = inputScanner.next();
		}
		counter = 42;

		System.out.println(
				"Welcome to ConsumeOne!\n\nPlease make one of the following options: \n1. Get all homies \n2. Get by id\n3. Get dude by acual name\n4. Add a dude\n5. Change a dude by id\n6. Delete a dude (seriously?)\n9. Exit");
		int val = inputScanner.nextInt();
		switchMeny(val);
	}

	private void switchMeny(int choice) {
		switch (choice) {
		case 1:
			showAll();
			startMenu();
			break;
		case 2:
			getId();
			startMenu();
			break;
		case 3:
			getName();
			startMenu();
			break;
		case 4:
			add();
			startMenu();
			break;
		case 5:
			update();
			startMenu();
			break;
		case 6:
			deleteById();
			startMenu();
			break;
		case 9:
			System.exit(0);
		}
	}

	private void showAll() {
		try {
			jsonArr = new JSONArray(apiconn.getAll());
			printToConsole();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getId() {
		System.out.printf("Id: ");
		int id = inputScanner.nextInt();
		try {
			jsonArr = new JSONArray(apiconn.getId(id));
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		printToConsole();
	}

	private void getName() {
		System.out.printf("Name: ");
		String name = inputScanner.nextLine();
		try {
			jsonArr = new JSONArray(apiconn.getName(name));
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		printToConsole();
	}

	private void add() {
		System.out.printf("Name: ");
		String name = inputScanner.next();
		if (name != "")
			System.out.printf("Profession: ");
		String profession = inputScanner.next();

		try {
			apiconn.addPerson(name, profession);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void update() {
		System.out.printf("Id: ");
		int id = inputScanner.nextInt();
		System.out.printf("Name: ");
		String name = inputScanner.next();
		if (name != "")
			System.out.printf("Profession: ");
		String profession = inputScanner.next();
		try {
			apiconn.updatePerson(id, name, profession);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteById() {
		System.out.printf("Id: ");
		int id = inputScanner.nextInt();
		try {
			apiconn.deletePerson(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printToConsole() {
		for (Object x : jsonArr) {
			System.out.println(x);
		}
	}
}
