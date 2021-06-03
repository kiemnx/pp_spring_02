package vn.plusplus.spring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.spring.entity.BookEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    /*SELECT * FROM book WHERE category_id= ;*/
    List<BookEntity> findAllByCategoryId(Integer cateId, Pageable pageable);
    List<BookEntity> findAllByAuthor(String author);

    List<BookEntity> findAllByCategoryIdAndAuthor(Integer cateId, String author);

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE id=?1")
    BookEntity findByNativeQuery(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE id=:Id")
    // id = "1 or 1=1; delete from book;"
    // SELECT * FROM book WHERE id=1 or 1=1; delete from book;
    BookEntity findByNameParam(@Param("Id") Integer id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE book SET title = ?1 WHERE id = ?2")
    int updateBookByNativeModify(String title, Integer id);

    @Query(nativeQuery = true, value = "SELECT id, title FROM book")
    List<Object[]> getIdAndTitle();
}
