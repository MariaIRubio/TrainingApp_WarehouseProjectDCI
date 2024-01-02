package trainingApp;

import java.time.LocalDate;
import java.util.List;

public class Application {

	public static void main(String[] args) throws InvalidPasswordException {
		
		User user = new User();
		Trainer trainer = new Trainer(user);
		UserMenu userMenu = new UserMenu();
		Payment payment = new Payment();
		Exercise exercise = new Exercise();
		List<User> usersList = user.getUsersList();
		List<Exercise> exercisesList = exercise.getExercisesList();
		TrainingProgram trainingProgram = new TrainingProgram(usersList, exercisesList);
		Progress progress = new Progress();
		
		LocalDate today = LocalDate.now();
		
		/*user.createNewUser();
		//user.createUserAccount();
		//trainer.displaySingleUserInformation();
		//trainer.displayUsersInformation();
		exercise.addExercise();
		//exercise.addExercise();
		//exercise.addExercise();

		trainingProgram.createTrainingProgram();
		//trainingProgram.modifyTrainingProgram();
		//trainingProgram.deleteTrainingProgram();
		
		trainingProgram.displayTrainingProgram();
		
		trainingProgram.assignTrainingSchedule("mariaib", 5, "Monday", "Thursday");
		//progress.markExerciseAsDone(exercise);
		progress.markTrainingDayAsDone("Monday");*/
	
	}
	

}
