package com.example.easyfit_app.model;

import android.util.Log;

import com.example.easyfit_app.activities.EquipmentActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import static com.example.easyfit_app.activities.MuscleGroupActivity.getCheckedGroups;

/**
 *Creates Workout objects. A Workout has an id for reference, a name which can be changed by the
 * user and level, goal, workoutMuscleGroups and workoutEquipment fields which hold the values taken
 * from the input activities. It also contains the exercises field which is the list of UserExercise
 * objects that make up the workout.
 */

public class Workout {

    public static final String TAG = "WorkoutClass";

    private int workoutId;
    private String workoutName;
    private String level;
    private String goal;
    private List<String> workoutMuscleGroups;
    private List<String> workoutEquipment;
    private int basicExerciseNo;
    private int auxiliaryExerciseNo;

    private List<UserExercise> exercises;

    public Workout() {
        this.workoutId = 0;
        this.workoutName = "";
        this.level = "";
        this.goal = "";
        this.workoutMuscleGroups = new ArrayList<>();
        this.workoutEquipment = new ArrayList<>();
        this.exercises = new ArrayList<>();
        this.basicExerciseNo = 0;
        this.auxiliaryExerciseNo = 0;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public String getLevel() {
        return level;
    }

    public String getGoal() {
        return goal;
    }

    public List<String> getWorkoutMuscleGroups() {
        return workoutMuscleGroups;
    }

    public List<String> getWorkoutEquipment() {
        return workoutEquipment;
    }

    public int getBasicExerciseNo() {
        return basicExerciseNo;
    }

    public int getAuxiliaryExerciseNo() {
        return auxiliaryExerciseNo;
    }

    public List<UserExercise> getWorkoutExercises() {
        return this.exercises;
    }

    public void setWorkoutId(int id) {
        this.workoutId = id;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setWorkoutMuscleGroups(List<String> workoutMuscleGroups) {
        this.workoutMuscleGroups = workoutMuscleGroups;
    }

    public void setWorkoutEquipment(List<String> workoutEquipment) {
        this.workoutEquipment = workoutEquipment;
    }

    public void setBasicExerciseNo(int basicExerciseNo) {
        this.basicExerciseNo = basicExerciseNo;
    }

    public void setAuxiliaryExerciseNo(int auxiliaryExerciseNo) {
        this.auxiliaryExerciseNo = auxiliaryExerciseNo;
    }

    public void setWorkoutExercises(List<UserExercise> exercises) {
        this.exercises = exercises;
    }

    public void setWorkoutLengthByLevel() {
        switch (this.level) {

            case "Beginner":
                this.basicExerciseNo = 2;
                this.auxiliaryExerciseNo = 2;
                break;

            case "Intermediate":
                this.basicExerciseNo = 4;
                this.auxiliaryExerciseNo = 3;
                break;

            case "Advanced":
                this.basicExerciseNo = 6;
                this.auxiliaryExerciseNo = 4;
                break;

        }
    }

    public void setSetsAndRepsByGoal(List<UserExercise> exercises) {
        switch (this.goal) {
            case "Strength":
                for (UserExercise exercise : exercises) {
                    if (exercise.getUtility().equals("Basic")) {
                        exercise.setSets(5);
                        exercise.setReps(1);
                    } else {
                        exercise.setSets(3);
                        exercise.setReps(6);
                    }
                }
                break;
            case "Size":
                for (UserExercise exercise : exercises) {
                    if (exercise.getUtility().equals("Basic")) {
                        exercise.setSets(4);
                        exercise.setReps(6);
                    } else {
                        exercise.setSets(3);
                        exercise.setReps(12);
                    }
                }
                break;
            case "Conditioning":
                for (UserExercise exercise : exercises) {
                    if (exercise.getUtility().equals("Basic")) {
                        exercise.setSets(3);
                        exercise.setReps(15);
                    } else {
                        exercise.setSets(2);
                        exercise.setReps(20);
                    }
                }
                break;
        }
    }

    // Filter by equipment

    public List<UserExercise> filterEquipment(List<UserExercise> exercises) {
        List<UserExercise> removeList = new ArrayList<>();
        ListIterator<UserExercise> iterator = exercises.listIterator();
        while (iterator.hasNext()) {
            UserExercise exercise = iterator.next();
            if (EquipmentActivity.getCheckedEquipment().contains(exercise.getEquipment())) {
                continue;
            }
            else {
                removeList.add(exercise);
            }
        }
        exercises.removeAll(removeList);
        return exercises;
    }

    public List<UserExercise> getExercisesByUtility(List<UserExercise> exercises, String utility) {
        List<UserExercise> newExercises = new ArrayList<>();
        for (UserExercise exercise : exercises) {
            if (exercise.getUtility().equals(utility)) {
                newExercises.add(exercise);
            }
        }
        return newExercises;
    }

    public void adjustWorkoutSize() {
        List<String> muscleGroups = getCheckedGroups();
        int workoutSize = basicExerciseNo + auxiliaryExerciseNo;

        while (muscleGroups.size() > workoutSize) {
            basicExerciseNo++;
            workoutSize++;
        }
    }

    public UserExercise getSpecificExerciseWithGroup(List<UserExercise> exercises, String muscleGroup) {
        for (UserExercise exercise : exercises) {
            if (exercise.getMuscleGroup().equals(muscleGroup)) {
                return exercise;
            }
        }
        return null;
    }

    public List<String> emptyGroups(List<String> muscleGroups, List<UserExercise> exercises) {
        List<String> emptyGroups = new ArrayList<>();
        for (String group : muscleGroups) {
            Exercise exercise = getSpecificExerciseWithGroup(exercises, group);
            if (exercise == null) {
                emptyGroups.add(group);
            }
        } return emptyGroups;
    }

    public List<UserExercise> makeListByMuscleGroup(List<UserExercise> exercises, int expectedListSize) {
        List<UserExercise> finalExercises = new ArrayList<>();
        List<String> muscleGroups = getCheckedGroups();
        UserExercise exercise;

        muscleGroups.removeAll(emptyGroups(muscleGroups, exercises));

        while (finalExercises.size() < expectedListSize
                && !exercises.isEmpty() && !muscleGroups.isEmpty()) {

            for (String group : muscleGroups) {

                exercise = getSpecificExerciseWithGroup(exercises, group);
                if (exercise != null) {
                    exercises.remove(exercise);
                    finalExercises.add(exercise);
                }
            }
        }
        return finalExercises;
    }

    public List<UserExercise> pairExerciseLists(List<UserExercise> listOne, List<UserExercise> listTwo) {
        List<UserExercise> pairedList = new ArrayList<>(listOne);
        pairedList.addAll(listTwo);

        return pairedList;
    }

    // Calls methods to generate a workout

    public List<UserExercise> makeFinalExercises(List<UserExercise> exercises) {

        filterEquipment(exercises);

        List<UserExercise> basicExercises = new ArrayList<>(getExercisesByUtility(exercises, "Basic"));
        List<UserExercise> auxiliaryExercises = new ArrayList<>(getExercisesByUtility(exercises, "Auxiliary"));

        Collections.shuffle(basicExercises);
        Collections.shuffle(auxiliaryExercises);

        adjustWorkoutSize();

        List<UserExercise> filteredBasic = new ArrayList<>(makeListByMuscleGroup(basicExercises, basicExerciseNo));
        List<UserExercise> filteredAuxiliary = new ArrayList<>(makeListByMuscleGroup(auxiliaryExercises, auxiliaryExerciseNo));

        List<UserExercise> pairedList = new ArrayList<>(pairExerciseLists(filteredBasic, filteredAuxiliary));

        setSetsAndRepsByGoal(pairedList);

        Collections.sort(pairedList, new UserExercise());

        return pairedList;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutId=" + workoutId +
                ", workoutName='" + workoutName + '\'' +
                ", level='" + level + '\'' +
                ", goal='" + goal + '\'' +
                '}';
    }
}