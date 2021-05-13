package vn.plusplus.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.spring.controller.response.BookListResponse;
import vn.plusplus.spring.entity.BookEntity;
import vn.plusplus.spring.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    /*@Autowired
    Connection connection;*/

    @Autowired
    BookRepository bookRepository;

    public BookListResponse getBookByCategoryID(Integer cateId, String order, String orderBy){

//        Sort sort = new Sort(Sort.Direction.ASC);

        BookListResponse response = new BookListResponse();
        List<BookEntity> data = bookRepository.findAllByCategoryIdAndAuthor(cateId,"NXB Kim Dong");
        response.setData(data);
        response.setCode(200);
        response.setMessage("Success");
        return response;
    }

    public BookEntity addNewBook(BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }
}
