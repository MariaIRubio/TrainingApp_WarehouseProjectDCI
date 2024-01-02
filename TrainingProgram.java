package trainingApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TrainingProgram {

	private List<User> usersList; // A ist with all the users from the app
	private List<Exercise> exercisesList; // A list with all the exercises that exists in the application, without assignation
	private Map<String, List<Exercise>> trainingsPrograms; // A list with trainings programs already selected and assingnated to the user
	private List<Exercise> userExercises; // List of exercises selected for the user
	Scanner scanner = new Scanner(System.in);
	Exercise exercise = new Exercise();
	private Map<String, List<Exercise>> trainingSchedule; // assign the training to specific days of the week

	public TrainingProgram() {
	}

	public TrainingProgram(List<User> usersList, List<Exercise> exercisesList) {
		this.usersList = usersList;
		this.exercisesList = exercisesList;
		this.trainingsPrograms = new HashMap<>();
		this.userExercises = new ArrayList<>();
		this.trainingSchedule = new HashMap<>();
	}

	public Map<String, List<Exercise>> getTrainingProgram() {
		return trainingsPrograms;
	}

	@Override
	public String toString() {
		return "Training Program: \n" + trainingsPrograms;
	}

	// CREATE A NEW TRAINING PROGRAM ASSINGNED TO A PERSON BY USERNAME
	public void createTrainingProgram() {
		int durationInWeeks = 0;
		System.out.println("\nCreating a training program");
		System.out.print("Introduce the username of the User you want to assign the training: ");
		String userUsername = scanner.nextLine();
		boolean userFound = false;

		for (User user : usersList) {
			if (user.getUserUsername().equalsIgnoreCase(userUsername)) {
				System.out.println("Creating a training program for " + user.getUserUsername());
				userFound = true;
				break;
			}
		}

		if (!userFound) {
			System.out.println("User " + userUsername + " is not found.");
			return;
		}

		System.out.print("\nHow many exercises do you want to add: ");
		int numberExercises = scanner.nextInt();
		scanner.nextLine();
		if (numberExercises <= exercisesList.size()) {
			for (int i = 0; i < numberExercises; i++) {
				System.out.print("Introduce the name of the exercise " + (i + 1) + ": ");
				String exerciseName = scanner.nextLine();
				boolean exerciseFound = false;

				for (Exercise exercise : exercisesList) {
					if (exercise.getName().equalsIgnoreCase(exerciseName) && !userExercises.contains(exercise)) {
						userExercises.add(exercise);
						System.out.println("Exercise " + exercise.getName() + " added.\n");	
						exerciseFound = true;
						break;
					} else if (exercise.getName().equalsIgnoreCase(exerciseName)) {
						System.out.println(
								"\nThe training program already includes this exercise: " + exercise.getName());
						System.out.print("Do you want to add a new exercise? (yes/no): ");
						String replaceExercise = scanner.nextLine().toLowerCase();

						if (replaceExercise.equalsIgnoreCase("yes")) {
							System.out.print("\nIntroduce the name of the new exercise: ");
							String newExerciseName = scanner.nextLine();
							Exercise newExercise = null;
							for (Exercise exercise2 : exercisesList) {
								if (exercise2.getName().equalsIgnoreCase(newExerciseName)) {
									newExercise = exercise2;
									break;
								}
							}
							if (newExercise != null && !userExercises.contains(newExercise)) {
								userExercises.add(newExercise);
								System.out.println("\nExercise " + newExercise.getName() + " added.");
								exerciseFound = true;
								break;
							} else {
								System.out.println(
										"Exercise " + newExerciseName + " not found or already exists in the program.");
							}
						} else {
							break;
						}
					}
				}

				if (!exerciseFound) {
					System.out.println("Exercise " + exerciseName + " not found.");
				}

			}
			
			System.out.print("Duration of the training in weeks: ");
			durationInWeeks = scanner.nextInt();
			scanner.nextLine();
			trainingsPrograms.put(userUsername, userExercises);

			System.out.println("\nTraining Program info:");
			System.out.println("Program assign to: " + userUsername);
			System.out.println("Number of exercises: " + userExercises.size());
			System.out.println("Duration in weeks: " + durationInWeeks);
			userExercises.forEach(System.out::println);
		} else {
			System.out.println("The number of exercises is bigger than the number of exercises available.");
		}
	}

	// MODIFY TRAINING PROGRAM
	public void modifyTrainingProgram() {
		System.out.println("\nModify a Training Program");
		System.out.print("Enter the username of the User whose training program you want to modify: ");
		String usernameInput = scanner.nextLine();

		for (Map.Entry<String, List<Exercise>> entry : trainingsPrograms.entrySet()) {
			String key = entry.getKey();
			List<Exercise> value = entry.getValue();

			if (key.equalsIgnoreCase(usernameInput)) {
				System.out.println("Training Program of: " + usernameInput);
				System.out.println(value);

				System.out.print("\nDo you want to add or delete exercises? (add/delete): ");
				String answer = scanner.nextLine().toLowerCase();

				switch (answer) {
				case "add":
					System.out.print("\nHow many exercises do you want to add?: ");
					int numberExercisesAdd = scanner.nextInt();
					scanner.nextLine();

					for (int i = 0; i < numberExercisesAdd; i++) {
						System.out.print("Enter the name of the exercise to add: ");
						String nameExercise = scanner.nextLine();
						boolean exerciseFound = false;

						for (Exercise exercise : exercisesList) {
							if (exercise.getName().equalsIgnoreCase(nameExercise) && !value.contains(exercise)) {
								value.add(exercise);
								System.out.println("Exercise " + exercise.getName() + " added successfully.");
								exerciseFound = true;
								break;
							} else if (exercise.getName().equalsIgnoreCase(nameExercise)) {
								System.out.println(
										"The training program already includes this exercise: " + exercise.getName());
								exerciseFound = true;
								break;
							}
						}

						if (!exerciseFound) {
							System.out.println("Exercise " + nameExercise + " not found.");
						}
					}
					break;

				case "delete":
					System.out.print("\nHow many exercises do you want to delete?: ");
					int numberExercisesDelete = scanner.nextInt();
					scanner.nextLine();

					for (int i = 0; i < numberExercisesDelete; i++) {
						System.out.print("Enter the name of the exercise to delete: ");
						String nameExercise = scanner.nextLine();
						boolean exerciseRemoved = false;

						for (Exercise exercise : value) {
							if (exercise.getName().equalsIgnoreCase(nameExercise)) {
								value.remove(exercise);
								System.out.println("Exercise " + nameExercise + " deleted successfully.");
								exerciseRemoved = true;
								break;
							}
						}

						if (!exerciseRemoved) {
							System.out.println("Exercise " + nameExercise + " not found.");
						}
					}
					break;

				default:
					System.out.println("Wrong answer");
					break;
				}

				System.out.println("\nUpdated Training Program info for " + key + ": ");
				System.out.println("Number of exercises: " + value.size());
				value.forEach(System.out::println);
				return;
			}
		}

		System.out.println("User " + usernameInput + " not found.");
	}

	// DELETE TRAINING PROGRAM
	public void deleteTrainingProgram() {
		System.out.println("\nDelete a Training Program");
		if (trainingsPrograms.isEmpty()) {
			System.out.println("There are no training programs available.");
			return;
		}

		System.out.print("Enter the username of the User you want to delete the training program: ");
		String usernameInput = scanner.nextLine().trim();

		if (trainingsPrograms.containsKey(usernameInput)) {
			System.out.println("\nTraining Program of: " + usernameInput);
			System.out.println(trainingsPrograms.get(usernameInput));

			System.out.print("\nDo you want to delete this training program? (yes/no): ");
			String answer = scanner.nextLine().toLowerCase();
			switch (answer) {
			case "yes":
				trainingsPrograms.remove(usernameInput);
				System.out.println("Training program for user " + usernameInput + " deleted successfully.");
				break;
			case "no":
				System.out.println("The training program for user " + usernameInput + " was not deleted.");
				break;
			default:
				System.out.println("Wrong answer.");
				break;
			}

			System.out.println("\nUpdated Training Program info:");
			System.out.println("Number of exercises: " + trainingsPrograms.size());
			trainingsPrograms.forEach((key, value) -> System.out.println(key + ": " + value));
		} else {
			System.out.println("User " + usernameInput + " not found.");
		}
	}

	// DISPLAY TRAINING PROGRAM 
	public void displayTrainingProgram() {	
		System.out.println("\nDisplay Training Program:");
		System.out.print("Insert user name: ");
		String username = scanner.nextLine();
		boolean userFound = false;		
		for (Map.Entry<String, List<Exercise>> entry : trainingsPrograms.entrySet()) {
			String key = entry.getKey();
			List<Exercise> value = entry.getValue();
			if(username.equalsIgnoreCase(key)) {
				System.out.println(value);
				userFound = true;
			}
		}
		
		if(!userFound) {
			System.out.println("User " + username + " not found.");
		}

	}
	
	// ASSIGN A SPECIFIC SCHEDULE TO A TRAINING PROGRAM
	public void assignTrainingSchedule(String username, int durationInWeeks, String...daysOfWeek) {
		if(usersList.isEmpty()) {
			System.out.println("There are no users to assign the schedule of the training program.");
			return;
		}
		
		List<String> daysOfWeekList = Arrays.asList(daysOfWeek);
		List<Exercise> exercisesForUser = new ArrayList<>(userExercises);
		List<Exercise> exercisesPerDay = new ArrayList<>(exercisesForUser);
		
		int exercisesPerDayCount = exercisesForUser.size() / (durationInWeeks * daysOfWeek.length);
		int exercisesIndex = 0;
		
		for(String day : daysOfWeek) {
			List<Exercise> dailyExercises = new ArrayList<>();
			for(int i = 0; i < exercisesPerDayCount && exercisesIndex < exercisesForUser.size(); i++) {
				dailyExercises.add(exercisesForUser.get(exercisesIndex));
				exercisesIndex++;
			} 
			trainingSchedule.put(username + "-" + day, dailyExercises);
			
			System.out.println("Training program assign to days of the week: " + day);
		}
	}
}
