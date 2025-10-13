package com.example.mov_tp03.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mov_tp03.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel cargarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        cargarViewModel = new ViewModelProvider(this).get(CargarViewModel.class);

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

     cargarViewModel.getMsjError().observe(getViewLifecycleOwner(), new Observer<String>() {
     @Override
     public void onChanged(String s) {

         binding.tvError.setText(s);
     }
      });
     cargarViewModel.getMsjExito().observe(getViewLifecycleOwner(), new Observer<String>() {
         @Override
         public void onChanged(String s) {
             Limpiar();
             binding.tvError.setText(s);
         }
     });

        binding.btCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvError.setText("");
                cargarViewModel.AddProducto(binding.etCodigo.getText().toString(),
                                            binding.etDescricion.getText().toString(),
                                            binding.etPrecio.getText().toString());
            }
        });

        return root;
    }
private void Limpiar(){
    binding.tvError.setText("");
    binding.etCodigo.setText("");
    binding.etDescricion.setText("");
    binding.etPrecio.setText("");
}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}