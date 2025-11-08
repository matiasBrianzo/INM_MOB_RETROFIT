package com.example.mov_tp03.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mov_tp03.Modelo.Contrato;
import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.Modelo.Inquilino;
import com.example.mov_tp03.Modelo.Pago;
import com.example.mov_tp03.R;
import com.example.mov_tp03.databinding.FragmentDetalleContratoBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class DetalleContratoFragment extends Fragment {

    private FragmentDetalleContratoBinding binding;
    private Inmueble inmueble;
    private DetalleContratoViewModel mv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetalleContratoBinding.inflate(getLayoutInflater());
        mv = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        View root = binding.getRoot();


        Bundle bundle = getArguments();
        inmueble = (Inmueble) bundle.getSerializable("inmueble");

        mv.getContratoMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {

                Inquilino inquilino = contrato.getInquilino();
                Inmueble inmueble = contrato.getInmueble();

                binding.tvCodigo.setText(String.valueOf(contrato.getIdContrato()));
                binding.tvMontoMensual.setText("$ "+String.valueOf(contrato.getMontoAlquiler()));


                binding.tvFechaInicio.setText( mv.customFecha(contrato.getFechaInicio()));
                binding.tvFechaFinalizacion.setText( mv.customFecha( contrato.getFechaFinalizacion()));

                binding.tvInquilino.setText(inquilino.getNombre());
                binding.tvInmueble.setText(inmueble.getDireccion());
                binding.btnPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mv.getPagosMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
                            @Override
                            public void onChanged(ArrayList<Pago> pagos) {
                                NavController navController = Navigation.findNavController(v);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("pagosAsociados", pagos);
                                navController.navigate(R.id.nav_detallePagosFragment, bundle);
                            }
                        });

                        mv.obtenerPagosPorContrato(contrato);
                    }
                });
            }
        });

        mv.obtenerContrato(inmueble);

        return root;
    }
}
