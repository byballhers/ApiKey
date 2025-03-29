package com.example.apikey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextDescripcion, editTextPrecio;
    private Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        editTextNombre = findViewById(R.id.edit_text_nombre);
        editTextDescripcion = findViewById(R.id.edit_text_descripcion);
        editTextPrecio = findViewById(R.id.edit_text_precio);
        buttonGuardar = findViewById(R.id.button_guardar);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombre.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                String precioString = editTextPrecio.getText().toString();

                if (nombre.isEmpty() || descripcion.isEmpty() || precioString.isEmpty()) {
                    Toast.makeText(AgregarProductoActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double precio = Double.parseDouble(precioString);
                    simularCrearProducto(nombre, descripcion, precio);
                } catch (NumberFormatException e) {
                    Toast.makeText(AgregarProductoActivity.this, "Precio inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void simularCrearProducto(String nombre, String descripcion, double precio) {
        // Simula la creación de un producto
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AgregarProductoActivity.this, "Producto creado (simulado)", Toast.LENGTH_SHORT).show();
                finish(); // Cierra la actividad después de la simulación
            }
        }, 1000); // Simula una demora de 1 segundo
    }
}