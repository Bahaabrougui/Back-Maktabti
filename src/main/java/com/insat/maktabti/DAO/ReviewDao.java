package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao extends JpaRepository<Review,Integer> {
    Review findById(int id);
}
