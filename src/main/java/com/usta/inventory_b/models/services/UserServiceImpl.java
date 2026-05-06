package com.usta.inventory_b.models.services;

import com.usta.inventory_b.entities.UserEntity;
import com.usta.inventory_b.models.daos.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserEntity> listAllUsers() {
        return userDao.findAll();
    }

    @Override
    public UserEntity findByCedula(String cedula) {
        return userDao.findById(cedula)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userDao.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}
