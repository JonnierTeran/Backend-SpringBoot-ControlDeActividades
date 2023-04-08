package com.example.Backend.Repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Backend.Models.TareaModel;


/**
 * TareaRepository
 */
@Repository
public interface TareaRepository extends JpaRepository<TareaModel, Long> {
    

    @Query("SELECT t FROM TareaModel t WHERE t.id_user.id = :idUsuario")
    List<TareaModel> findByUserId(@Param("idUsuario") Long idUsuario);


    @Query("SELECT t FROM TareaModel t WHERE t.id_user.id = :idUsuario AND t.estado = 'Pendiente' ")
    List<TareaModel> findByTaskPendiente(@Param("idUsuario") Long idUsuario);

    @Query("SELECT t FROM TareaModel t WHERE t.id_user.id = :idUsuario AND t.estado = 'Completada' ")
    List<TareaModel> findByTaskCompletada(@Param("idUsuario") Long idUsuario);

    
}