package com.example.coinnote.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coinnote.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ModificarContraseña extends AppCompatActivity {

    Button modificar_contraseña;
    TextView txtusername;
    ImageButton atras;
    ImageView imagen_perfil;
    FirebaseAuth fAuth;
    StorageReference storageReference;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cont);
        imagen_perfil= findViewById(R.id.imagen_perfi);
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        txtusername = findViewById(R.id.nombre_usuario);
        fStore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = fStore.collection("Usuario").document(fAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                txtusername.setText(value.getString("Username"));

            }
        });

        StorageReference image = storageReference.child("usuario/" + fAuth.getCurrentUser().getUid() + "/foto_perfil.jpg");
        image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imagen_perfil);
            }

        });


        atras = (ImageButton) findViewById(R.id.atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modificar_contraseña = (Button) findViewById(R.id.modificar_contraseña);
        modificar_contraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModificarContraseña.this, Perfil.class);
                startActivity(intent);

            }
        });
    }



}