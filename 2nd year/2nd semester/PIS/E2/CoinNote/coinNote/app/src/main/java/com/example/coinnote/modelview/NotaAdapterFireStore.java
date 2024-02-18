package com.example.coinnote.modelview;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coinnote.R;
import com.example.coinnote.model.Nota;
import com.example.coinnote.view.MainActivity;
import com.example.coinnote.view.VisualizarNota;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import android.content.Intent;

import static androidx.core.content.ContextCompat.startActivity;

public class NotaAdapterFireStore extends FirestoreRecyclerAdapter<Nota, NotaAdapterFireStore.NotaAdpaterFireStoreViewHolder> {
    ImageButton editar;
    ImageButton ver_imagen;



    public NotaAdapterFireStore(@NonNull FirestoreRecyclerOptions<Nota> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotaAdpaterFireStoreViewHolder holder, int position, @NonNull Nota model) {
        holder.card.setCardBackgroundColor(Color.parseColor(model.getColorNota()));
        holder.categoria.setText(model.getCategoria());
        if(isColorDark(Color.parseColor(model.getColorNota()))){
            holder.categoria.setTextColor(Color.WHITE);
        holder.gastoTotal.setTextColor(Color.WHITE);
        }
        else{
            holder.categoria.setTextColor(Color.BLACK);
        holder.gastoTotal.setTextColor(Color.BLACK);}
        holder.gastoTotal.setText(String.valueOf(model.getGastoTotal()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToVisualizeNote= new Intent(v.getContext(), VisualizarNota.class);
                goToVisualizeNote.putExtra("CATEGORIA_NOTA", model.getCategoria());
                goToVisualizeNote.putExtra("COLOR_NOTA", model.getColorNota());
                startActivity(v.getContext(), goToVisualizeNote,null);
            }
        });


    }

    @NonNull
    @Override
    public NotaAdpaterFireStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.element_nota_de_grid, null);
        return new NotaAdpaterFireStoreViewHolder(view);
    }

    public class NotaAdpaterFireStoreViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView categoria, gastoTotal;

        public NotaAdpaterFireStoreViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.element_nota_cardview);
            categoria = itemView.findViewById(R.id.categoria_nota_textview);
            gastoTotal = itemView.findViewById(R.id.gasto_total_nota_textview);

        }

    }
    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();

    }

    public boolean isColorDark(int color){
        double darkness = 1-(0.299*Color.red(color) + 0.587*Color.green(color) + 0.114*Color.blue(color))/255;
        if(darkness<0.5){
            return false; // It's a light color
        }else{
            return true; // It's a dark color
        }
    }
}
