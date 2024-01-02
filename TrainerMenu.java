package trainingApp;

public class TrainerMenu {

	public void displayTrainerMenu() throws InvalidPasswordException {

		int choice = 0;

		while (choice != 4) {
			System.out.println("""

					Menu:
					1. Log in.
					2. Create an account.
					3. Reset password.
					4. Exit.

					""");
		}
	}

	public void displayTrainerMenu2() {

		int choice = 0;

		while (choice != 7) {
			System.out.println("""

					Menu:
					1. Profile.
					2. Trainings.
					3. View User's progress.
					4. Manage User's subscriptions.
					5. Notifications.
					6. Chat.
					7. Log out.

					""");

		}
	}

	public void displayTrainerMenuProfile() {

		int choice = 0;

		while (choice != 3) {
			System.out.println("""

					Profile Menu:
					1. Update profile.
					2. Manage account.

					""");
		}
	}

	public void displayTrainerMenuUpdateProfile() {

		int choice = 0;

		while (choice != 5) {
			System.out.println("""

					Update Profile Menu:
					1. Username.
					2. Password.
					3. Email.
					4. Name.

					""");
		}
	}

	public void displayTrainerMenuTrainings() {

		int choice = 0;

		while (choice != 5) {
			System.out.println("""

					Trainings Menu:
					1. Exercises.
					2. Create a Training.
					3. Modify a Training.
					4. Delete a Trainiing.

					""");

		}
	}

	public void displayTrainerMenuExercises() {

		int choice = 0;

		while (choice != 4) {
			System.out.println("""

					Exercises Menu:
					1. View exercises.
					2. Add Exercises.
					3. Delete Exercises.

					""");

		}
	}
}
