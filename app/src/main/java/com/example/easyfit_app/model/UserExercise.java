package com.example.easyfit_app.model;

import java.io.Serializable;

/**
 * Creates UserExercise objects which are similar to Exercise objects but with the addition of sets
 * and reps. Exercises only have a number of sets and reps when performed, so UserExercise is used
 * when exercises are displayed as part of a workout.
 */

public class UserExercise extends Exercise implements Serializable {

    private int sets;
    private int reps;

    public UserExercise() {
        sets = 0;
        reps = 0;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

}
