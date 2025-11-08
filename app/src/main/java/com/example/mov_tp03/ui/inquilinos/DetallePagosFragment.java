package com.example.mov_tp03.ui.inquilinos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mov_tp03.Modelo.Pago;
import com.example.mov_tp03.R;
import com.example.mov_tp03.databinding.FragmentDetallePagosBinding;

import java.util.ArrayList;

public class DetallePagosFragment extends Fragment {

    private FragmentDetallePagosBinding binding;
    private ArrayList<Pago> pagos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetallePagosBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        pagos = (ArrayList<Pago>) bundle.getSerializable("pagosAsociados");


        RecyclerView rv = binding.rvListaPagos;
        GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(grilla);
        PagosAdapter adapter = new PagosAdapter(getContext(), pagos, getLayoutInflater());
        rv.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return root;
    }
}