package com.example.apikey;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private ProductoAdapter adapter;
    private List<Producto> productos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recycler_view_productos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductoAdapter(productos);
        recyclerView.setAdapter(adapter);

        // Simulación de obtención de productos
        simularObtenerProductos();

        FloatingActionButton fabAgregar = findViewById(R.id.fab_agregar_producto);
        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgregarProductoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void simularObtenerProductos() {
        // Simula una respuesta JSON de la API
        String json = "[{\"id\": 1, \"nombre\": \"Producto 1\", \"descripcion\": \"Descripción 1\", \"precio\": 10.00}, {\"id\": 2, \"nombre\": \"Producto 2\", \"descripcion\": \"Descripción 2\", \"precio\": 20.00}]";

        try {
            JSONArray response = new JSONArray(json);
            productos.clear();
            for (int i = 0; i < response.length(); i++) {
                JSONObject productoJson = response.getJSONObject(i);
                int id = productoJson.getInt("id");
                String nombre = productoJson.getString("nombre");
                String descripcion = productoJson.getString("descripcion");
                double precio = productoJson.getDouble("precio");
                productos.add(new Producto(id, nombre, descripcion, precio));
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}