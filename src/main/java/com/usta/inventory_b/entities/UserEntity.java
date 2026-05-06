package com.usta.inventory_b.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="usuarios")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(max = 15)
    @Column(name="cedula", length = 15)
    private String cedula;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombreUsuario;

    @NotBlank
    @Size(max = 50)
    @Column(name = "apellido_usuario", length = 50, nullable = false)
    private String apellidoUsuario;

    @NotBlank
    @Email
    @Size(max = 150)
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;


    @NotBlank
    @Size(max = 100)
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name="estado_usuario",nullable = false)
    private boolean estadoUsuario=true;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_rol",nullable = false)
    private RoleEntity rol;

    public UserEntity() {}


}
