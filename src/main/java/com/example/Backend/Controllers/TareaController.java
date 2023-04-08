package com.example.Backend.Controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.Models.TareaModel;
import com.example.Backend.Services.TareaService;


@RestController
@RequestMapping(path = "/task")
public class TareaController {

    @Autowired
    TareaService TareaServices;


    //Obtener todas las tareas
    @GetMapping(path = "/Registros")
    public ResponseEntity<List<TareaModel>> obtenerTodasLasTareas() {
        List<TareaModel> tareas = TareaServices.obtenerTodasLasTareas();
        return new ResponseEntity<>(tareas, HttpStatus.OK);
    }

    //Registrar una tarea
    @PostMapping(path="/Registro")
    public ResponseEntity<String> RegistrarTask(@RequestBody TareaModel Tarea){
        if(Tarea != null){
            this.TareaServices.AddTarea(Tarea);
            return new ResponseEntity<>("Proceso Exitoso", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().body("La tarea no cumple con los Requerimientos minimos");
        }
    }


    @GetMapping("Details/{id}")
    public ResponseEntity<TareaModel> getById(@PathVariable Long id) {
        Optional<TareaModel> task = this.TareaServices.GetTask(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(task.get() , HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        //Eliminar Por id
    //Metodo http Delete y su ruta de acceso con variales por url
    @DeleteMapping(path ="/Delete/{id}") // Se define la variable
    public ResponseEntity<String> Registrat(@PathVariable("id") Long id){ //Parametrizacion de la variable por url
        if(this.TareaServices.DeleteTask(id)){  // Ejecucuon y Validacion
            return new ResponseEntity<>("Tarea Eliminada con exito" , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Proceso incompleto, no se elimino una tarea" , HttpStatus.OK);
    }
        
    }


    @GetMapping("Details/taskUser/{id}")
    public ResponseEntity<List<TareaModel>> getByUser(@PathVariable Long id) {
        if (id != null) {
            this.TareaServices.TaskByUser(id);
            return new ResponseEntity<>(this.TareaServices.TaskByUser(id) , HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("Details/Pendiente/{id}")
    public ResponseEntity<List<TareaModel>> getByPendiente(@PathVariable Long id) {
        if (id != null) {
            this.TareaServices.TaskByPendiente(id);
            return new ResponseEntity<>(this.TareaServices.TaskByPendiente(id) , HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("Details/Completada/{id}")
    public ResponseEntity<List<TareaModel>> getByCompletada(@PathVariable Long id) {
        if (id != null) {
            this.TareaServices.TaskByCompletada(id);
            return new ResponseEntity<>(this.TareaServices.TaskByCompletada(id) , HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}
