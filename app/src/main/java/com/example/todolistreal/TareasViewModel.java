package com.example.todolistreal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TareasViewModel extends ViewModel {

    private final ListaTareas listaTareas;
    private final MutableLiveData<List<Tarea>> listaTareasLiveData;
    private final MutableLiveData<String> nombreBuscado;
    private final MutableLiveData<String> descripcionBuscado;
    private final MutableLiveData<String> errorBusquedaLiveData;

    public TareasViewModel() {
        listaTareas = new ListaTareas();
        listaTareasLiveData = new MutableLiveData<>();
        nombreBuscado = new MutableLiveData<>();
        descripcionBuscado = new MutableLiveData<>();
        errorBusquedaLiveData = new MutableLiveData<>(null);
    }

    public LiveData<List<Tarea>> getListaTareasLiveData() {
        listaTareasLiveData.setValue(listaTareas.getListaTareas());
        return listaTareasLiveData;
    }

    public LiveData<String> getDescripcionBuscado() {
        return descripcionBuscado;
    }

    public LiveData<String> getNombreBuscado() {
        return nombreBuscado;
    }

    public LiveData<String> getErrorBusquedaLiveData() {
        return errorBusquedaLiveData;
    }

    public void BuscarTarea(String nombre) {
        if (nombre.isEmpty()) {
            errorBusquedaLiveData.setValue("Es necesario indicar un nombre");
        } else {
            Tarea tarea = listaTareas.getTarea(nombre);
            if (tarea != null) {
                nombreBuscado.postValue(tarea.getNombre());
                descripcionBuscado.postValue(tarea.getDescripcion().toString());
            } else {
                errorBusquedaLiveData.postValue("La tarea no existe");
            }
        }
    }

    public void eliminarTarea(int posicion) {
        Tarea t = listaTareasLiveData.getValue().get(posicion);
        listaTareas.removeTarea(t);
        listaTareasLiveData.setValue(listaTareas.getListaTareas());
    }

    public void agregarTarea(Tarea nuevaTarea) {
        listaTareas.addTarea(nuevaTarea);
        listaTareasLiveData.setValue(listaTareas.getListaTareas());
    }

}
