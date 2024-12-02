package com.example.todolistreal;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistreal.databinding.MoldeTareasBinding;

import java.util.List;

public class ListaTareasAdapter extends RecyclerView.Adapter<ListaTareasAdapter.TareasViewHolder> {

    List<Tarea> lista;

    public ListaTareasAdapter(List<Tarea> lista) {this.lista = lista;
    }

    @NonNull
    @Override
    public TareasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TareasViewHolder(MoldeTareasBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull TareasViewHolder holder, int position) {
        Tarea t = lista.get(position);

        holder.binding.nombreTarea.setText(t.getNombre());
        holder.binding.descripcionTarea.setText(t.getDescripcion().toString());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class TareasViewHolder extends RecyclerView.ViewHolder {

        MoldeTareasBinding binding;

        public TareasViewHolder(@NonNull MoldeTareasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
