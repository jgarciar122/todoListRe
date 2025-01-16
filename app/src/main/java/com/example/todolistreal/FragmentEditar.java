package com.example.todolistreal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class FragmentEditar extends Fragment {

    private Spinner spinnerPrioridad;
    private EditText edittextNombre;
    private EditText edittextDescripcion;
    private Button buttonGuardar;
    private TareasViewModel tareasViewModel;
    private Tarea tareaActual;

    public FragmentEditar() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tareasViewModel = new ViewModelProvider(requireActivity()).get(TareasViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar, container, false);

        spinnerPrioridad = view.findViewById(R.id.spinner_prioridad);
        edittextNombre = view.findViewById(R.id.edittext_nombre);
        edittextDescripcion = view.findViewById(R.id.edittext_descripcion);
        buttonGuardar = view.findViewById(R.id.button_guardar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.prioridad_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridad.setAdapter(adapter);

        if (getArguments() != null) {
            tareaActual = (Tarea) getArguments().getSerializable("tarea");
            if (tareaActual != null) {
                edittextNombre.setText(tareaActual.getNombre());
                edittextDescripcion.setText(tareaActual.getDescripcion());
                spinnerPrioridad.setSelection(adapter.getPosition(tareaActual.getPrioridad()));
            }
        }

        buttonGuardar.setOnClickListener(v -> {
            String nombre = edittextNombre.getText().toString();
            String descripcion = edittextDescripcion.getText().toString();
            String prioridadString = spinnerPrioridad.getSelectedItem().toString();

            if (nombre.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                if (tareaActual != null) {
                    tareaActual.setNombre(nombre);
                    tareaActual.setDescripcion(descripcion);
                    tareaActual.setPrioridad(prioridadString);
                    tareasViewModel.editarTarea(tareaActual);
                } else {
                    Toast.makeText(getContext(), "Error: No se pudo editar la tarea.", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getContext(), "Tarea editada exitosamente.", Toast.LENGTH_SHORT).show();

                NavController navController = NavHostFragment.findNavController(FragmentEditar.this);
                navController.navigate(R.id.action_fragmentEditar_to_listaTareas);
            }
        });

        return view;
    }
}