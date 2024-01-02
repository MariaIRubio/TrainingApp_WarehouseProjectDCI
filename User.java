package trainingApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

	private String username;
	private String email;
	private String password;
	private String name;
	private int age;
	private double weight;
	private int height;
	List<String> injuries = new ArrayList<>();
	private boolean activeSubscription;

	Map<String, String> usernamesEmailsList = new HashMap<>();
	Set<String> emailsList = new HashSet<>();
	Map<String, String> usersCredentialsList = new HashMap<>();
	List<User> usersList = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);

	public User() {

	}

	public User(String username, String email, String password, String name, int age, double weight, int height,
			List<String> injuries) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.injuries = (injuries != null) ? new ArrayList<>(injuries) : new ArrayList<>();
		this.activeSubscription = false;
		this.usersList = (usersList != null) ? new ArrayList<>(usersList) : new ArrayList<>();	}

	public String getUserUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserEmail() {
		return email;
	}

	public void setUserEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return password;
	}

	public void setUserPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return name;
	}

	public void setUserName(String name) {
		this.name = name;
	}

	public int getUserAge() {
		return age;
	}

	public void setUserAge(int age) {
		this.age = age;
	}

	public double getUserWeight() {
		return weight;
	}

	public void setUserWeight(double weight) {
		this.weight = weight;
	}

	public double getUserHeight() {
		return height;
	}

	public void setUserHeight(int height) {
		this.height = height;
	}

	public List<String> getUserInjuries() {
		return injuries;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUserInjuries(List<String> injuries) {
		this.injuries = injuries;
	}

	public boolean isActiveUserSubscription() {
		return activeSubscription;
	}

	public void setActiveUserSubscription(boolean activeSubscription) {
		this.activeSubscription = activeSubscription;
	}

	@Override
	public String toString() {
		return """

				Account info:
				Username: %s
				Email: %s
				Password: %s

				Profile info:
				Name: %s
				Age: %d
				Weight: %.2f Kg
				Height: %d cm
				Injuries: %s
				Subscription: %b
				""".formatted(username, email, password, name, age, weight, height, injuries, activeSubscription);
	}

	// CREATE USER ACCOUNT
	public void createUserAccount() throws InvalidPasswordException {
		System.out.println("Create an Account.\n");
		enterUsername();
		enterPassword();
		enterEmail();
		System.out.println("\nAccount created succesfully!\n");
	}
	
	// CREATE A NEW USER (WITH ACCOUNT AND PROFILE)
	public void createNewUser() throws InvalidPasswordException {
		createUserAccount();
		createUserProfile();

		User user = new User(username, email, password, name, age, weight, height, injuries);
		usersList.add(user);
		System.out.println("\nUser created succesfully.");

		usersList.forEach(System.out::println);

	}

	// CREATE USERNAME
	public void enterUsername() {
		boolean validUsername = false;

		while (!validUsername) {
			try {
				System.out.print("Choose an username: ");
				String usernameInput = scanner.nextLine();
				validUsername = usernameInput != null && !usernameInput.isEmpty()
						&& usernameInput.matches("^[a-zA-Z0-9]+$");
				if (!validUsername) {
					System.out.println("Username no valid. Enter a valid one.\n");

				} else {
					this.username = usernameInput;

				}
			} catch (IllegalArgumentException e) {
				System.out.println("The username don't fulfill the requirements. Please check it again.");
			}
		}

	}

	// CREATE USER PASSWORD
	public void enterPassword() throws InvalidPasswordException {

		boolean validPassword = false;
		while (!validPassword) {
			try {
				System.out.print("""
						Choose a password.
						It must contain minimun 8 characteres, one capital letter and one number: """);
				String passwordInput = scanner.nextLine();

				if (passwordInput != null && passwordInput.length() >= 8 && passwordInput.matches(".*[A-Z].*")
						&& passwordInput.matches(".*\\d.*")) {
					this.password = passwordInput;
					validPassword = true;
				} else {
					validPassword = false;
					throw new InvalidPasswordException("Password Invalid. Try again. ");

				}
			} catch (InvalidPasswordException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	// ASSOCIATE AN EMAIL TO THE ACCOUNT
	public void enterEmail() {

		boolean validEmail = false;
		Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");

		while (!validEmail) {
			try {
				System.out.print("Enter your email account: ");
				String emailInput = scanner.nextLine();
				if (emailInput != null && !emailInput.isEmpty()) {
					Matcher matcher = emailPattern.matcher(emailInput);

					if (matcher.matches()) {
						if (emailsList.contains(emailInput)) {
							System.out.println("This email is already in use. Try with another one.");
						} else {
							this.email = emailInput;
							emailsList.add(emailInput);
							usernamesEmailsList.put(email, username);
							usersCredentialsList.put(emailInput, password);
							validEmail = true;
						}
					} else {
						System.out.println("Invalid email format. Please enter a valid email.");
					}
				} else {
					System.out.println("Invalid email. Please enter a valid one.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Email must have an email format.");
			}
		}

		/*
		 * usersAccountList .forEach((email, username) -> System.out.println("\nEmail: "
		 * + email + "\nUsername: " + username));
		 * emailsList.forEach(System.out::println);
		 */

	}

	// CREATE AN USER PROFILE
	public void createUserProfile() {
		System.out.println("Create a Profile.\n");
		enterName();
		enterAge();
		enterWeight();
		enterHeight();
		enterInjuries();

		System.out.println("\nProfile created succesfully!");
	}

	// NAME
	public void enterName() {
		while (true) {
			try {
				System.out.print("Enter your name: ");
				String nameInput = scanner.nextLine();
				if (nameInput != null && !nameInput.isEmpty() && nameInput.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
					String[] nameParts = nameInput.trim().split("\\s+");
					StringBuilder formattedName = new StringBuilder();
					for (String part : nameParts) {
						if (!part.isEmpty()) {
							formattedName.append(part.substring(0, 1).toUpperCase())
									.append(part.substring(1).toLowerCase()).append(" ");
						}
					}
					this.name = formattedName.toString().trim();
					break;
				} else {
					System.out.println("Invalid name. Please enter a valid name with only letters.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid name.\n");
			}
		}
	}

	// AGE
	public void enterAge() {
		while (true) {
			try {
				System.out.print("Enter your age: ");
				int ageInput = scanner.nextInt();
				if (ageInput != 0 && ageInput < 100) {
					this.age = ageInput;
					break;
				} else {
					System.out.println("Check your age again. It shouldn't be 0 or bigger than 100.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid age. Age must be a number.\n");
				scanner.next();
			}
		}

	}

	// WEIGHT
	public void enterWeight() {
		while (true) {
			try {
				System.out.print("Enter your weight in Kg (Ex. 62.5): ");
				double weightInput = scanner.nextDouble();
				if (weightInput != 0.0) {
					this.weight = weightInput;
					break;
				} else {
					System.out.println("Weight can't be 0 Kg.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid weight. Weight must be a number.\n");
				scanner.next();
			}
		}
	}

	// HEIGHT
	public void enterHeight() {
		while (true) {
			try {
				System.out.print("Enter your height in cm. (Ex. 176): ");
				int heightInput = scanner.nextInt();
				scanner.nextLine();
				if (heightInput != 0.0) {
					this.height = heightInput;
					break;
				} else {
					System.out.println("Height can't be 0 cm.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid height. Height must be a number.\n");
				scanner.next();
			}
		}
	}

	// INJURIES
	public void enterInjuries() {
		while (true) {
			try {
				System.out.print("Enter your injuries separated by a comma. (Ex. Shoulder, Hip, Knie, ...): ");
				String injuriesInput = scanner.nextLine();
				if (injuriesInput != null && !injuriesInput.isEmpty()) {
					String[] injuriesArray = injuriesInput.split(",");

					for (String injury : injuriesArray) {
						String formattedInjury = injury.trim().substring(0, 1).toUpperCase()
								+ injury.trim().substring(1).toLowerCase();
						this.injuries.add(formattedInjury);
					}

					if (this.injuries.isEmpty()) {
						System.out.println("Add at least one injury to assign your a personalized training.");
					} else {
						break;
					}
				} else {
					System.out.println("Add your injuries to assign your a personalized training.");
				}

				// injuries.forEach(System.out::println);
			} catch (InputMismatchException | StringIndexOutOfBoundsException e) {
				System.out.println("The list can only contain words.");
				System.out.println("List must contain any injurie.\n");
			}
		}
	}

	// LOG IN
	public void logIn() {
		System.out.println("\nLog in");

		boolean loggedIn = false;
		int logginAttempts = 0;
		final int maxLogginAttempts = 3;

		while (!loggedIn && logginAttempts < maxLogginAttempts) {
			System.out.print("Email: ");
			String emailLogIn = scanner.nextLine();
			System.out.print("Password: ");
			String passwordLogIn = scanner.nextLine();

			if (validateCredentials(emailLogIn, passwordLogIn)) {
				System.out.println("Access validated.");
				loggedIn = true;
			} else {
				if (!emailsList.contains(emailLogIn)) {
					System.out.println("This email is not registered. You must create an account.");
					break;
				} else {
					logginAttempts++;
					if (logginAttempts == maxLogginAttempts) {
						System.out.println(
								"\nYou tried to login too many times. Please wait 24h or reset your password.");
						return;
					} else {
						System.out.println("Incorrect password. Try again.\n");
					}
				}
			}
		}

		if (!loggedIn) {
			System.out.println("Log in not succcesful.");
		}
	}

	// VALIDATE CREDENTIALS
	private boolean validateCredentials(String emailLogIn, String passwordLogIn) {
		if (emailsList.contains(emailLogIn)) {
			String storedPassword = usersCredentialsList.get(emailLogIn);
			if (storedPassword != null && storedPassword.equals(passwordLogIn)) {
				return true;
			} else {
				return false;
			}
		} else {

			return false;
		}

	}

	// RESET PASSWORD
	public void resetPassword() throws InvalidPasswordException {
		System.out.println("\nReset Password.");
		System.out.print("Enter your email: ");
		String email = scanner.nextLine();

		if (emailsList.contains(email)) {
			System.out.println("Enter your new password: ");
			String newPassword = scanner.nextLine();

			try {
				validateNewPassword(newPassword);
				usersCredentialsList.put(email, newPassword);
				System.out.println("Password updated.");

			} catch (InvalidPasswordException e) {
				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("Email not found. Please enter a valid email.");

		}
	}

	private void validateNewPassword(String newPassword) throws InvalidPasswordException {

		while (true) {
			try {
				if (newPassword != null && newPassword.length() >= 8 && newPassword.matches(".*[A-Z].*")
						&& newPassword.matches(".*\\d.*")) {
					this.password = newPassword;
					break;
				} else {
					throw new InvalidPasswordException("Password Invalid. Please enter a valid password.");
				}

			} catch (InvalidPasswordException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
