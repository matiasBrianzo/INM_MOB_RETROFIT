package com.example.mov_tp03.ui.inquilinos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mov_tp03.Modelo.Pago;
import com.example.mov_tp03.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Pago> pagosAsociados;
    private LayoutInflater inflater;

    public PagosAdapter(Context context, ArrayList<Pago> pagosAsociados, LayoutInflater inflater) {
        this.context = context;
        this.pagosAsociados = pagosAsociados;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public PagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_pago, parent, false);
        return new PagosAdapter.ViewHolder(root);
    }


    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.ViewHolder holder, int position) {
        holder.idPago.setText(String.valueOf(pagosAsociados.get(position).getIdPago()));
        holder.numPago.setText(String.valueOf(pagosAsociados.get(position).getIdPago()));
        holder.codigoContrato.setText(String.valueOf(pagosAsociados.get(position).getContrato().getIdContrato()));
        holder.importe.setText(String.valueOf(pagosAsociados.get(position).getMonto()));

        String fechaDePago = pagosAsociados.get(position).getFechaPago();
        holder.fechaDePago.setText(customFecha(fechaDePago));
    }

    @Override
    public int getItemCount() {
        return pagosAsociados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView idPago,numPago, codigoContrato, importe, fechaDePago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idPago = itemView.findViewById(R.id.tvCodigoPago);
            numPago = itemView.findViewById(R.id.tvNumPago);
            codigoContrato = itemView.findViewById(R.id.tvCodigoContrato);
            importe = itemView.findViewById(R.id.tvImporte);
            fechaDePago = itemView.findViewById(R.id.tvFechaPago);

        }

    }
    public String customFecha(String pfecha) {
        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(parser.parse(pfecha));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
