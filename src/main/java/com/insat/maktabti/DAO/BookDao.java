package com.insat.maktabti.DAO;

import com.insat.maktabti.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {
    Book findById(int id);
}
