package com.example.todolistreal;

import android.os.Bundle;
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

public class FragmentCrear extends Fragment {

    private Spinner spinnerPrioridad;
    private EditText edittextNombre;
    private EditText edittextDescripcion;
    private Button buttonAdd;
    private TareasViewModel tareasViewModel;

    public FragmentCrear() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tareasViewModel = new ViewModelProvider(requireActivity()).get(TareasViewModel.class); // Asegúrate de esto
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        spinnerPrioridad = view.findViewById(R.id.spinner_prioridad);
        edittextNombre = view.findViewById(R.id.edittext_nombre);
        edittextDescripcion = view.findViewById(R.id.edittext_descripcion);
        buttonAdd = view.findViewById(R.id.button_add);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.prioridad_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridad.setAdapter(adapter);

        buttonAdd.setOnClickListener(v -> {
            String nombre = edittextNombre.getText().toString();
            String descripcion = edittextDescripcion.getText().toString();
            String prioridadString = spinnerPrioridad.getSelectedItem().toString();

            if (nombre.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                Tarea nuevaTarea = new Tarea(nombre, descripcion, prioridadString);

                if (tareasViewModel != null) {
                    tareasViewModel.agregarTarea(nuevaTarea);
                } else {
                    Toast.makeText(getContext(), "Error: ViewModel no encontrado", Toast.LENGTH_SHORT).show();
                }

                edittextNombre.setText("");
                edittextDescripcion.setText("");
                spinnerPrioridad.setSelection(0);

                Toast.makeText(getContext(), "Tarea añadida exitosamente.", Toast.LENGTH_SHORT).show();

                NavController navController = NavHostFragment.findNavController(FragmentCrear.this);
                navController.navigate(R.id.action_fragmentCrear_to_listaTareas);
            }
        });

        return view;
    }
}