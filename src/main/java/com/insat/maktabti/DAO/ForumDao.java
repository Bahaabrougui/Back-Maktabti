package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumDao extends JpaRepository<Forum,Integer> {
    Forum findById(int id);
}
