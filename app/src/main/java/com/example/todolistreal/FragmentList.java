package com.example.todolistreal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistreal.databinding.FragmentListBinding;


public class FragmentList extends Fragment {

    FragmentListBinding binding;
    TareasViewModel tareasViewModel;

    public FragmentList() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        binding.RecyclerListaTareas.setLayoutManager(layoutManager);

        tareasViewModel = new ViewModelProvider(requireActivity()).get(TareasViewModel.class);

        tareasViewModel.getListaTareasLiveData().observe(getViewLifecycleOwner(), lista -> {
            ListaTareasAdapter adapter = new ListaTareasAdapter(lista);
            binding.RecyclerListaTareas.setAdapter(adapter);
        });

        binding.floatingActionButton.setOnClickListener(v -> {
            androidx.navigation.Navigation.findNavController(view)
                    .navigate(R.id.action_listaTareas_to_fragmentCrear);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tareasViewModel.eliminarTarea(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(binding.RecyclerListaTareas);
    }



}
