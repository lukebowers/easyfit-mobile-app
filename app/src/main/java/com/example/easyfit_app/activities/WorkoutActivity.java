package com.example.easyfit_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.easyfit_app.R;
import com.example.easyfit_app.database.DatabaseHelper;
import com.example.easyfit_app.dialogs.EmptyDialogFragment;
import com.example.easyfit_app.dialogs.HelpDialogFragment;
import com.example.easyfit_app.dialogs.SaveDialogFragment;
import com.example.easyfit_app.list_adapters.UserExerciseAdapter;
import com.example.easyfit_app.model.UserExercise;
import com.example.easyfit_app.model.Workout;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a window that displays the generated workout. It contains a list of exercises, a shuffle
 * button which returns new exercises and a button to save the workout.
 */

public class WorkoutActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "WorkoutActivity";

    static DatabaseHelper helper;

    private ListView lvWorkout;
    private UserExerciseAdapter adapter;
    private Button btnSaveWorkout, btnShuffleWorkout;

    private List<UserExercise> allExercises;
    private static Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Toolbar tbLevel = (Toolbar) findViewById(R.id.tb_workout);
        setSupportActionBar(tbLevel);
        setTitle("Your Workout");
        tbLevel.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        helper = new DatabaseHelper(this);

        allExercises = new ArrayList<>(helper.getUserExerciseList());

        workout = new Workout();
        setWorkoutArguments(workout);
        workout.setWorkoutExercises(workout.makeFinalExercises(allExercises));

        lvWorkout = (ListView) findViewById(R.id.lv_workout_list);
        adapter = new UserExerciseAdapter(this, R.layout.item_user_exercise, workout.getWorkoutExercises());
        lvWorkout.setAdapter(adapter);

        btnSaveWorkout = findViewById(R.id.btn_save_workout);
        btnShuffleWorkout = findViewById(R.id.btn_shuffle_workout);

        btnSaveWorkout.setOnClickListener(this);
        btnShuffleWorkout.setOnClickListener(this);

        isWorkoutEmpty();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_workout:
                DialogFragment saveDialog = new SaveDialogFragment();
                saveDialog.show(getSupportFragmentManager(), "save_workout");
                break;
            case R.id.btn_shuffle_workout:
                reloadActivity();
                break;
        }
    }

    public void isWorkoutEmpty() {
        if (workout.getWorkoutExercises().isEmpty()) {
            DialogFragment emptyDialog = new EmptyDialogFragment();
            emptyDialog.show(getSupportFragmentManager(), "workout_empty");
        }
    }

    public void reloadActivity() {
        finish();
        // Skip screen blink
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    public void setWorkoutArguments(Workout workout) {
        workout.setLevel(LevelActivity.getLevel());
        workout.setGoal(GoalActivity.getGoal());
        workout.setWorkoutMuscleGroups(MuscleGroupActivity.getCheckedGroups());
        workout.setWorkoutEquipment(EquipmentActivity.getCheckedEquipment());
        workout.setWorkoutLengthByLevel();
    }

    public static Workout getCurrentWorkout() {
        return workout;
    }

    public static void saveWorkout(Workout workout) {
        helper.addWorkout(workout);
    }

    // Options menu methods

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tb_item_home:
                Intent intentOpenHome = new Intent(WorkoutActivity.this, MainActivity.class);
                startActivity(intentOpenHome);
                return true;
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(WorkoutActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
