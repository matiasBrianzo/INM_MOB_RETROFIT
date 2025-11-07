package com.example.mov_tp03.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.Modelo.Inquilino;
import com.example.mov_tp03.databinding.FragmentInquilinosBinding;
import com.example.mov_tp03.ui.inmuebles.InmueblesAdapter;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinosBinding binding;
    private InquilinosViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(InquilinosViewModel.class);
        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm.getListaInquilino().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InquilinosAdapter adapter = new InquilinosAdapter(getContext(),inmuebles,getLayoutInflater());
                GridLayoutManager glm=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                binding.listaInquilino.setLayoutManager(glm);
                binding.listaInquilino.setAdapter(adapter);
            }
        });


        vm.obtenerListaInmuebles();
        return root;

    }



}