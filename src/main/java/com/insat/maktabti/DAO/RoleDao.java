package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {
    Role findById(int id);
}
