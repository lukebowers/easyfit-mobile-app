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
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.easyfit_app.R;
import com.example.easyfit_app.database.DatabaseHelper;
import com.example.easyfit_app.dialogs.HelpDialogFragment;
import com.example.easyfit_app.list_adapters.SavedWorkoutAdapter;
import com.example.easyfit_app.model.Workout;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a window containing a list of all workouts saved by the user. Tapping on a workout in the
 * list navigates to SavedWorkoutActivity which shows the exercises in the workout.
 */

public class BrowseWorkoutsActivity extends AppCompatActivity {

    public static final String TAG = "BrowseWorkoutsActivity";

    DatabaseHelper helper;

    private ListView lvSavedWorkouts;
    private SavedWorkoutAdapter adapter;
    private List<Workout> workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_workouts);

        Toolbar tbBrowseWorkouts = (Toolbar) findViewById(R.id.tb_browse_workouts);
        setSupportActionBar(tbBrowseWorkouts);
        setTitle("Saved Workouts");
        tbBrowseWorkouts.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        helper = new DatabaseHelper(this);
        workoutList = new ArrayList<>(helper.getWorkoutList());

        lvSavedWorkouts = (ListView) findViewById(R.id.lv_saved_workouts);

        adapter = new SavedWorkoutAdapter(this, R.layout.item_saved_workout, workoutList);
        lvSavedWorkouts.setAdapter(adapter);

        lvSavedWorkouts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BrowseWorkoutsActivity.this, SavedWorkoutActivity.class);
                Workout workout = (Workout) lvSavedWorkouts.getItemAtPosition(position);
                intent.putExtra("Position", workout.getWorkoutId());
                startActivity(intent);
            }
        });

        lvSavedWorkouts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
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
                Intent intentOpenHome = new Intent(BrowseWorkoutsActivity.this, MainActivity.class);
                startActivity(intentOpenHome);
                return true;
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(BrowseWorkoutsActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
