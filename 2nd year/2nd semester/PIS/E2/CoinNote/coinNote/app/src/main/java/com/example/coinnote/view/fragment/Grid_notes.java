package com.example.coinnote.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.coinnote.R;
import com.example.coinnote.model.Nota;
import com.example.coinnote.modelview.NotaAdapterFireStore;
import com.example.coinnote.view.MainActivity;
import com.example.coinnote.view.PaginaPrin;
import com.example.coinnote.view.VisualizarNota;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Grid_notes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Grid_notes extends Fragment {
    List<Nota> elements;
    LinearLayout linearLayout;
    CardView card;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;
    CardView cardNota;
    View vista;
    Activity activity;

    public Grid_notes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment grid_notes.
     */
    // TODO: Rename and change types and number of parameters
    public static Grid_notes newInstance(String param1, String param2) {
        Grid_notes fragment = new Grid_notes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }





    }

    public List<Nota> getElements(){return  elements;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid_notes, container, false);
        RecyclerView recyclerView= view.findViewById(R.id.lista_notas_recylcerview);



        Query query = FirebaseFirestore.getInstance().collection("Notas").whereEqualTo("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirestoreRecyclerOptions<Nota> options =  new FirestoreRecyclerOptions.Builder<Nota>()
                .setQuery(query, Nota.class)
                .build();
        NotaAdapterFireStore notaAdapterFireStore = new NotaAdapterFireStore(options) ;

        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),3));
        recyclerView.setAdapter(notaAdapterFireStore);
        notaAdapterFireStore.startListening();

        return view;
    }


}
