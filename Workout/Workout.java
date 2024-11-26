package Exams.second.Workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Workout {
    public String type;
    public int exerciseCount;
    public List<Exercise> exercises;

    public Workout(String type, int exerciseCount) {
        this.type = type;
        this.exerciseCount = exerciseCount;
        this.exercises = new ArrayList<>();
    }

    //•	Field exercises – List that holds added exercises.
    //•	Method addExercise(Exercise exercise) – adds an entity to the exercises If there is still space on the workout sheet (exerciseCount).
    //•	Method removeExercise(String name, String muscle) – removes the exercise by given name and muscle, if such exists, and returns boolean.
    //•	Method getExercise(String name, String muscle) –
    // returns the exercise with the given name and muscle or null if there is no such exercise.
    //•	Method getMostBurnedCaloriesExercise() – returns the exercise which is burned the most calories or null if there are no exercises.
    //•	Getter getExerciseCount() – returns the number of exercises.
    //•	getStatistics() – returns a String in the following format:
    //o	"Workout type: {workout type}
    //Exercise: {Exercise1}
    //Exercise: {Exercise2}
    //(…)"

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void remove(String name, String muscle) {
        this.exercises.stream().filter(exercise -> exercise.getName().equals(name) && exercise.getMuscle().equals(muscle)).findFirst().ifPresent(toBeRemoved -> this.exercises.remove(toBeRemoved));
    }
    public Exercise getExercise(String name, String muscle) {
        return this.exercises.stream().filter(exercise -> exercise.getName().equals(name) && exercise.getMuscle().equals(muscle)).findFirst().orElse(null);
    }
    public Exercise getMostBurnedCaloriesExercise() {
        //виж дали това работи, иначе има и много по-добър начин
        return this.exercises.stream().sorted((a, b) -> {
            return Integer.compare(b.getBurnedCalories(), a.getBurnedCalories());
        }).findFirst().orElse(null);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExerciseCount() {
        return exerciseCount;
    }

    public void setExerciseCount(int exerciseCount) {
        this.exerciseCount = exerciseCount;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    //has exercises (List, which stores the entity Exercise).
    // All entities inside the repository have the same fields. Also, the Workout class should have those fields:
    //•	type: String
    //•	exerciseCount: int
}
