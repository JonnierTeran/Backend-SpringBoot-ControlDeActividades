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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Import de la entidad de Datos
import com.example.Backend.Models.UserModel;

//import del serivico de users
import com.example.Backend.Services.UsuarioService;

//Notacion de Controlador y ruta de acceso
@RestController
@RequestMapping(path = "/Users")
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
    

    //Registrar / Actualizar Usuarios 
    //Metodo http Post
    @PostMapping(path = "/Registrar")
    public ResponseEntity<String> Registrat(@RequestBody UserModel User){  //Recibe un cuerpo por parametro
        if(User != null){  //Validaciones 
            this.usuarioService.RegUser(User);  //Metodo del servicio 
            return new ResponseEntity<>("Accion ejecutada exitosamente" , HttpStatus.OK) ;
        }else{
            return new ResponseEntity<>("Proceso incompleto, no se cumplen los requisitos para la solicitud" , HttpStatus.OK);
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
}
