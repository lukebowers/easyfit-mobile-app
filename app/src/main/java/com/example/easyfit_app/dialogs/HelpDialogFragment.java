package com.example.easyfit_app.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.easyfit_app.R;

/**
 * Creates a popup window with text explaining how to use the app.
 */

public class HelpDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.title_help);
        builder.setMessage(R.string.content_help);

        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                com.example.easyfit_app.dialogs.HelpDialogFragment.this.getDialog().dismiss();
            }
        });
        return builder.create();

    }
}
