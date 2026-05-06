package com.usta.inventory_b.models.services;

import com.usta.inventory_b.entities.UserEntity;

import java.util.List;

public interface UserService {

    public List<UserEntity> listAllUsers();
    public UserEntity findByCedula(String cedula);
    public UserEntity save(UserEntity user);
    public UserEntity findByEmail(String email);


}
