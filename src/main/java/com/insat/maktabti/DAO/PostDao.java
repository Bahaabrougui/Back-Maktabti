package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post,Integer> {
    Post findById(int id);
}
