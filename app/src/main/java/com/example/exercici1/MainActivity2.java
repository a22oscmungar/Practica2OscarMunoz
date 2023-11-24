package com.example.exercici1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.exercici1.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMain2Binding binding;
    TextView tvUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MediaPlayer mp = MediaPlayer.create(this, R.raw.cancion);
        mp.start();

        DataBaseHelper dbHelper = new DataBaseHelper(MainActivity2.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] values = {
                DataBaseHelper.COLUMN_NOMBRE,
                DataBaseHelper.COLUMN_PASS
        };

        Cursor cursor = db.query(
                DataBaseHelper.TABLE_NAME,
                values,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_NOMBRE));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_PASS));

            Log.d("prueba sqlite", nombre + " " + pass);
        }

        if(cursor != null){
            db.close();
        }

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
/*
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}