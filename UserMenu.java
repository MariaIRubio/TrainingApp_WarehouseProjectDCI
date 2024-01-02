package trainingApp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMenu {

	User user;
	Scanner scanner = new Scanner(System.in);

	public void displayUserMenu() throws InvalidPasswordException {

		int choice = 0;

		while (choice != 4) {
			System.out.println("""

					Menu:
					1. Log in.
					2. Create an account.
					3. Reset password.
					4. Exit.
					
					""");
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1 -> user.logIn();
				case 2 -> user.createUserAccount();
				case 3 -> user.resetPassword();
				case 4 -> System.out.println("Exiting the program");
				default -> System.out.println("Invalid option. Please choose a right one.");
				}
				;

			} catch (InputMismatchException e) {
				System.out.println("Option must be a number.");
				scanner.nextLine();
			}
		}
	}

	public void displayUserMenu2() {

		int choice = 0;

		while (choice != 5) {
			System.out.println("""

					Menu:
					1. Profile.
					2. Training.
					3. Notifications.
					4. Chat.
					4. Log out.
					
					""");
		}
	}

	public void displayUserMenuProfile() {

		int choice = 0;

		while (choice != 4) {
			System.out.println("""

					Profile Menu:
					1. Update Profile.
					2. Payment Method.
					3. Membership.
					
					""");
		}

	}

	public void displayUserMenuUpdateProfile() {

		int choice = 0;

		while (choice != 9) {
			System.out.println("""

					Update Profile Menu:
					1. Username.
					2. Password.
					3. Email.
					4. Name
					5. Age.
					6. Height.
					7. Weight.
					8. Injuries.
					
					""");
		}
	}
	
	public void displayUserPaymentMethodMenu() {

		int choice = 0;

		while (choice != 4) {
			System.out.println("""

					Payment Method Menu:
					1. Choose Payment Method.
					2. Change Payment Method.
					3. Payment history.
					4. Membership.
					
					""");
		}
	}
	
	public void displayUserMenuMembership() {

		int choice = 0;

		while (choice != 3) {
			System.out.println("""

					Memership Menu:
					1. My Membership.
					2. Update Membership.
					
					""");
		}
	}
	
	public void displayUserTrainingMethod() {

		int choice = 0;

		while (choice != 4) {
			System.out.println("""

					Trainig Menu:
					1. Training of the day.
					2. Leave a comment.
					3. Calendar.
					4. View my progress.
					
					""");
		}	
	}
}
