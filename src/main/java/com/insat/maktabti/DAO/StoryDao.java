package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.Story;
import com.insat.maktabti.domain.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoryDao extends JpaRepository<Story,Integer> {
    Story findById(int id);
    Story findByName(String name);
    Page<Story> findAllByStatus(String status, Pageable pageable);
    Page<Story> findAllByStatusAndOwnerId(String status,Long UserId, Pageable pageable);
    Page<Story> findAllByStatusAndOwnerIdNot(String status,Long UserId, Pageable pageable);

}
