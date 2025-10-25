package com.example.mov_tp03.ui.inmuebles;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.R;
import com.example.mov_tp03.databinding.FragmentDetalleInmuebleBinding;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel mv;
    private FragmentDetalleInmuebleBinding binding;
    private Inmueble inmueble;


    public static DetalleInmuebleFragment newInstance() {
        return new DetalleInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        inmueble = (Inmueble) bundle.getSerializable("inmueble");

        binding.tvCodigo.setText(String.valueOf(inmueble.getIdInmueble()));
        binding.tvAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
        binding.tvDireccion.setText(inmueble.getDireccion());
        binding.tvPrecio.setText(String.valueOf(inmueble.getValor()));
        binding.tvUso.setText(inmueble.getUso());
        binding.tvTipo.setText(inmueble.getTipo());
        //Picasso.get().load(inmueble.getImagen()).into(binding.imageView2);
        //binding.cbEstado.setChecked(inmueble.getdisponible().equals("Disponible"));
        binding.cbEstado.setChecked(inmueble.isDisponible());

        binding.cbEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // inmueble.setDisponible(binding.cbEstado.isChecked() ? "Disponible" : "Agotado");

                inmueble.setDisponible(binding.cbEstado.isChecked());
                mv.actualizarInmueble(inmueble);
            }
        });


        return root;
    }


}