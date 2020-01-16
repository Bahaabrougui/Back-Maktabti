package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.Chapter;
import com.insat.maktabti.domain.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ChapterDao extends JpaRepository<Chapter,Integer> {
    Chapter findById(long id);
    Page<Chapter> findAllByStoryId(int id, Pageable pageable);
    int countAllByStoryId(int id);
    Chapter findByStoryIdAndAndNumero(int id, int numero);
}
