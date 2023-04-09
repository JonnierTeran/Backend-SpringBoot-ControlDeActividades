package com.example.Backend.Controllers;


//Estructuras de datos
import java.util.List;
import java.util.Optional;

//notaciones para generar instancias
import org.springframework.beans.factory.annotation.Autowired;

//Notaciones para solicitudes http
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Modelo y servicios de la entidad
import com.example.Backend.Models.TareaModel;
import com.example.Backend.Services.TareaService;


@RestController  //Controlador de servicios Rest
@RequestMapping(path = "/task")  //Ruta http para el controlador
public class TareaController {

    //Objeto del servicio y su instancia
    @Autowired
    TareaService TareaServices;


    //Obtener todas las tareas yn estado http 200
    @GetMapping(path = "/Registros")  //Metodo Get y Ruta de acceso http al metodo
    public ResponseEntity<List<TareaModel>> obtenerTodasLasTareas() {
        List<TareaModel> tareas = TareaServices.obtenerTodasLasTareas();
        return new ResponseEntity<>(tareas, HttpStatus.OK);
    }

    //Registrar una tarea
    @PostMapping(path="/Registro") //metodo post y ruta de acceso al endpoint
    public ResponseEntity<String> RegistrarTask(@RequestBody TareaModel Tarea){
        if(Tarea != null){
            this.TareaServices.AddTarea(Tarea);
            return new ResponseEntity<>("Proceso Exitoso", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().body("La tarea no cumple con los Requerimientos minimos");
        }
    }

    
    //Obtener una tarea por  id
    @GetMapping("Details/{id}") //Metodo get y ruta de acceso al endpoint
    public ResponseEntity<Optional<TareaModel>> getById(@PathVariable Long id) {
        //Optional<TareaModel> task = this.TareaServices.GetTask(id);
        //if (task.isPresent()) {
            return new ResponseEntity<>(this.TareaServices.GetTask(id) , HttpStatus.OK);
        //} else {
          //  return ResponseEntity.notFound().build();
        //}
    }

    //Eliminar Por id registrado 
    //Metodo http Delete y su ruta de acceso con variales por url
    @DeleteMapping(path ="/Delete/{id}") // Se define la variable
    public ResponseEntity<String> Registrat(@PathVariable("id") Long id){ //Parametrizacion de la variable por url
        if(this.TareaServices.DeleteTask(id)){  // Ejecucuon y Validacion
            return new ResponseEntity<>("Tarea Eliminada con exito" , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Proceso incompleto, no se elimino una tarea" , HttpStatus.OK);
    }
        
    }


    //Obtener tareas de cada usuario
    @GetMapping("/Details/taskUser/{id}")
    public ResponseEntity<List<TareaModel>> getByUser(@PathVariable Long id) {
        if (id != null) {
            this.TareaServices.TaskByUser(id);
            return new ResponseEntity<>(this.TareaServices.TaskByUser(id) , HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Tareas pendientes de cada usuario
    @GetMapping("/Details/Pendiente/{id}")
    public ResponseEntity<List<TareaModel>> getByPendiente(@PathVariable Long id) {
        if (id != null) {
            this.TareaServices.TaskByPendiente(id);
            return new ResponseEntity<>(this.TareaServices.TaskByPendiente(id) , HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    //Tareas completadas por cada usuario
    @GetMapping("/Details/Completada/{id}")
    public ResponseEntity<List<TareaModel>> getByCompletada(@PathVariable Long id) {
        if (id != null) {
            this.TareaServices.TaskByCompletada(id);
            return new ResponseEntity<>(this.TareaServices.TaskByCompletada(id) , HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}
