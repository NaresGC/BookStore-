package com.naresh.BookStore.repository;

import com.naresh.BookStore.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookJpaRepository extends JpaRepository<Books, Long> {

    Books findByTitle(String title);
    Books findOneById(Long id);
    @Query("SELECT b FROM Books b where b.price > 100")
    List<Books> findByPrice(Float price);
}
