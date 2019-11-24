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
import android.widget.ListView;

import com.example.easyfit_app.R;
import com.example.easyfit_app.database.DatabaseHelper;
import com.example.easyfit_app.dialogs.HelpDialogFragment;
import com.example.easyfit_app.list_adapters.UserExerciseAdapter;
import com.example.easyfit_app.model.Workout;

/**
 * Creates a window containing a saved workout. A custom adapter is used to show the exercises
 * in a ListView.
 */

public class SavedWorkoutActivity extends AppCompatActivity {

    private static final String TAG = "SavedWorkoutActivity";

    static DatabaseHelper helper;

    private ListView lvSavedWorkout;
    private UserExerciseAdapter adapter;
    private Workout savedWorkout;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workout);

        Toolbar tbSavedWorkout = (Toolbar) findViewById(R.id.tb_saved_workout);
        setSupportActionBar(tbSavedWorkout);
        tbSavedWorkout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        helper = new DatabaseHelper(this);

        savedWorkout = new Workout();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            position = bundle.getInt("Position");
            Log.d(TAG, "Position: " + position);
        }
        savedWorkout = helper.getWorkoutFromDatabase(position);

        lvSavedWorkout = (ListView) findViewById(R.id.lv_saved_workout);
        adapter = new UserExerciseAdapter(this, R.layout.item_user_exercise,
                savedWorkout.getWorkoutExercises());
        lvSavedWorkout.setAdapter(adapter);

        setTitle(savedWorkout.getWorkoutName());
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
                Intent intentOpenHome = new Intent(SavedWorkoutActivity.this, MainActivity.class);
                startActivity(intentOpenHome);
                return true;
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(SavedWorkoutActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}