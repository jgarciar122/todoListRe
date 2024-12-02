package com.example.todolistreal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaTareas {
    List<Tarea> listaTareas;


    public ListaTareas() {
        listaTareas = new ArrayList<>();
        listaTareas.add(new Tarea("Comprar leche", "Ir al supermercado y comprar leche", ""));
        listaTareas.add(new Tarea("Sacar la basura", "Sacar la basura al contenedor", ""));
        listaTareas.add(new Tarea("Estudiar para el examen", "Repasar los apuntes y hacer ejercicios", ""));
        listaTareas.add(new Tarea("Comprar leche", "Ir al supermercado y comprar leche", ""));
        listaTareas.add(new Tarea("Sacar la basura", "Sacar la basura al contenedor", ""));
        listaTareas.add(new Tarea("Estudiar para el examen", "Repasar los apuntes y hacer ejercicios", ""));
        listaTareas.add(new Tarea("Comprar leche", "Ir al supermercado y comprar leche", ""));
        listaTareas.add(new Tarea("Sacar la basura", "Sacar la basura al contenedor", ""));
        listaTareas.add(new Tarea("Estudiar para el examen", "Repasar los apuntes y hacer ejercicios", ""));
        listaTareas.add(new Tarea("Comprar leche", "Ir al supermercado y comprar leche", ""));
        listaTareas.add(new Tarea("Sacar la basura", "Sacar la basura al contenedor", ""));
        listaTareas.add(new Tarea("Estudiar para el examen", "Repasar los apuntes y hacer ejercicios", ""));
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    public boolean agregarTarea(Tarea t) {
        if (listaTareas.contains(t)) return false;
        return listaTareas.add(t);
    }

    public Tarea getTarea(String nombre) {
        Tarea tarea = null;
        for (Tarea t : listaTareas) {
            if (t.getNombre().equals(nombre)) tarea = t;
        }
        return tarea;
    }

    public boolean removeTarea(Tarea t) {
        Iterator<Tarea> iter = listaTareas.iterator();
        while (iter.hasNext()) {
            Tarea tarea = iter.next();
            if (tarea.equals(t)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }
    public void addTarea(Tarea tarea) {
        listaTareas.add(tarea);
    }

    public void editarTarea(Tarea tareaEditada) {
        for (int i = 0; i < listaTareas.size(); i++) {
            if (listaTareas.get(i).getNombre().equals(tareaEditada.getNombre())) {
                listaTareas.set(i, tareaEditada); // Reemplaza la tarea en la lista
                break;
            }
        }
    }


}


