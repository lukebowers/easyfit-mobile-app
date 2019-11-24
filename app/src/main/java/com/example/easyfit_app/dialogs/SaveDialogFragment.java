package com.example.easyfit_app.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easyfit_app.R;
import com.example.easyfit_app.activities.WorkoutActivity;
import com.example.easyfit_app.model.Workout;

/**
 * Creates a popup window for the user to enter a name for their saved workout.
 */

public class SaveDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        final LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_save_view, null));

        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Dialog dialogView = (Dialog) getDialog();
                EditText input = (EditText) dialogView.findViewById(R.id.et_enter_name);
                String name;
                if (input.getText().toString().equals("")) {
                    name = "My Workout";
                } else {
                    name = input.getText().toString();
                }

                Workout workout = WorkoutActivity.getCurrentWorkout();
                workout.setWorkoutName(name);
                WorkoutActivity.saveWorkout(workout);
                Toast.makeText(getActivity(), "Saving workout…",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                SaveDialogFragment.this.getDialog().cancel();
            }
        });
        return builder.create();

    }
}
