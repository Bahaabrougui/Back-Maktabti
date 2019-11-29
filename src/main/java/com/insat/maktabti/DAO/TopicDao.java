package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicDao extends JpaRepository<Topic,Integer> {
    Topic findById(int id);
}
