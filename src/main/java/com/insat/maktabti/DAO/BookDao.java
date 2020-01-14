package com.insat.maktabti.DAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.insat.maktabti.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {
    Book findById(int id);
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAllByType(String type, Pageable pageable);
    long count();
    Page<Book> findAllByGenre(String genre, Pageable pageable);
    Page<Book> findAllByGenreAndType(String genre, String type, Pageable pageable);
    Page<Book> findAllByUserIdAndType(Long UserId,String type, Pageable pageable);
    Page<Book> findAllByTypeAndUserIdNot(String type,Long UserId, Pageable pageable);
}
