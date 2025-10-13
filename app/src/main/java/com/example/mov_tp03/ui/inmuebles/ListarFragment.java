package com.example.mov_tp03.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mov_tp03.Modelo.Producto;
import com.example.mov_tp03.databinding.FragmentListarBinding;

import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ListarViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =new ViewModelProvider(this).get(ListarViewModel.class);

        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
vm.getMLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Producto>>() {
    @Override
    public void onChanged(ArrayList<Producto> productos) {
    ListarAdapter la = new ListarAdapter(getContext(),(ArrayList<Producto>) productos,getLayoutInflater());
        GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
    binding.rvLista.setLayoutManager(grilla);
        binding.rvLista.setAdapter(la);
    }
});
vm.CargarLista();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}