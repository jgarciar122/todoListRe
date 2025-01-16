package com.example.todolistreal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaTareas {
    List<Tarea> listaTareas;


    public ListaTareas() {
        listaTareas = new ArrayList<>();
        listaTareas.add(new Tarea("Comprar leche", "Ir al supermercado y comprar leche", "1"));
        listaTareas.add(new Tarea("Sacar la basura", "Sacar la basura al contenedor", "2"));
        listaTareas.add(new Tarea("Estudiar para el examen", "Repasar los apuntes y hacer ejercicios", "3"));
        listaTareas.add(new Tarea("Comprar leche", "Ir al supermercado y comprar leche", "1"));
        listaTareas.add(new Tarea("Sacar la basura", "Sacar la basura al contenedor", "2"));
        listaTareas.add(new Tarea("Estudiar para el examen", "Repasar los apuntes y hacer ejercicios", "3"));
        listaTareas.add(new Tarea("Comprar leche", "Ir al supermercado y comprar leche", "2"));
        listaTareas.add(new Tarea("Sacar la basura", "Sacar la basura al contenedor", "2"));
        listaTareas.add(new Tarea("Estudiar para el examen", "Repasar los apuntes y hacer ejercicios", "1"));
        listaTareas.add(new Tarea("Comprar leche", "Ir al supermercado y comprar leche", "3"));
        listaTareas.add(new Tarea("Sacar la basura", "Sacar la basura al contenedor", "3"));
        listaTareas.add(new Tarea("Estudiar para el examen", "Repasar los apuntes y hacer ejercicios", "1"));
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
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
                listaTareas.set(i, tareaEditada);
                break;
            }
        }
    }


}


