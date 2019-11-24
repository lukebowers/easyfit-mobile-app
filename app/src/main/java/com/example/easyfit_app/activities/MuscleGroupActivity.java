package com.example.easyfit_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.easyfit_app.R;
import com.example.easyfit_app.dialogs.HelpDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a window where the user can select the muscle groups used in the workout. The window
 * contains a list of muscle groups in a checkbox format and a button that navigates to the next
 * activity. A switch statement adds the checked values to a list which is fetched by
 * WorkoutActivity.
 */

public class MuscleGroupActivity extends AppCompatActivity {

    CheckBox cbChest, cbShoulders, cbBack, cbTraps, cbLegs, cbAbs, cbTriceps, cbBiceps;

    private static List<String> checkedGroups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_group);

        Toolbar tbMuscleGroup = (Toolbar) findViewById(R.id.tb_muscle_group);
        setSupportActionBar(tbMuscleGroup);
        setTitle("Choose Muscle Groups");
        tbMuscleGroup.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        checkedGroups = new ArrayList<>();

        Button btnNextA4 = (Button) findViewById(R.id.btn_next_a4);
        btnNextA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedGroups.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Select a muscle group to continue.", Toast.LENGTH_SHORT).show();
                } else {
                    openEquipmentActivity();
                }
            }
        });
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.cb_chest:
                if (checked) {
                    checkedGroups.add("Chest");
                } else {
                    checkedGroups.remove("Chest");
                }
                break;
            case R.id.cb_shoulders:
                if (checked) {
                    checkedGroups.add("Shoulders");
                } else {
                    checkedGroups.remove("Shoulders");
                }
                break;
            case R.id.cb_back:
                if (checked) {
                    checkedGroups.add("Back");
                } else {
                    checkedGroups.remove("Back");
                }
                break;
            case R.id.cb_traps:
                if (checked) {
                    checkedGroups.add("Traps");
                } else {
                    checkedGroups.remove("Traps");
                }
                break;
            case R.id.cb_legs:
                if (checked) {
                    checkedGroups.add("Legs");
                } else {
                    checkedGroups.remove("Legs");
                }
                break;
            case R.id.cb_abs:
                if (checked) {
                    checkedGroups.add("Abs");
                } else {
                    checkedGroups.remove("Abs");
                }
                break;
            case R.id.cb_triceps:
                if (checked) {
                    checkedGroups.add("Triceps");
                } else {
                    checkedGroups.remove("Triceps");
                }
                break;
            case R.id.cb_biceps:
                if (checked) {
                    checkedGroups.add("Biceps");
                } else {
                    checkedGroups.remove("Biceps");
                }
                break;
        }

    }

    public void openEquipmentActivity() {
        Intent intentA4 = new Intent(this, EquipmentActivity.class);
        startActivity(intentA4);
    }

    public static List<String> getCheckedGroups() {
        return checkedGroups;
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
                Intent intentOpenHome = new Intent(MuscleGroupActivity.this, MainActivity.class);
                startActivity(intentOpenHome);
                return true;
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(MuscleGroupActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
