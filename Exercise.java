package trainingApp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Exercise {
	enum Cathegory {
		HIP, SHOULDER, ANKLE, NECK, KNIE, CORE, WRIST, BACK
	};

	private String name;
	private String description;
	private String repetitions;
	private String series;
	private String category;
	private String videoURL;
	Scanner scanner = new Scanner(System.in);
	List<Exercise> exercisesList = new ArrayList<>();

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getRepetitions() {
		return repetitions;
	}

	public String getSeries() {
		return series;
	}

	public String getCategory() {
		return category;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public List<Exercise> getExercisesList() {
		return exercisesList;
	}

	public Exercise() {
	}

	public Exercise(String name, String description, String repetitions, String series, String category, String videoURL) {
		this.name = name;
		this.description = description;
		this.repetitions = repetitions;
		this.series = series;
		this.category = category;
		this.videoURL = videoURL;
	}

	@Override
	public String toString() {
		return """

				Exercise info:
				Name: %s
				Description: %s
				Repetitions: %s
				Series: %s
				Category: %s
				Video URL: %s
				""".formatted(name, description, repetitions, series, category, videoURL);
	}
	
	// ADD AN EXERCISE TO THE LIST
	public void addExercise() {
		boolean exerciseAdded = false;

		System.out.println("\nAdd exercise: ");
		while (!exerciseAdded) {
			try {
				boolean nameExists = false;
				System.out.print("Name: ");
				String name = scanner.nextLine();

				for (Exercise exercise : exercisesList) {
					if (exercise.getName().equalsIgnoreCase(name)) {
						nameExists = true;
						break;
					}
				}
				if (nameExists) {
					System.out
							.println("An exercise with this name already exists. Please choose another name.");

				} else {
					System.out.print("Description: ");
					String description = scanner.nextLine();
					System.out.print("Repetitions: ");
					String repetitions = scanner.nextLine();
					System.out.print("Series: ");
					String series = scanner.nextLine();
					System.out.print("Category: ");
					String category = scanner.nextLine();

					String videoURL;
					boolean videoURLExists;
					do {
						videoURLExists = false;
						System.out.print("Video URL: ");
						videoURL = scanner.nextLine();

						for (Exercise exercise : exercisesList) {
							if (exercise.getVideoURL().equalsIgnoreCase(videoURL)) {
								videoURLExists = true;
								break;
							}
						}
						if (videoURLExists) {
							System.out.println(
									"An exercise with this video URL already exists. Please choose another URL.");

						}
					} while (videoURLExists);

					Exercise exercise = new Exercise(name, description, repetitions, series, category, videoURL);
					exercisesList.add(exercise);
					System.out.println("\nExercise added succesfully.\n");
					exerciseAdded = true;

				}
			} catch (InputMismatchException e) {
				System.out.println("There was an error while typing. Try again.\n ");
				scanner.nextLine();
			}
		}

		
		  System.out.println("\nList of Exercises updated:");
		  exercisesList.forEach(System.out::println);
		 
	}
	
	// DELETE AN EXERCISE FROM THE LIST
	public void deleteExercise() {

		boolean exerciseFound = false;
		Exercise exerciseToRemove = null;

		System.out.println("\nDelete exercise: ");
		System.out.print("Introduce the name of the exercise you want to delete: ");
		String exerciseDelete = scanner.nextLine();

		for (Exercise exercise : exercisesList) {
			if (exercise.getName().equalsIgnoreCase(exerciseDelete)) {
				System.out.println(exercise.toString());
				System.out.print("Do you want to delete this exercise? yes/no: ");
				String answer = scanner.nextLine();

				if (answer.equals("yes")) {
					exerciseToRemove = exercise;
					exerciseFound = true;
					break;
				} else if (answer.equals("no")) {
					System.out.println("Exercise wont be deleted.");
					exerciseFound = true;
					break;
				} else {
					System.out.println("Wrong answer. You must answer yes or no.");
					break;
				}
			}
		}

		if (exerciseFound) {
			if (exerciseToRemove != null) {
				exercisesList.remove(exerciseToRemove);
				System.out.println("Exercise removed sucesfully.");
			}
		} else {
			System.out.println("Exercise not found on the list.");
		}
	}

	// DISPLAY  LIST OF EXERCISES
	public void displayExercises() {
		System.out.println("\nDisplay List of Exercises: ");
		if(!exercisesList.isEmpty()) {
		exercisesList.forEach(System.out::println);
		}else {
			System.out.println("There are no exercises added to the list.");
		}

		// In the future I want to add a feature that display the exercises filtered b
		// category
	}

}
