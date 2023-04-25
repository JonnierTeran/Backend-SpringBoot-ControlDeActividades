package com.example.Backend.Services;


//Tipos de datos
import java.util.List;
import java.util.Optional;

//Generados de instancias Automaticas
import org.springframework.beans.factory.annotation.Autowired;

//Declaracion del servicio
import org.springframework.stereotype.Service;
//Modelo de datos
import com.example.Backend.Models.TareaModel;

//Repositorio
import com.example.Backend.Repositories.TareaRepository;

@Service //Notacion del servicio
public class TareaService {
    
    //Se genera una instancia del objeto
    @Autowired
    TareaRepository TareaRepository;


    //Registrar una Tarea por medio del repositorio
    public void AddTarea(TareaModel Tarea){
        this.TareaRepository.save(Tarea);
    }

    
    //VerTodas Las Tareas  por medio del repositorio
    public List<TareaModel> obtenerTodasLasTareas() {
        return this.TareaRepository.findAll();
    }

    //Ver Tarea por id por medio del repositorio
    public Optional<TareaModel> GetTask(Long id) {
        return this.TareaRepository.findById(id);
    }


      //Elimina una tarea por id por medio del repositorio
      public Boolean DeleteTask(Long id){
        try { // Validaciones
            this.TareaRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }

    }


    //Ver Tareas por usuario
    public List<TareaModel> TaskByUser(Long id){
        return this.TareaRepository.findByUserId(id);
    }

    // Ver tareas pendientes por usuario
    public List<TareaModel> TaskByPendiente(Long id){
        return this.TareaRepository.findByTaskPendiente(id);
    }

    //Ver tareas completadas por usuario
    public List<TareaModel> TaskByCompletada(Long id){
        return this.TareaRepository.findByTaskCompletada(id);
    }

    //Ver Cantidad De tareas pendientes por usuario
    public Long CantidadPendientes(Long id){
        return this.TareaRepository.findByUserPendiente(id);
    }

    //Ver cantidad de tareas Completadas por usuario
    public Long CantidadCompletada(Long id){
        return this.TareaRepository.findByUserCompletada(id);
    }

    //Cantidad total de tareas Registradas
    public Long Registros(Long Id){
        return this.TareaRepository.findByUserTaks(Id);
    }

    //Actualizar Estado de tarea a Completada 
    public void ActualizarEstadoCompletada(Long id){
        this.TareaRepository.ActualizarEstadoCompletado(id);
    }

    //Actualizar Estado de tarea a Pendiente 
    public void ActualizarAPendiente(Long id){
        this.TareaRepository.ActualizarEstadoPendiente(id);
    }

    
}
