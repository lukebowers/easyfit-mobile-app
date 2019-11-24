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
 * Creates a window where the user can select their fitness level. The window contains radio buttons
 * with the options 'Beginner', 'Intermediate' and 'Advanced' and a button that navigates to the
 * next activity. The selected radio button is assigned to a String variable which is fetched by
 * WorkoutActivity.
 */

public class LevelActivity extends AppCompatActivity {

    private static final String TAG = "LevelActivity";

    TextView tvSelectFitnessLevel;

    RadioGroup rgLevel;
    RadioButton rbLevel;

    private static String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Toolbar tbLevel = (Toolbar) findViewById(R.id.tb_level);
        setSupportActionBar(tbLevel);
        setTitle("Choose Level");
        tbLevel.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvSelectFitnessLevel = findViewById(R.id.tv_select_fit_level);
        rgLevel = findViewById(R.id.rg_level);

        Button btnNextA2 = findViewById(R.id.btn_next_a2);
        btnNextA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level == null) {
                    Toast.makeText(getApplicationContext(),
                            "Select a fitness level to continue.", Toast.LENGTH_SHORT).show();
                } else {
                    openGoalActivity();
                }
            }
        });

    }

    public static String getLevel() {
        return level;
    }

    public void checkLevelRadio(View v) {
        int radioId = rgLevel.getCheckedRadioButtonId();
        rbLevel = findViewById(radioId);

        level = rbLevel.getText().toString();
        Log.i(TAG, "Level selected: " + level);
    }

    public void openGoalActivity() {
        Intent intentA2 = new Intent(this, GoalActivity.class);
        startActivity(intentA2);
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
                Intent intentOpenHome = new Intent(LevelActivity.this, MainActivity.class);
                startActivity(intentOpenHome);
                return true;
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(LevelActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
