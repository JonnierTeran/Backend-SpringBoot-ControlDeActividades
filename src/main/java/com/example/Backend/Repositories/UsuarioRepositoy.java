package com.example.Backend.Repositories;

//Tipo de estructura de datos
import java.util.Optional;

//Clase de Jpa Repositorio para obtener metodos de JPA
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//Notacion para el repositorio
import org.springframework.stereotype.Repository;

//Modelo de datos
import com.example.Backend.Models.UserModel;

import jakarta.transaction.Transactional;

//Notacion para el repositorio
@Repository
public interface UsuarioRepositoy extends JpaRepository<UserModel, Long>{ //Hereda metodos del repositorio  e indica la entidad y el tipo de dato de la primary key
    
    //Metodo para filtrar por Email, del repositorio
    public abstract Optional<UserModel> findByEmail(String Email);

  
    //metodo para Actualizar La informacion personal de un usuario
    @Modifying
    @Transactional
    @Query("UPDATE UserModel u SET u.nombres = :#{#User.nombres}, u.apellidos = :#{#User.apellidos} WHERE u.id = :#{#User.id}")
    public abstract void ActualizarUser(@Param("User") UserModel User);

    //metodo para Actualizar La Contraseña  de un usuario
    @Modifying
    @Transactional
    @Query("UPDATE UserModel u SET u.contraseña = :contraseña WHERE u.id = :id")
    public abstract void ActualizarUserPass(@Param("contraseña") String contraseña, @Param("id") Long id);

   

    
}
