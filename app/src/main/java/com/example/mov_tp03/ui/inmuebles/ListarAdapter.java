package com.example.mov_tp03.ui.inmuebles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mov_tp03.Modelo.Producto;
import com.example.mov_tp03.R;

import java.util.List;

public class ListarAdapter extends RecyclerView.Adapter<ListarAdapter.ViewHolderList> {

    private List<Producto> LstProducto;
    private LayoutInflater inflater;
    private Context context;
    public ListarAdapter(Context context, List<Producto> lstProducto, LayoutInflater inflater) {
        this.context = context;
        this.LstProducto = lstProducto;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolderList(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderList holder, int position) {
       Producto proActual =LstProducto.get(position);
       holder.codigo.setText(proActual.getCodigo());
        holder.descripcion.setText(proActual.getDescricion());

        double pre= proActual.getPrecio();
        String str = ("$"+pre);
        holder.precio.setText(str);
    }

    @Override
    public int getItemCount() {
        return LstProducto.isEmpty()?1:LstProducto.size() ;
    }


    public class ViewHolderList extends RecyclerView.ViewHolder {

        TextView codigo,descripcion, precio;
        public ViewHolderList(@NonNull View itemView) {
            super(itemView);
            codigo=itemView.findViewById(R.id.tvCodigo);
            descripcion=itemView.findViewById(R.id.tvDesc);
            precio=itemView.findViewById(R.id.tvPrecio);
        }
    }

}
