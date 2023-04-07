package com.example.Backend.Repositories;

//Tipo de estructura de datos
import java.util.Optional;

//Clase de Jpa Repositorio para obtener metodos de JPA
import org.springframework.data.jpa.repository.JpaRepository;

//Notacion para el repositorio
import org.springframework.stereotype.Repository;

//Modelo de datos
import com.example.Backend.Models.UserModel;

//Notacion para el repositorio
@Repository
public interface UsuarioRepositoy extends JpaRepository<UserModel, Long>{ //Hereda metodos del repositorio  e indica la entidad y el tipo de dato de la primary key
    
    //Metodo para filtrar por Email, del repositorio
    public abstract Optional<UserModel> findByEmail(String Email);
}
