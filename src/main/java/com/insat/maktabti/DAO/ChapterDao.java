package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterDao extends JpaRepository<Chapter,Integer> {
    Chapter findById(int id);
}
