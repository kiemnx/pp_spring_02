package vn.plusplus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.spring.controller.response.BookListResponse;
import vn.plusplus.spring.services.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/{category_id}")
    public BookListResponse getBookByCategoryID(@PathVariable(name = "category_id") Integer cateId,
                                                @RequestParam(name = "order", defaultValue = "ASC") String order,
                                                @RequestParam(name = "orderBy", defaultValue = "title") String orderBy){
        return bookService.getBookByCategoryID(cateId, order, orderBy);
    }
}
