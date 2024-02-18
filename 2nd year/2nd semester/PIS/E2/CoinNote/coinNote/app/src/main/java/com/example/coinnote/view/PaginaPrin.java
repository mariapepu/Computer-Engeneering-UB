package com.example.coinnote.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coinnote.R;
import com.example.coinnote.model.Nota;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import yuku.ambilwarna.AmbilWarnaDialog;

public class PaginaPrin extends AppCompatActivity {


    //  Comporentes en la pagina principal
    FloatingActionButton botonFlotante;
    Dialog dialogAddNote, dialogShareNote;
    //      Dialogo para agregar nota
    EditText categoria;
    Button dialogButtonAcept, dialogButtonDismis;
    Button selectColor;
    //      Para TabLayout
    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tabPrin, tabCal;
    PagerController pagerAdapter;
    ImageView imagen_perfil;
    TextView txtusername;
    StorageReference storageReference;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;


    int mDefaultColor;


    //firebase
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginaprin);

        botonFlotante = findViewById(R.id.fab);
        setSupportActionBar(findViewById(R.id.main_toolbar));

        dialogAddNote = new Dialog(this);

        mDefaultColor = ContextCompat.getColor(PaginaPrin.this, R.color.black);
        selectColor = findViewById(R.id.dialog_add_note_color_picker);


        dialogShareNote = new Dialog(this);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);


        imagen_perfil= findViewById(R.id.imagen_perfi);
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();



        txtusername = findViewById(R.id.nombre_usuario);
        fStore = FirebaseFirestore.getInstance();

        setUserDataToolbar();
        setBotonFlotante();
        setTabLayout();


    }

    private void setUserDataToolbar() {
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
    }


    // Cosas del tabLayout
    private void setTabLayout() {
        tabLayout = findViewById(R.id.tabLay);
        viewPager = findViewById(R.id.pager);
        tabPrin = findViewById(R.id.tabPrin);
        tabCal = findViewById(R.id.tabCal);
        pagerAdapter = new PagerController(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setBotonFlotante() {
        botonFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddNote.setContentView(R.layout.dialog_add_note);
                dialogAddNote.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogAddNote.show();

                categoria = dialogAddNote.findViewById(R.id.dialog_add_note_category_variable);
                dialogButtonAcept = dialogAddNote.findViewById(R.id.dialog_add_note_aceptar);
                dialogButtonDismis = dialogAddNote.findViewById(R.id.dialog_add_note_cancelar);

                dialogButtonAcept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //añade notas a la coleccion

                        Map<String, Object> map = new HashMap<>();
                        String hexColor = String.format("%06X", (0xFFFFFF & mDefaultColor));
                        map.put("categoria", categoria.getText().toString());
                        map.put("gastoTotal", 200);
                        map.put("colorNota", "#" + hexColor);
                        map.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        map.put("notaID", FirebaseAuth.getInstance().getCurrentUser().getUid()+categoria.getText().toString());
                        if (categoria.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(), "La categoria no puede estar vacia", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            firestore.collection("Notas").add(map)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d("Tag", "nota a firebase");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("Tag", "ha fallado");
                                        }
                                    });

                            Intent irACrearGastoActivity = new Intent(v.getContext(), VisualizarNota.class);
                            irACrearGastoActivity.putExtra("CATEGORIA_NOTA",categoria.getText().toString());
                            irACrearGastoActivity.putExtra("COLOR_NOTA", "#"+hexColor );
                            startActivity(irACrearGastoActivity);

                            dialogAddNote.dismiss();
                        }

                    }
                });
                dialogButtonDismis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialogAddNote.dismiss();
                    }
                });

            }
        });
    }


    // Definir el compportameinto del dialogo para seleccionar el color de la libreria Ambiwarma
    public void openColorPicker(View view) {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @SuppressLint("ResourceAsColor")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                Button s = dialogAddNote.findViewById(R.id.box_color);
                String hexColor = String.format("%06X", (0xFFFFFF & color));
                s.setBackgroundColor(color);
            }
        });
        colorPicker.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Comportamiento de la toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_settings was selected
            // Muestra SPinner para listar notas
            case R.id.app_bar_filter:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(PaginaPrin.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_popup_listar, null);
                mBuilder.setTitle("Listar notas por");
                Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adabter = new ArrayAdapter<String>(PaginaPrin.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.items));
                adabter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adabter);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("-elige opción-")) {
                            Toast.makeText(PaginaPrin.this, mSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else if( mSpinner.getSelectedItem().toString().equalsIgnoreCase("Ordenar de A a Z")){

                        }
                        else if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("Ordenar de Z a A")){

                        }
                        else if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("Ordenar por gasto mayor")){

                        }
                        else if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("Ordenar por gasto menor")){

                        }
                        else if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("Ordenar por fecha")){

                        }
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();

                break;
            // Filtrar las notas solo por notas compartidas
            case R.id.app_bar_shared:

                Toast.makeText(this, "Shared Selected", Toast.LENGTH_SHORT)
                        .show();

                break;
            // Cierrra la sesion en Firebase autentication
            case R.id.app_bar_menu_cerrrarSesion:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;

            // Va a la activity perfil
            case R.id.app_bar_menu_perfil:
                Toast.makeText(this, "Perfil Selected", Toast.LENGTH_SHORT)
                        .show();
                Intent switchActivityIntent2 = new Intent(this, Perfil.class);
                startActivity(switchActivityIntent2);
                break;

            default:
                break;
        }
        return true;
    }


}