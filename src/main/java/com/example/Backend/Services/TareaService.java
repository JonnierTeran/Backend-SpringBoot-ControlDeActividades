package com.example.Backend.Services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.Models.TareaModel;
import com.example.Backend.Repositories.TareaRepository;

@Service
public class TareaService {
    
    @Autowired
    TareaRepository TareaRepository;


    //Registrar una Tarea
    public void AddTarea(TareaModel Tarea){
        this.TareaRepository.save(Tarea);
    }

    //VerTodas Las Tareas
    public List<TareaModel> obtenerTodasLasTareas() {
        return this.TareaRepository.findAll();
    }

    //Ver Tarea por id
    public Optional<TareaModel> GetTask(Long id) {
        return this.TareaRepository.findById(id);
    }


      //Elimina usuario por id por medio del repositorio
      public Boolean DeleteTask(Long id){
        try { // Validaciones
            this.TareaRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }

    }


    public List<TareaModel> TaskByUser(Long id){
        return this.TareaRepository.findByUserId(id);
    }


    public List<TareaModel> TaskByPendiente(Long id){
        return this.TareaRepository.findByTaskPendiente(id);
    }

    public List<TareaModel> TaskByCompletada(Long id){
        return this.TareaRepository.findByTaskCompletada(id);
    }
}
