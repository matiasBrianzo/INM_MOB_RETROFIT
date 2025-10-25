package com.example.mov_tp03.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mov_tp03.R;
import com.example.mov_tp03.Modelo.Inmueble;
import com.bumptech.glide.Glide;

import java.util.List;

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.ViewHolderInmueble> {

    private List<Inmueble> listaInmuebles;
    private Context context;
    private LayoutInflater inflater;
    private static final String PATH = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";


    public InmueblesAdapter(List<Inmueble> listaInmuebles, Context context, LayoutInflater inflater) {
        this.listaInmuebles = listaInmuebles;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolderInmueble onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=inflater.inflate(R.layout.item_inmueble,parent,false);
        return new ViewHolderInmueble(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInmueble holder, int position) {
        Inmueble inmActual = listaInmuebles.get(position);
        holder.direccion.setText(inmActual.getDireccion());
        holder.precio.setText(inmActual.getValor()+"");
        Glide.with(context)
                .load(PATH + inmActual.getImagen())
                .placeholder(null)
                .error("null")
                .into(holder.portada);
    }

    @Override
    public int getItemCount() {
        return listaInmuebles.size();
    }
//public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public class ViewHolderInmueble extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView direccion, precio;
        private ImageView portada;
        public ViewHolderInmueble(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccion);
            precio = itemView.findViewById(R.id.tvValor);
            portada= itemView.findViewById(R.id.imgPortada);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            NavController navController = Navigation.findNavController(v);
            Inmueble inmueble = listaInmuebles.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", inmueble);
            navController.navigate(R.id.nav_detalleInmuebleFragment, bundle);
        }
    }
}
