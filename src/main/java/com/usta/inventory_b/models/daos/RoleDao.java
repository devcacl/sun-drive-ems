package com.usta.inventory_b.models.daos;

import com.usta.inventory_b.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByIdRol(Long idRol);
}
