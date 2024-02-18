package com.example.coinnote.modelview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coinnote.R;
import com.example.coinnote.model.Gasto;
import com.example.coinnote.view.CrearGasto;
import com.example.coinnote.view.VisualizarNota;
import com.example.coinnote.view.popUp_Imagen;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

import static androidx.core.content.ContextCompat.startActivity;

public class GastoAdapterFireStore extends FirestoreRecyclerAdapter<Gasto, GastoAdapterFireStore.GastoAdapterFireStoreViewHolder> {

    public GastoAdapterFireStore(@NonNull FirestoreRecyclerOptions<Gasto> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull GastoAdapterFireStoreViewHolder holder, int position, @NonNull Gasto model) {
        holder.concepto.setText(model.getConcepto());
        holder.fecha.setText(model.getFecha());
        holder.gasto.setText(model.getCoste());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEditGasto= new Intent(v.getContext(), CrearGasto.class);
                goToEditGasto.putExtra("concepto", model.getConcepto());
                goToEditGasto.putExtra("fecha", model.getFecha());
                startActivity(v.getContext(), goToEditGasto,null);
            }
        });
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Cargando imagen ...",Toast.LENGTH_SHORT).show();
                Intent goToImageFullView = new Intent(v.getContext(), popUp_Imagen.class);
                goToImageFullView.putExtra("UID", model.getConcepto()+model.getFecha());
                startActivity(v.getContext(),goToImageFullView,null);
            }
        });

    }

    @NonNull
    @Override
    public GastoAdapterFireStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.element_gasto, null);
        return new GastoAdapterFireStoreViewHolder(view);
    }
    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();

    }

    public class GastoAdapterFireStoreViewHolder extends RecyclerView.ViewHolder{
        TextView concepto, fecha,gasto;
        ImageButton imagen;

        public GastoAdapterFireStoreViewHolder(@NonNull View itemView) {
            super(itemView);
            concepto= itemView.findViewById(R.id.concepto_element);
            fecha= itemView.findViewById(R.id.fechaGasto_element);
            gasto= itemView.findViewById(R.id.gastoCoste_element);
            imagen= itemView.findViewById(R.id.ver_imagen_element);
        }
    }
}
