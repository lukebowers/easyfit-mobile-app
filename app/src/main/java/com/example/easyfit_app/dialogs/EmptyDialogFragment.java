package com.example.easyfit_app.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.easyfit_app.R;

/**
 * Creates a popup window notifying the user of an empty workout.
 */

public class EmptyDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));

        builder.setMessage(R.string.content_empty);
        builder.setTitle(R.string.title_empty);

        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                com.example.easyfit_app.dialogs.EmptyDialogFragment.this.getDialog().dismiss();
            }
        });
        return builder.create();

    }
}