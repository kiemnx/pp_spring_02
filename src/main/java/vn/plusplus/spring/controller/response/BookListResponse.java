package vn.plusplus.spring.controller.response;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.spring.model.BookEntity;

import java.util.List;

@Getter @Setter
public class BookListResponse {
    private Integer code;
    private String message;
    private List<BookEntity> data;
}
