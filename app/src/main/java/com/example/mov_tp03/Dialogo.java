package com.example.mov_tp03;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class Dialogo {
    public static void mostratDialogo(Context context){
        new AlertDialog.Builder(context)
                .setTitle("Atencion!")
                .setMessage("Usted esta por abandonar una aplicacion Epica. ¿Estas seguro?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity act = (Activity) context;
                        act.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Seguimos entonces :)", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}