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
 * Creates a window where the user can select the equipment for the workout. The window contains
 * radio buttons with the options 'Freeweights', 'Gym machines' and 'No equipment' and a button
 * that generates the workout.
 */

public class EquipmentActivity extends AppCompatActivity {

    CheckBox cbFreeweights, cbMachines, cbNone;

    private static List<String> checkedEquipment;
    private Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        Toolbar tbEquipment = (Toolbar) findViewById(R.id.tb_equipment);
        setSupportActionBar(tbEquipment);
        setTitle("Choose Equipment");
        tbEquipment.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        checkedEquipment = new ArrayList<>();

        btnGenerate = (Button) findViewById(R.id.btn_generate);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedEquipment.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Select equipment to continue.", Toast.LENGTH_SHORT).show();
                } else {
                    openWorkoutActivity();
                }
            }
        });
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.cb_freeweights:
                if (checked) {
                    checkedEquipment.add("Freeweights");
                } else {
                    checkedEquipment.remove("Freeweights");
                }
                break;
            case R.id.cb_machines:
                if (checked) {
                    checkedEquipment.add("Machine");
                } else {
                    checkedEquipment.remove("Machine");
                }
                break;
            case R.id.cb_no_equipment:
                if (checked) {
                    checkedEquipment.add("None");
                } else {
                    checkedEquipment.remove("None");
                }
                break;
        }
    }

    public void openWorkoutActivity() {
        Intent intentA5 = new Intent(this, WorkoutActivity.class);
        startActivity(intentA5);
    }

    public static List<String> getCheckedEquipment() {
        return checkedEquipment;
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
                Intent intentOpenHome = new Intent(EquipmentActivity.this, MainActivity.class);
                startActivity(intentOpenHome);
                return true;
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(EquipmentActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
