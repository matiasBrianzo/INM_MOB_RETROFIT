package com.example.mov_tp03.ui.inmuebles;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mov_tp03.R;
import com.example.mov_tp03.databinding.FragmentCrearInmuebleBinding;

public class CrearInmuebleFragment extends Fragment {


    private CrearInmuebleViewModel mViewModel;
    private FragmentCrearInmuebleBinding binding;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        //  return inflater.inflate(R.layout.fragment_crear_inmueble, container, false);
        binding = FragmentCrearInmuebleBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this).get(CrearInmuebleViewModel.class);
        isEnable(false);
        // TODO: Use the ViewModel
        abrirGaleria();
        binding.btnSeleccionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.launch(intent);
                isEnable(true);
            }
        });

        binding.btnGuardarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargarInmueble();
            }
        });
        mViewModel.getUriMutable().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.ivFotoInmueble.setImageURI(uri);

            }
        });
        return binding.getRoot();
    }

    private void cargarInmueble(){
        mViewModel.RunSave(
                binding.etDireccion.getText().toString(),
                binding.etUso.getText().toString(),
                binding.etTipo.getText().toString(),
                binding.etLatitud.getText().toString(),
                binding.etLongitud.getText().toString(),
                binding.etPrecio.getText().toString(),
                binding.etAmbientes.getText().toString(),
                binding.cbDisponible.isChecked(),
                binding.etSuperficie.getText().toString());
    }

    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        arl=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                mViewModel.recibirFoto(result);
            }
        });
    }
    private void isEnable(boolean enable)
    {
        binding.etDireccion.setEnabled(enable);
        binding.etUso.setEnabled(enable);
        binding.etTipo.setEnabled(enable);
        binding.etLatitud.setEnabled(enable);
        binding.etLongitud.setEnabled(enable);
        binding.etPrecio.setEnabled(enable);
        binding.etAmbientes.setEnabled(enable);
        binding.cbDisponible.setEnabled(enable);
        binding.etSuperficie.setEnabled(enable);
        binding.btnGuardarInmueble.setEnabled(enable);
    }
}