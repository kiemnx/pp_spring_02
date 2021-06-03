package vn.plusplus.spring.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddBookRequest {
    private String title;
    private String author;
    private String avatar;
    private Integer categoryId;
}
