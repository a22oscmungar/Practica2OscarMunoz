package com.example.exercici1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnEnviar;
    EditText etUsu, etPass;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = findViewById(R.id.btnEnviar);
        etUsu = findViewById(R.id.ETusu);
        etPass = findViewById(R.id.ETpass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //PRIMERO GUARDAMOS EN LA BASE DE DATOS
                DataBaseHelper dbHelper = new DataBaseHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                //AHORA AGREGAMOS LOS VALORES
                ContentValues values = new ContentValues();
                values.put(DataBaseHelper.COLUMN_NOMBRE, etUsu.getText().toString());
                values.put(DataBaseHelper.COLUMN_PASS, etPass.getText().toString());

                //ESTO HACE EL INSERT
                long newRowId = db.insert(DataBaseHelper.TABLE_NAME, null, values);

                //Y CERRAR CONEXION
                db.close();

                //AQUI HAREMOS EL INTENT PARA EL BASIC ACTIVITY
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnFormulario) {
            // Iniciar la actividad MainActivity
            Intent intent = new Intent(this, activityFormulario.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

