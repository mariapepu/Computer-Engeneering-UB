package com.example.coinnote.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coinnote.R;
import com.example.coinnote.model.Gasto;
import com.example.coinnote.model.Nota;
import com.example.coinnote.modelview.GastoAdapterFireStore;
import com.example.coinnote.modelview.NotaAdapterFireStore;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class VisualizarNota extends AppCompatActivity {

    ImageButton add;
    ImageButton flecha, basura;
    ImageButton editar;
    ImageButton ver_imagen;
    Dialog dialogShareNote;
    TextView categoria;
    Task<QuerySnapshot> firestore;
    String TAG = "missatge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_nota);
        dialogShareNote = new Dialog(this);
        String categoriaNota = getIntent().getStringExtra("CATEGORIA_NOTA");
        String colorNota = getIntent().getStringExtra("COLOR_NOTA");
        categoria= findViewById(R.id.categoria);
        categoria.setText(categoriaNota);
        categoria.setBackgroundColor(Color.parseColor(colorNota));
        if(isColorDark(Color.parseColor(colorNota))){
            categoria.setTextColor(Color.WHITE);
        }else {
            categoria.setTextColor(Color.BLACK);
        }

        add = (ImageButton) findViewById(R.id.añadir);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualizarNota.this, CrearGasto.class);
                intent.putExtra("NOTA_ID", FirebaseAuth.getInstance().getUid()+getIntent().getStringExtra("CATEGORIA_NOTA"));
                startActivity(intent);
            }
        });
        basura = (ImageButton) findViewById(R.id.basura);
        basura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notaAdapterFireStore.deleteItem();

            }
        });
        flecha = (ImageButton) findViewById(R.id.flecha);
        flecha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        //Metodo que inicia el recycler
        init();



    }

    // Metodo para que sirve para determinar si un color es oscuro o no
    public boolean isColorDark(int color){
        double darkness = 1-(0.299*Color.red(color) + 0.587*Color.green(color) + 0.114*Color.blue(color))/255;
        if(darkness<0.5){
            return false; // It's a light color
        }else{
            return true; // It's a dark color
        }
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewGasto);
        Query query = FirebaseFirestore.getInstance().collection("gastos").whereEqualTo("notaId", FirebaseAuth.getInstance().getUid()+getIntent().getStringExtra("CATEGORIA_NOTA"));
        FirestoreRecyclerOptions<Gasto> options = new FirestoreRecyclerOptions.Builder<Gasto>()
                .setQuery(query, Gasto.class)
                .build();
        GastoAdapterFireStore gastoAdapterFireStore = new GastoAdapterFireStore(options);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(gastoAdapterFireStore);
        gastoAdapterFireStore.startListening();



        //swipe gasto
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                gastoAdapterFireStore.deleteItem(viewHolder.getAdapterPosition());


            }
        }).attachToRecyclerView(recyclerView);



    }

    public void botonCompartir(View v){
        dialogShareNote.setContentView(R.layout.dialog_compartir);
        dialogShareNote.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogShareNote.show();
        Button btnOk = dialogShareNote.findViewById(R.id.aceptar);
        Button btnCancel = dialogShareNote.findViewById(R.id.cancelar);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShareNote.dismiss();
                Toast.makeText(VisualizarNota.this, "Cancelar", Toast.LENGTH_SHORT).show();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShareNote.dismiss();
                Toast.makeText(VisualizarNota.this, "Acceptar", Toast.LENGTH_SHORT).show();
            }
        });

    }
}