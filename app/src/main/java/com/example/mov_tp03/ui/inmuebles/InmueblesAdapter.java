package com.example.mov_tp03.ui.inmuebles;
/*
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mov_tp03.R;
import com.example.mov_tp03.Modelo.Inmueble;

import java.util.List;

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.ViewHolder> {

    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater inflater;

    public InmueblesAdapter(Context context, List<Inmueble> inmuebles, LayoutInflater inflater) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(root);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.direccion.setText(inmuebles.get(position).getDireccion());
        holder.monto.setText(inmuebles.get(position).getPrecioInmueble() + "");
        //Glide.with(context).load(inmuebles.get(position).getImagen()).into(holder.fotoInmueble);


    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //ImageView fotoInmueble;
        TextView direccion, monto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //fotoInmueble = itemView.findViewById(R.id.ivInmueble);
            direccion = itemView.findViewById(R.id.tvDireccion);
            monto = itemView.findViewById(R.id.tvMontoMensual);
        }

        @Override
        public void onClick(View v) {
            NavController navController = Navigation.findNavController(v);
            Inmueble inmueble = inmuebles.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", inmueble);
            navController.navigate(R.id.nav_detalleInmuebleFragment, bundle);
        }

    }

}
*/