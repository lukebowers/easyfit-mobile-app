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

import com.example.easyfit_app.R;
import com.example.easyfit_app.database.DatabaseHelper;
import com.example.easyfit_app.dialogs.HelpDialogFragment;
import com.example.easyfit_app.model.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Creates the home screen window. This window contains a welcome message, a button to start, a
 * button to view saved workouts and a button to view all exercises in the database.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    public DatabaseHelper helper;

    private Button btnStart;
    private Button btnViewSaved;
    private Button btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tbMain = (Toolbar) findViewById(R.id.tb_home);
        setSupportActionBar(tbMain);
        setTitle("EasyFit Home");

        helper = new DatabaseHelper(this);

        btnStart = (Button) findViewById(R.id.btn_start);
        btnViewSaved = (Button) findViewById(R.id.btn_view_saved);
        btnViewAll = (Button) findViewById(R.id.btn_view_all);

        btnStart.setOnClickListener(this);
        btnViewSaved.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);

        readExerciseData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Intent intentOpenLevel = new Intent(MainActivity.this,
                        LevelActivity.class);
                startActivity(intentOpenLevel);
                break;

            case R.id.btn_view_saved:
                Intent intentOpenSaved = new Intent(MainActivity.this,
                        BrowseWorkoutsActivity.class);
                startActivity(intentOpenSaved);
                break;

            case R.id.btn_view_all:
                Intent intentOpenBrowse = new Intent(MainActivity.this,
                        AllExercisesActivity.class);
                startActivity(intentOpenBrowse);
                break;
        }
    }

    private void readExerciseData() {

        InputStream input = getResources().openRawResource(R.raw.exercise_data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, Charset.forName("UTF-8"))
        );

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                // Split line by comma
                String[] tokens = line.split(",");

                //Read data
                Exercise exercise = new Exercise();
                exercise.setExerciseId(Integer.parseInt(tokens[0]));
                exercise.setExerciseName(tokens[1]);
                exercise.setMuscleGroup(tokens[2]);
                exercise.setUtility(tokens[3]);
                exercise.setEquipment(tokens[4]);
                exercise.setUrl(tokens[5]);

                helper.addExercise(exercise);

            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading data file.", e);
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
