package trainingApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Progress {

	private List<Exercise> completedExercises;
	private List<Exercise> remainingExercises;
	private Map<String, Boolean> completedDays;

	public Progress() {
		this.completedExercises = new ArrayList<>();
		this.remainingExercises = new ArrayList<>();
		this.completedDays = new HashMap<>();
	}

	public Progress(List<Exercise> userExercises, List<String> daysOfWeek) {
		this.completedExercises = new ArrayList<>();
		this.remainingExercises = new ArrayList<>(userExercises);
		this.completedDays = new HashMap<>();

		for (Exercise exercise : userExercises) {
			if (!userExercises.contains(exercise)) {
				completedExercises.add(exercise);
			}
		}

		for (String day : daysOfWeek) {
			completedDays.put(day, false);
		}
	}

	// VIEW TRAINING PROGRESS
	public void viewTrainingProgress() {
		System.out.println("\nView Progress: ");

		long completedCount = completedDays.values().stream().filter(Boolean::booleanValue).count();
		long remainingCount = completedDays.size() - completedCount;

		// COMPLETED DAYS
		System.out.println("Completed Days: ");
		completedDays.forEach((day, isCompleted) -> {
			if (isCompleted) {
				System.out.println("- " + day);
			}
		});

		// COMPLETED EXERCISES
		System.out.println("\nCompleted Exercises: ");
		completedExercises.forEach(exercise -> System.out.println("- " + exercise.getName()));

		// REMAINING DAYS
		System.out.println("\nRemaining Days: ");
		completedDays.forEach((day, isCompleted) -> {
			if (!isCompleted) {
				System.out.println("- " + day);
			}
		});

		// REMAINING EXERCISES
		System.out.println("\nRemaining Exercises: ");
		remainingExercises.forEach(exercise -> System.out.println("- " + exercise.getName()));

	}

	// MARK EXERCISE AS DONE
	public void markExerciseAsDone(Exercise exercise) {
		if (!remainingExercises.isEmpty()) {
			if (remainingExercises.contains(exercise)) {
				remainingExercises.remove(exercise);
				completedExercises.add(exercise);
				System.out.println("The exercise '" + exercise.getName() + "' is marked as done.");
			} else {
				System.out.println(
						"The exercise '" + exercise.getName() + "' is not in the list of remaining exercises.");
			}
		} else {
			System.out.println("All exercises are completed.");
		}
	}

	// MARK TRAINING DAY AS DONE
	public void markTrainingDayAsDone(String day) {
		if (!completedDays.containsKey(day)) {
			completedDays.put(day, true);
			System.out.println("The training day '" + day + "' is marked as completed.");
		} else {
			System.out.println("The training day '" + day + "' is not in the training program days.");
		}
	}
}
