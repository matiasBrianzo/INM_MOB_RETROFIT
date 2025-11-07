package com.example.mov_tp03.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mov_tp03.Modelo.Contrato;
import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.Modelo.Inquilino;
import com.example.mov_tp03.R;
import com.example.mov_tp03.databinding.FragmentDetalleInquilinoBinding;

public class DetalleInquilinoFragment extends Fragment {
    private FragmentDetalleInquilinoBinding binding;
    private DetalleInquilinoViewModel mv;
    private Inmueble inmueble;

    public static DetalleInquilinoFragment newInstance() {
        return new DetalleInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        inmueble = (Inmueble) bundle.getSerializable("inmueble");
        mv.getInquilinoMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                binding.tvCodigo.setText(String.valueOf(contrato.getIdInquilino()));
                binding.tvDni.setText(String.valueOf(contrato.getInquilino().getDni()));
                binding.tvNombre.setText(contrato.getInquilino().getNombre());
                binding.tvApellido.setText(contrato.getInquilino().getApellido());
                binding.tvMail.setText(contrato.getInquilino().getEmail());
                binding.tvTelefono.setText(contrato.getInquilino().getTelefono());
            }
        });
        mv.obtenerInquilino(inmueble);

        return root;
    }



}