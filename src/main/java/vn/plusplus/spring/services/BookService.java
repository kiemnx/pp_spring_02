package vn.plusplus.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.plusplus.spring.controller.request.AddBookRequest;
import vn.plusplus.spring.controller.request.UpdateBookRequest;
import vn.plusplus.spring.controller.response.BookListResponse;
import vn.plusplus.spring.entity.BookEntity;
import vn.plusplus.spring.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    /*@Autowired
    Connection connection;*/

    @Autowired
    BookRepository bookRepository;

    public BookListResponse getBookByCategoryID(Integer cateId, String order, String orderBy, Integer pageNum){

        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);
        if(order.equals("DESC")){
            sort = Sort.by(Sort.Direction.DESC, orderBy);
        }
        PageRequest pageRequest = PageRequest.of(pageNum, 2, sort);

        BookListResponse response = new BookListResponse();
        List<BookEntity> data = bookRepository.findAllByCategoryId(cateId, pageRequest);
        response.setData(data);
        response.setCode(200);
        response.setMessage("Success");
        return response;
    }

    public BookEntity addNewBook(BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }

    public List<BookEntity> addListBook(List<AddBookRequest> requests){
        List<BookEntity> entities = new ArrayList<>();
        for(AddBookRequest e : requests){
            BookEntity item = new BookEntity();
            item.setTitle(e.getTitle());
            item.setAuthor(e.getAuthor());
            item.setAvatar(e.getAvatar());
            item.setCategoryId(e.getCategoryId());
            entities.add(item);
        }
        entities = bookRepository.saveAll(entities);
        return entities;
    }

    public BookEntity updateBook(UpdateBookRequest request){
        BookEntity entity = bookRepository.findById(request.getId()).orElse(null);
        if(entity == null){
            System.out.println("Not existed ID: " + request.getId());
            return null;
        }
        entity.setTitle(request.getTitle());
        entity.setAvatar(request.getAvatar());
        entity.setAuthor(request.getAuthor());
        entity = bookRepository.save(entity);
        return entity;
    }
    public String deleteBook(Integer id){
        BookEntity entity = bookRepository.findById(id).orElse(null);
        if(entity == null){
            System.out.println("Not existed ID: " + id);
            return null;
        }
        bookRepository.delete(entity);
        return "OK";
    }

    @Transactional
    public String updateBook(String title, Integer id){
        BookEntity entity = bookRepository.findById(id).orElse(null);
        if(entity == null){
            System.out.println("Not existed ID: " + id);
            return null;
        }
        bookRepository.updateBookByNativeModify(title, id);
        /*if(true){
            throw new RuntimeException("e");
        }*/
        return "OK";
    }

    public void getIdAndTitle(){
        List<Object[]> objects = bookRepository.getIdAndTitle();
        for(Object[] ob : objects){
            Integer id = (Integer) ob[0];
            String title = String.valueOf(ob[1]);
        }
    }

    @Async
    public void testAsync(){
        System.out.println(Thread.currentThread().getName() + " execute on: " + System.currentTimeMillis()/1000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " execute on: " + System.currentTimeMillis()/1000);
    }
}


