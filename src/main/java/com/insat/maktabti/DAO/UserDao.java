package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findById(int id);

    User findByUsername(String name);
}
