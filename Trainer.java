package trainingApp;

import java.util.List;
import java.util.Scanner;

public class Trainer {

	private String username;
	private String password;
	private String email;
	private String name;

	User user;
	List<User> usersList;
	Scanner scanner = new Scanner(System.in);

	public Trainer() {
	}

	public Trainer(String username, String password, String email, String name) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;

	}

	public Trainer(User user) {
		this.user = user;
		this.usersList = user.getUsersList();
	}

	public String getTrainerUsername() {
		return username;
	}

	public void setTrainerUsername(String username) {
		this.username = username;
	}

	public String getTrainerPassword() {
		return password;
	}

	public void setTrainerPassword(String password) {
		this.password = password;
	}

	public String getTrainerEmail() {
		return email;
	}

	public void setTrainerEmail(String email) {
		this.email = email;
	}

	public String getTrainerName() {
		return name;
	}

	public void setTrainerName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return """

				Account info:
				Username: %s
				Password: %s
				Email: %s
				Name: %s
				""".formatted(username, password, email, name);
	}

	// DISPLAY USERS INFORMATION
	public void displayUsersInformation() {
		// User user = new User(null, null, null, null, 0, 0, 0, null);
		System.out.println("\nDisplay User's info: ");
		if (!usersList.isEmpty()) {
			usersList.forEach(System.out::println);
		} else {
			System.out.println("The Users list is empty.");
		}
	}

	// DISPLAY ONE USER INFORMATION
	public void displaySingleUserInformation() {
		System.out.println("\nDisplay User Information: ");
		System.out.print("Introduce the username: ");
		String username = scanner.nextLine();
		boolean userFound = false;

		if (!usersList.isEmpty()) {
			for (User user : usersList) {
		
				if (user.getUserUsername().equals(username)) {
					System.out.println(user);
					userFound = true;
					break;
				}
			}
		} else {
			System.out.println("The Users list is empty.");
		}

		if (!userFound) {
			System.out.println("User not found.");
		}
	}

}
