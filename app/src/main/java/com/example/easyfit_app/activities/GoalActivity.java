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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easyfit_app.R;
import com.example.easyfit_app.dialogs.HelpDialogFragment;

/**
 * Creates a window where the user can select their fitness goal. The window contains
 * radio buttons with the options 'Strength', 'Size' and 'Conditioning' and a button
 * that navigates to the next activity. The selected radio button is assigned to a String variable
 * which is fetched by WorkoutActivity.
 */

public class GoalActivity extends AppCompatActivity {

    private static final String TAG = "GoalActivity";

    TextView tvSelectGoal;

    RadioGroup rgGoal;
    RadioButton rbGoal;

    private static String goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        Toolbar tbGoal = (Toolbar) findViewById(R.id.tb_goal);
        setSupportActionBar(tbGoal);
        setTitle("Choose Goal");
        tbGoal.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvSelectGoal = findViewById(R.id.tv_select_goal);
        rgGoal = findViewById(R.id.rg_goal);

        Button btnNextA3 = findViewById(R.id.btn_next_a3);
        btnNextA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goal == null) {
                    Toast.makeText(getApplicationContext(),
                            "Select a fitness goal to continue.", Toast.LENGTH_SHORT).show();
                } else {
                    openMuscleGroupActivity();
                }
            }
        });
    }

    public static String getGoal() {
        return goal;
    }

    public void checkGoalRadio(View v) {
        int radioId = rgGoal.getCheckedRadioButtonId();
        rbGoal = findViewById(radioId);

        goal = rbGoal.getText().toString();
        Log.i(TAG, "Goal selected: " + goal);
    }

    public void openMuscleGroupActivity() {
        Intent intentA3 = new Intent(this, MuscleGroupActivity.class);
        startActivity(intentA3);
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
                Intent intentOpenHome = new Intent(GoalActivity.this, MainActivity.class);
                startActivity(intentOpenHome);
                return true;
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(GoalActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
