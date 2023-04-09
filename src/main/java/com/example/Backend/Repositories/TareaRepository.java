package com.example.Backend.Repositories;


//Libreria para listas
import java.util.List;

//Clase Jpa Repositorio para metodos del crud
import org.springframework.data.jpa.repository.JpaRepository;

//notacion para los query de jpql
import org.springframework.data.jpa.repository.Query;

//Parametros para las query jpql
import org.springframework.data.repository.query.Param;

//Decordaror para  indicar que este sera el repositorio
import org.springframework.stereotype.Repository;

import com.example.Backend.Models.TareaModel;


/**
 * TareaRepository
 */
@Repository  // Se genera una interfaz de repositorio que hereda una clase a la que le indicamos el  modelo a usar y su tipo de dato del id
public interface TareaRepository extends JpaRepository<TareaModel, Long> {
    
    //Consulta jpql para Buscar tareas por usuarios
    @Query("SELECT t FROM TareaModel t WHERE t.id_user.id = :idUsuario")
    public abstract List<TareaModel> findByUserId(@Param("idUsuario") Long idUsuario);


    //Consulta jpql para buscar tareas pendientes para cada usuario
    @Query("SELECT t FROM TareaModel t WHERE t.id_user.id = :idUsuario AND t.estado = 'Pendiente' ")
    public abstract List<TareaModel> findByTaskPendiente(@Param("idUsuario") Long idUsuario);


    //Consulta jpqlpara buscar tareas completadaas por usuario
    @Query("SELECT t FROM TareaModel t WHERE t.id_user.id = :idUsuario AND t.estado = 'Completada' ")
    public abstract List<TareaModel> findByTaskCompletada(@Param("idUsuario") Long idUsuario);

    
}