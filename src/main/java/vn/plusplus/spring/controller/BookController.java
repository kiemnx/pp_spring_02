package vn.plusplus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.spring.controller.request.AddBookRequest;
import vn.plusplus.spring.controller.request.UpdateBookRequest;
import vn.plusplus.spring.controller.response.BookListResponse;
import vn.plusplus.spring.entity.BookEntity;
import vn.plusplus.spring.repository.BookRepository;
import vn.plusplus.spring.services.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository repository;

    @GetMapping(value = "/{category_id}")
    public BookListResponse getBookByCategoryID(@PathVariable(name = "category_id") Integer cateId,
                                                @RequestParam(name = "order", defaultValue = "ASC") String order,
                                                @RequestParam(name = "orderBy", defaultValue = "title") String orderBy,
                                                @RequestParam(name = "pageNum") Integer pageNum){
        return bookService.getBookByCategoryID(cateId, order, orderBy, pageNum);
    }

    @PostMapping(value = "/addOne")
    public BookEntity addOneBook(@RequestBody AddBookRequest bookRequest){
        BookEntity entity = new BookEntity();
        entity.setTitle(bookRequest.getTitle());
        entity.setAuthor(bookRequest.getAuthor());
        entity.setAvatar(bookRequest.getAvatar());
        entity.setCategoryId(bookRequest.getCategoryId());
        entity = bookService.addNewBook(entity);
        return entity;
    }

    @PostMapping(value = "/addList")
    public List<BookEntity> addListBook(@RequestBody List<AddBookRequest> requests){
        return bookService.addListBook(requests);
    }

    @PutMapping(value = "/update")
    public BookEntity updateBook(@RequestBody UpdateBookRequest request){
        return bookService.updateBook(request);
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") Integer id){
        return bookService.deleteBook(id);
    }

    @GetMapping(value = "/native-index/{id}")
    public BookEntity getBookByIdNative(@PathVariable(name = "id") Integer id){
        BookEntity entity = repository.findByNameParam(id);
        return entity;
    }

    @PutMapping(value = "/update-title/{id}")
    public String updateNative(@PathVariable(name = "id") Integer id,
                               @RequestParam(value = "title") String title){
        return bookService.updateBook(title, id);
    }

    @GetMapping(value = "/id-title")
    public void getIdAndTitle(){
        bookService.getIdAndTitle();
    }
}
