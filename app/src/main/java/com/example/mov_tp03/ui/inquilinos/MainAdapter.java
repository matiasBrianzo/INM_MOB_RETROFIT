package com.example.mov_tp03.ui.inquilinos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.R;

import java.util.List;

  public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolderInquilino> {

    private Context context;
    private List<Inmueble> alquilados;
    private LayoutInflater inflater;

    public MainAdapter(Context context, List<Inmueble> pInmueble, LayoutInflater inflater) {
        this.context = context;
        this.alquilados = pInmueble;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public MainAdapter.ViewHolderInquilino onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=inflater.inflate(R.layout.item_inquilino,parent,false);

        return new MainAdapter.ViewHolderInquilino(itemView);
    }

      @Override
      public void onBindViewHolder(@NonNull ViewHolderInquilino holder, int position) {
          Inmueble inmueble = alquilados.get(position);
          holder.direccion.setText(inmueble.getDireccion());
          Glide.with(context)
                  .load(inmueble.getPathImagen())
                  .placeholder(null)
                  .error("null")
                  .into(holder.portada);
      }

    @Override
    public int getItemCount() {
        return alquilados.size();
    }
      public class ViewHolderInquilino extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView direccion;
        private ImageView portada;
        Button btnVerInquilino,btnVerContrato;
        public ViewHolderInquilino(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccion);
            portada= itemView.findViewById(R.id.imgPortada);
            btnVerInquilino = itemView.findViewById(R.id.btnVerInquilino);
            btnVerContrato = itemView.findViewById(R.id.btnVerContrato);
            btnVerContrato.setOnClickListener(this);
            btnVerInquilino.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            NavController navController = Navigation.findNavController(v);
            Inmueble inmueble = alquilados.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", inmueble);
            if (v.getId() == btnVerInquilino.getId()) {
                // Lógica para btnVerInquilino
                navController.navigate(R.id.nav_detalleInquilinoFragment, bundle);
            } else if (v.getId() == btnVerContrato.getId()) {
                // Lógica para btnVerContrato
                navController.navigate(R.id.nav_detalleContratoFragment, bundle); // ¡Cambia R.id.nav_detalleContratoFragment por el ID real de tu destino!
            }
        }

    }
}