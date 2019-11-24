package com.example.easyfit_app.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Creates Exercise objects which are individual elements of a workout. Each exercise has an id so
 * that it can be referenced in the database, a name, so that it is recognisable to the user and
 * muscleGroup, utility and equipment fields so that it can be selected for workouts.
 */

public class Exercise implements Comparator<Exercise> {

    private int id;
    private String exerciseName;
    private String muscleGroup;
    private String utility;
    private String equipment;
    private String url;

    public Exercise() {
        this.id = 0;
        this.exerciseName = "";
        this.muscleGroup = "";
        this.utility = "";
        this.equipment = "";
        this.url = "";
    }

    public Exercise(int id, String exerciseName, String muscleGroup, String utility,
                    String equipment, String url) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.muscleGroup = muscleGroup;
        this.utility = utility;
        this.equipment = equipment;
        this.url = url;
    }

    public void setExerciseId(int id) {
        this.id = id;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public void setUtility(String utility) {
        this.utility = utility;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getUtility() {
        return utility;
    }

    public String getEquipment() {
        return equipment;
    }
    
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", exerciseName='" + exerciseName + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", utility=" + utility +
                ", equipment=" + equipment +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int compare(Exercise ex1, Exercise ex2) {
        // Custom order list
        List<String> order = Arrays.asList("Legs", "Chest", "Shoulders", "Back",
                "Traps", "Triceps", "Biceps", "Abs");
        // Get muscle group in order of list
        int compareGroup = Integer.valueOf(
                order.indexOf(ex1.getMuscleGroup()))
                .compareTo(order.indexOf(ex2.getMuscleGroup()));
        int compareUtility = ex1.getUtility().compareTo(ex2.getUtility());

        // If muscle groups are not equal, compare them; else compare by utility
        if (compareGroup != 0) {
            return compareGroup;
        } else {
            return -compareUtility;
        }
    }

}
