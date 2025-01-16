package com.example.todolistreal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todolistreal.databinding.MoldeTareasBinding;
import java.util.List;

public class ListaTareasAdapter extends RecyclerView.Adapter<ListaTareasAdapter.TareasViewHolder> {

    private List<Tarea> lista;
    private OnItemClickListener onItemClickListener;
    private Fragment fragment;

    public ListaTareasAdapter(List<Tarea> lista, Fragment fragment) {
        this.lista = lista;
        this.fragment = fragment;
    }

    public interface OnItemClickListener {
        void onItemClick(Tarea tarea, int position);
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
        holder.binding.prioridad.setText(t.getPrioridad().toString());

        holder.binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(t, position);
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable("tarea", t);
                NavController navController = NavHostFragment.findNavController(fragment);
                navController.navigate(R.id.action_listaTareas_to_fragmentEditar, bundle);
            }
        });
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