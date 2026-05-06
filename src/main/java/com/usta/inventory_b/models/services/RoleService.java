package com.usta.inventory_b.models.services;

import com.usta.inventory_b.entities.RoleEntity;

import java.util.List;

public interface RoleService {

    public List<RoleEntity> listAll();
    public RoleEntity findById(Long id);
    public void save(RoleEntity role);



}

