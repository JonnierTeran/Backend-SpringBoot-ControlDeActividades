package com.example.Backend.Models;





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * UserModel
 */
@Entity // Indicador de que es un modelo Real para una Base de datos
@Table(name = "Usuario") // Indicamos el nombre que queremos para la tabla en la bd para que no use el de la clase
public class UserModel {
    //Propiedades de la entidas / clase

    @Id // Indicamos que es un id  primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera automaticamente y Es Autoincrementable
    @Column(unique = true, nullable = false) // Es unico  y no nullo
    private Long id;

    @Column(nullable = false  , columnDefinition = "VARCHAR(250) ") //  no nullo , y tamaño maximo de 255 bits
    private String nombres;

    @Column(nullable = false , columnDefinition = "VARCHAR(250) ") //  no nullo , y tamaño maximo de 255 bits
    private String apellidos;

    @Column(nullable = false,  columnDefinition = "VARCHAR(250) ") //  no nullo , y tamaño maximo de 255 bits
    private String email;

    @Column(nullable = false ,  columnDefinition = "VARCHAR(250) ") //  no nullo , y tamaño maximo de 255 bits 
    private String contraseña;




    
    public UserModel(){}

    //Sett y gett
    public void setId(Long Id){
        this.id = Id;
    }

    public Long getId(){
        return this.id;
    }

    public void setNombres(String nombres){
        this.nombres = nombres;
    }

    public String getNombres(){
        return this.nombres;
    }
    
    public void setApellidos(String Apellidos){
        this.apellidos = Apellidos;
    }

    public String getApellidos(){
        return this.apellidos;
    }

    public void setEmail(String Email){
        this.email = Email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setContraseña(String Contraseña){
        this.contraseña = Contraseña;
    }

    public String getContraseña(){
        return this.contraseña;
    }

  
  
}


   