package com.vinihans.persons_app.utils;

import static com.vinihans.persons_app.view_model.people.PeopleViewModel.deleteOne;

import android.app.AlertDialog;
import android.content.Context;

import com.vinihans.persons_app.model.people.People;


public class AlertDialogUtil {


    private Context context;

    public AlertDialogUtil(Context context) {
        this.context = context;
    }


    public static void deleteDialog(String message,Context context, People people,String token){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setNegativeButton("Não", null);
        alertDialog.setPositiveButton("Sim", (dialogInterface, i) -> {

            deleteOne(people,token);
            }
        );
        alertDialog.create();
        alertDialog.show();
    }

    public void createDialog(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context.getApplicationContext());
        alertDialog.setMessage(message);
        alertDialog.setNegativeButton("Não", null);
        alertDialog.setPositiveButton("Sim", null);
        alertDialog.create();
        alertDialog.show();
    }
}