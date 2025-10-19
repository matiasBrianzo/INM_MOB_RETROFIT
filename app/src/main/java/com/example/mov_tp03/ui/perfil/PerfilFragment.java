package com.example.mov_tp03.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mov_tp03.Modelo.Propietario;
import com.example.mov_tp03.R;
import com.example.mov_tp03.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        vm=new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                // Se habilita la edicion de los campos
                SetEnable(aBoolean);
            }
        });
        vm.getNombre().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //Se cambia el Nombre del Boton de Editar a Guardar
                binding.btPerfil.setText(s);
            }
        });

        binding.btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se Actuliza el Propitario
                vm.cambioBoton(binding.btPerfil.getText().toString(),
                        binding.etNombre.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etDni.getText().toString(),
                        binding.etTelefono.getText().toString(),
                        binding.etEmail.getText().toString());
            }
        });

        vm.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                //LLeno el formulario
                SetEnable(false);
                binding.etIdPropietario.setEnabled(false);

                binding.etNombre.setText(p.getNombre());
                binding.etApellido.setText(p.getApellido());
                binding.etDni.setText(p.getDni());
                binding.etTelefono.setText(p.getTelefono());
                binding.etEmail.setText(p.getEmail());
                binding.etIdPropietario.setText(String.valueOf(p.getIdPropietario()));
            }
        });

        vm.obtenerPerfil();
        return root;
    }

    private void SetEnable(boolean b) {
        binding.etNombre.setEnabled(b);
        binding.etApellido.setEnabled(b);
        binding.etDni.setEnabled(b);
        binding.etTelefono.setEnabled(b);
        binding.etEmail.setEnabled(b);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}