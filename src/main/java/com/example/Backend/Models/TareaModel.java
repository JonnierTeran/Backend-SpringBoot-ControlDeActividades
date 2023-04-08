package com.example.Backend.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * TareaModel
 */

@Entity
@Table(name = "Tarea")
public class TareaModel {

    @Id // Indicamos que es un id  primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera automaticamente y Es Autoincrementable
    @Column(unique = true, nullable = false) // Es unico  y no nullo
    private Long id;

    @Column(nullable = false  , columnDefinition = "VARCHAR(250) ") //  no nullo , y tamaño maximo de 255 bits
    private String titulo;

    @Column(nullable = false  , columnDefinition = "VARCHAR(250) " ) //  no nullo , y tamaño maximo de 255 bits
    @JsonProperty("descripcion")
    private String descripcion;

    @Column(nullable = false  , columnDefinition = "VARCHAR(250) DEFAULT 'Pendiente' ") //  no nullo , y tamaño maximo de 255 bits
    private String estado;

    @ManyToOne // Relacion de muchos a uno
    @JoinColumn(nullable = false, name="id_user") //  no nullo 
    private UserModel id_user;
     
    public TareaModel(){}
    
    public void setId(Long id){
        this.id= id;
    }
    public Long getId(){
        return this.id;
    }

    public void setTitulo(String Titulo){
        this.titulo = Titulo;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public void setDescripcion(String Descripcion){
        this.descripcion = Descripcion;
    }

    public String GetDrescString(){
        return this.descripcion;
    }

    public void setEstdado(String Estado){
        this.estado = Estado;
    }

    public String getEstado(){
        return this.estado;
    }

    public void setId_user(UserModel id_user){
        this.id_user = id_user;
    }

    public UserModel getId_user(){
        return this.id_user;
    }

   

    
}