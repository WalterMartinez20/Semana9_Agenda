package com.example.semana9_agenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Agregar_amigos extends AppCompatActivity {
    DB usuarios;
    String accion = "nuevo";
    String id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_amigos);
        mostrardatos();
    }

    public void mostrardatos() {
        try {
            Bundle bundle = getIntent().getExtras();
            accion = bundle.getString("accion");

            if (accion.equals("modificar")) {
                id = bundle.getString("id");
                String user[] = bundle.getStringArray("user");

                TextView tempval = (TextView) findViewById(R.id.txtnombre);
                tempval.setText(user[0].toString());

                tempval = (TextView)findViewById(R.id.txtdireccion);
                tempval.setText(user[1].toString());

                tempval = (TextView)findViewById(R.id.txtTelefono);
                tempval.setText(user[2].toString());
            }

        } catch (Exception e) {
            Toast.makeText(Agregar_amigos.this, "Error: " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void guardar_amigos(View v) {
        try {
            TextView tempval = (TextView) findViewById(R.id.txtnombre);
            String nom = tempval.getText().toString();

            tempval = (TextView) findViewById(R.id.txtdireccion);
            String dir = tempval.getText().toString();

            tempval = (TextView) findViewById(R.id.txtTelefono);
            String tel = tempval.getText().toString();

            usuarios = new DB(Agregar_amigos.this, "", null, 1);
            usuarios.guardarUsuario(nom, dir, tel, accion, id);

            Toast.makeText(Agregar_amigos.this, "Listo, amigo registrado con exito", Toast.LENGTH_LONG).show();
            Intent imostrar = new Intent(Agregar_amigos.this, MainActivity.class);
            startActivity(imostrar);

        } catch (Exception ex) {
            Toast.makeText(Agregar_amigos.this, "Error: " + ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}
