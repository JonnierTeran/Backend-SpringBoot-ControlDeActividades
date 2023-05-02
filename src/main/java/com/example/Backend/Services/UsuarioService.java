package com.example.Backend.Services;

// import de las estrucutras de datos
import java.util.ArrayList;
import java.util.Optional;

//AutoInstancia
import org.springframework.beans.factory.annotation.Autowired;

//Notacion para el servicio
import org.springframework.stereotype.Service;

//Modelo de datos
import com.example.Backend.Models.UserModel;

//Repositorio del user
import com.example.Backend.Repositories.UsuarioRepositoy;

//Notacion del servicio
@Service
public class UsuarioService {

    //Instancia automatica
    @Autowired
    UsuarioRepositoy UserRepository;


    //Obtiene usuarios por medio del repositorio
    public ArrayList<UserModel> GetUsers(){
        return (ArrayList<UserModel>) this.UserRepository.findAll();
    }

    //Registra usuarios por medio del repositorio
    public void RegUser(UserModel User){
        this.UserRepository.save(User);
    }

    //Elimina usuario por id por medio del repositorio
    public Boolean DeleteUser(Long id){
        try { // Validaciones
            this.UserRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }

    }

    //Busca usuarios por email por medio del repositorio
    public Optional<UserModel> UserByEmail(String Correo){
        return this.UserRepository.findByEmail(Correo); 
    }

    //Actualizar informacion de un usuario
    public void ActualizarUsuario(UserModel User){
        this.UserRepository.ActualizarUser(User);
    }

    //Actualizar Contraeña de un usuario
    public void ActualizarPassword(String Contraseña, Long Id){
        this.UserRepository.ActualizarUserPass(Contraseña,Id);
    }

}
