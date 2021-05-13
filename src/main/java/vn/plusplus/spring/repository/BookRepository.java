package vn.plusplus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.spring.entity.BookEntity;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    /*SELECT * FROM book WHERE category_id= ;*/
    List<BookEntity> findAllByCategoryId(Integer cateId);
    List<BookEntity> findAllByAuthor(String author);

    List<BookEntity> findAllByCategoryIdAndAuthor(Integer cateId, String author);

    /*@Query(nativeQuery = true, value = "SELECT * FROM book WHERE")
    List<BookEntity> findByNativeQuery();*/
}
