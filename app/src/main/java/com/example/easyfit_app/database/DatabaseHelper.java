package com.example.easyfit_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.easyfit_app.model.Exercise;
import com.example.easyfit_app.model.UserExercise;
import com.example.easyfit_app.model.Workout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper class that manages the SQLite database. It contains String variables to define the
 * database schema, methods for table management and methods for interacting with the database,
 * including CRUD.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "exercises.db";
    private static final int DATABASE_VERSION = 1;

    // Exercise table definition

    public static final String TABLE_EXERCISES = "exercises";
    public static final String COL_EXERCISE_ID = "exercise_id";
    public static final String COL_EXERCISE_NAME = "exercise_name";
    public static final String COL_EXERCISE_GROUP = "muscle_group";
    public static final String COL_EXERCISE_UTILITY = "mechanics";
    public static final String COL_EXERCISE_EQUIPMENT = "equipment";
    public static final String COL_EXERCISE_URL = "url";

    // Workout table definition

    public static final String TABLE_WORKOUTS = "workouts";
    public static final String COL_WORKOUT_ID = "workout_id";
    public static final String COL_WORKOUT_NAME = "workout_name";
    public static final String COL_WORKOUT_EXERCISES = "workout_exercises";



    // SQL statement for creating Exercise table

    private static final String SQL_CREATE_TABLE_EXERCISES = "CREATE TABLE "
            + TABLE_EXERCISES + "("
            + COL_EXERCISE_ID + " INTEGER PRIMARY KEY, "
            + COL_EXERCISE_NAME + " TEXT NOT NULL, "
            + COL_EXERCISE_GROUP + " TEXT NOT NULL, "
            + COL_EXERCISE_UTILITY + " TEXT NOT NULL, "
            + COL_EXERCISE_EQUIPMENT + " TEXT NOT NULL, "
            + COL_EXERCISE_URL + " TEXT);";

    // SQL statement for creating Workout table

    private static final String SQL_CREATE_TABLE_WORKOUTS = "CREATE TABLE "
            + TABLE_WORKOUTS + "("
            + COL_WORKOUT_ID + " INTEGER PRIMARY KEY, "
            + COL_WORKOUT_NAME + " TEXT, "
            + COL_WORKOUT_EXERCISES + " BLOB);";

    // SQL statements for table deletion

    private static final String SQL_DELETE_TABLE_EXERCISES = "DROP TABLE IF EXISTS "
            + TABLE_EXERCISES;

    private static final String SQL_DELETE_TABLE_WORKOUTS = "DROP TABLE IF EXISTS "
            + TABLE_WORKOUTS;

    // Constructor

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_EXERCISES);
        db.execSQL(SQL_CREATE_TABLE_WORKOUTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_EXERCISES);
        db.execSQL(SQL_DELETE_TABLE_WORKOUTS);
        onCreate(db);
    }

    // Exercise table methods

    public void addExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EXERCISE_ID, exercise.getId());
        values.put(COL_EXERCISE_NAME, exercise.getExerciseName());
        values.put(COL_EXERCISE_GROUP, exercise.getMuscleGroup());
        values.put(COL_EXERCISE_UTILITY, exercise.getUtility());
        values.put(COL_EXERCISE_EQUIPMENT, exercise.getEquipment());
        values.put(COL_EXERCISE_URL, exercise.getUrl());
        db.insertWithOnConflict(TABLE_EXERCISES, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        Log.i(TAG, "addExercise(): " + exercise.getExerciseName() + " added to database.");
    }

    public Exercise getExercise(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EXERCISES, new String[] {
                COL_EXERCISE_ID, COL_EXERCISE_NAME, COL_EXERCISE_GROUP, COL_EXERCISE_UTILITY,
                COL_EXERCISE_EQUIPMENT, COL_EXERCISE_URL}, COL_EXERCISE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Exercise exercise = new Exercise(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5));

        return exercise;
    }

   public Cursor getAllExercises() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_EXERCISES;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public List<Exercise> getExerciseList() {

        String query = "SELECT * FROM " + TABLE_EXERCISES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<Exercise> exerciseList = new ArrayList<>();

        while(cursor.moveToNext()) {
            Exercise exercise = new Exercise();
            exercise.setExerciseId(Integer.parseInt(cursor.getString(0)));
            exercise.setExerciseName(cursor.getString(1));
            exercise.setMuscleGroup(cursor.getString(2));
            exercise.setUtility(cursor.getString(3));
            exercise.setEquipment(cursor.getString(4));
            exercise.setUrl(cursor.getString(5));

            exerciseList.add(exercise);
        }
        cursor.close();
        return exerciseList;

    }

    public List<UserExercise> getUserExerciseList() {

        String query = "SELECT * FROM " + TABLE_EXERCISES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<UserExercise> userExercises = new ArrayList<>();

        while(cursor.moveToNext()) {
            UserExercise exercise = new UserExercise();
            exercise.setExerciseId(Integer.parseInt(cursor.getString(0)));
            exercise.setExerciseName(cursor.getString(1));
            exercise.setMuscleGroup(cursor.getString(2));
            exercise.setUtility(cursor.getString(3));
            exercise.setEquipment(cursor.getString(4));
            exercise.setUrl(cursor.getString(5));

            userExercises.add(exercise);
        }
        cursor.close();
        return userExercises;
    }

    public void updateExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EXERCISE_ID, exercise.getId());
        values.put(COL_EXERCISE_NAME, exercise.getExerciseName());
        values.put(COL_EXERCISE_GROUP, exercise.getMuscleGroup());
        values.put(COL_EXERCISE_UTILITY, exercise.getUtility());
        values.put(COL_EXERCISE_EQUIPMENT, exercise.getEquipment());
        values.put(COL_EXERCISE_URL, exercise.getUrl());
        db.update(TABLE_EXERCISES, values, COL_EXERCISE_ID
                + " = " + exercise.getId(),
                new String[]{String.valueOf(exercise.getId())});
    }

    public void removeExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXERCISES, COL_EXERCISE_ID
                + " = " + exercise.getId(), null);
    }

    // Workout table methods

    public void addWorkout(Workout workout) {

        List<UserExercise> exercises = new ArrayList<>();
        exercises = workout.getWorkoutExercises();
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();

        ContentValues values = new ContentValues();
        values.put(COL_WORKOUT_NAME, workout.getWorkoutName());
        values.put(COL_WORKOUT_EXERCISES, gson.toJson(exercises).getBytes());
        db.insert(TABLE_WORKOUTS, null, values);

    }

    public Workout getWorkoutFromDatabase(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        Workout workout = null;
        String query = "SELECT * FROM " + TABLE_WORKOUTS + " WHERE "
                + COL_WORKOUT_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int workoutId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_WORKOUT_ID)));
            String workoutName = cursor.getString(cursor.getColumnIndex(COL_WORKOUT_NAME));
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(COL_WORKOUT_EXERCISES));
            String json = new String(blob);
            Gson gson = new Gson();
            List<UserExercise> exercises = gson.fromJson(json, new TypeToken<List<UserExercise>>()
            {}.getType());

            workout = new Workout();
            workout.setWorkoutId(workoutId);
            workout.setWorkoutName(workoutName);
            workout.setWorkoutExercises(exercises);
        }
        cursor.close();
        return workout;

    }

    public List<Workout> getWorkoutList() {

        String query = "SELECT * FROM " + TABLE_WORKOUTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<Workout> workoutList = new ArrayList<>();

        while (cursor.moveToNext()) {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(COL_WORKOUT_EXERCISES));
            String json = new String(blob);
            Gson gson = new Gson();
            List<UserExercise> exercises = gson.fromJson(json, new TypeToken<List<UserExercise>>()
            {}.getType());
            Workout workout = new Workout();
            workout.setWorkoutId(Integer.parseInt(cursor.getString(0)));
            workout.setWorkoutName(cursor.getString(1));
            workout.setWorkoutExercises(exercises);

            workoutList.add(workout);
        }
        cursor.close();
        return workoutList;

    }

    public void updateWorkout(Workout workout) {
        List<UserExercise> exercises = new ArrayList<>();
        exercises = workout.getWorkoutExercises();
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();
        ContentValues values = new ContentValues();
        values.put(COL_WORKOUT_NAME, workout.getWorkoutName());
        values.put(COL_WORKOUT_EXERCISES, gson.toJson(exercises).getBytes());
        db.update(TABLE_WORKOUTS, values, COL_WORKOUT_ID
                + " = " + workout.getWorkoutId(),
                new String[]{String.valueOf(workout.getWorkoutId())});
    }

    public void removeWorkout(Workout workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORKOUTS, COL_WORKOUT_ID
                + " = " + workout.getWorkoutId(), null);
    }

}
