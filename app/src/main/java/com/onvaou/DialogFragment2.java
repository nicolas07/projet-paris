package com.onvaou;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by Nicolas on 23/10/2017.
 */
public class DialogFragment2 extends DialogFragment {

    SendMessage SM;

    public DialogFragment2() {
        // Empty constructor required for DialogFragment
    }

    public static DialogFragment2 newInstance() {

        DialogFragment2 frag = new DialogFragment2();
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String title = getArguments().getString("title");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return alertDialogBuilder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
