package com.example.Backend.Controllers;

//Import de Estructura de datos
import java.util.ArrayList;
import java.util.Optional;

//Import De Notaciones para servicios Rest
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.Models.Response;
//Import de la entidad de Datos
import com.example.Backend.Models.UserModel;
//import del serivico de users
import com.example.Backend.Services.UsuarioService;

//Notacion de Controlador y ruta de acceso
@RestController
@RequestMapping(path = "/Users" )
public class UsuarioController {

    //Instancia Automatica
    @Autowired
    UsuarioService usuarioService;

    //Obtener usuarios
    //Metodo http Get y ruta
    @GetMapping(path = "/Registros")
    public ArrayList<UserModel> Obtener(){
       return this.usuarioService.GetUsers();  //Retorna la invocacion al metodo del servicio
    }
    

    //Registrar  Usuarios  Unicos
    //Metodo http Post
    @PostMapping(path = "/Registrar")
    public ResponseEntity<Response> Registrat(@RequestBody UserModel User) {
        if(User != null){
            Optional<UserModel> userExistente = this.usuarioService.UserByEmail(User.getEmail());
            if(userExistente.isPresent()) {
                return  new ResponseEntity<>(new Response("Error, Ya existe un usuario asociado a esta direccion de Email"), HttpStatus.OK);
            }
            this.usuarioService.RegUser(User);
            return  new ResponseEntity<>(new Response("Usuario Registrado Exitosamente"), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(new Response("Error al registrar"), HttpStatus.BAD_REQUEST);
        }
}





    //Eliminar Por id
    //Metodo http Delete y su ruta de acceso con variales por url
    @DeleteMapping(path ="/Delete/{id}") // Se define la variable
    public ResponseEntity<String> Delete(@PathVariable("id") Long id){ //Parametrizacion de la variable por url
        if(this.usuarioService.DeleteUser(id)){  // Ejecucuon y Validacion
            this.usuarioService.DeleteUser(id);
            return  new ResponseEntity<>("Usuario Eliminado Exitosamente", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Proceso incompleto, no se cumplen los requisitos para la solicitud" , HttpStatus.OK);
    }
        
    }


    //Bucar un usuario por email
    //Metodo http Get con su Ruta y variable por url
    @GetMapping(path="/UserEmail/{correo}") // Se define la variable
    public ResponseEntity<Optional<UserModel>> ObtenerPorEmail(@PathVariable("correo") String correo){ //Se recibe la variable por url
        return new ResponseEntity<>(this.usuarioService.UserByEmail(correo), HttpStatus.OK);
    }

    
    //Actualizar Informacion Personal de un usuario
    //Metodo http put para actualizar datos de un usuario
    @PutMapping(path = "/Update")
    public ResponseEntity<Response> ActualizarUser(@RequestBody UserModel User){
        if(User.getNombres() != "" && User.getApellidos() != "" && User.getId() != null){
            this.usuarioService.ActualizarUsuario(User);
            return new ResponseEntity<>(new Response("Usuario Actualizado con exito"), HttpStatus.OK);
        }else{
            return new ResponseEntity<Response>(new Response("Error Al Actualizar Usuario"), HttpStatus.BAD_REQUEST);
        }
    }


    //Actualizar Informacion Personal de un usuario
    //Metodo http put para actualizar datos de un usuario
    @PutMapping(path = "/Update/pass")
    public ResponseEntity<Response> ActualizarUserPass(@RequestBody UserModel User){
        if(User.getNombres() != "" && User.getApellidos() != "" && User.getId() != null && User.getContraseña() != null || User.getContraseña() != ""){
            this.usuarioService.ActualizarPassword(User.getContraseña(), User.getId());
            return new ResponseEntity<>(new Response("Contraseña Actualizada con exito"), HttpStatus.OK);
        }else{
            return new ResponseEntity<Response>(new Response("Error Al Actualizar Contraseña"), HttpStatus.BAD_REQUEST);
        }
    }
}
