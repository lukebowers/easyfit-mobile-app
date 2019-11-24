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

import com.example.easyfit_app.database.DatabaseHelper;
import com.example.easyfit_app.R;
import com.example.easyfit_app.dialogs.HelpDialogFragment;
import com.example.easyfit_app.list_adapters.ExerciseAdapter;
import com.example.easyfit_app.model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a window containing a list of all exercises in the database.
 */

public class AllExercisesActivity extends AppCompatActivity {

    private static final String TAG = "AllExercisesActivity";

    DatabaseHelper helper;

    private ListView lvExercises;
    private ExerciseAdapter adapter;
    private List<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exercises);

        Toolbar tbAllExercises = (Toolbar) findViewById(R.id.tb_all_exercises);
        setSupportActionBar(tbAllExercises);
        setTitle("Exercise Database");
        tbAllExercises.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        helper = new DatabaseHelper(this);
        exerciseList = new ArrayList<>(helper.getExerciseList());

        lvExercises = (ListView) findViewById(R.id.lv_exercise_list);

        adapter = new ExerciseAdapter(this, R.layout.item_exercise, exerciseList);
        lvExercises.setAdapter(adapter);

        Log.i(TAG, "Items in list: " + String.valueOf(adapter.getCount()));
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
                Intent intentOpenHome = new Intent(AllExercisesActivity.this, MainActivity.class);
                startActivity(intentOpenHome);
                return true;
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(AllExercisesActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
