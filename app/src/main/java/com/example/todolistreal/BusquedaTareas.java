package com.example.todolistreal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistreal.databinding.FragmentBusquedaTareasBinding;


public class BusquedaTareas extends Fragment {

    FragmentBusquedaTareasBinding binding;
    TareasViewModel tareasViewModel;

    public BusquedaTareas() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBusquedaTareasBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tareasViewModel = new ViewModelProvider(requireActivity()).get(TareasViewModel.class);

        tareasViewModel.getNombreBuscado().observe(getViewLifecycleOwner(), nombre -> {
            binding.nombreBuscado.setText(nombre);
        });

        tareasViewModel.getDescripcionBuscado().observe(getViewLifecycleOwner(), departamento -> {
            binding.descripcionBuscada.setText(departamento);
        });

        binding.botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tareasViewModel.BuscarTarea(binding.textoNombre.getText().toString());
            }
        });

        tareasViewModel.getErrorBusquedaLiveData().observe(getViewLifecycleOwner(), error -> {
            binding.textoNombre.setError(error);
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.textoNombre.setText("");
        binding.textoNombre.setError(null);
        binding.nombreBuscado.setText("");
        binding.descripcionBuscada.setText("");
    }
}


