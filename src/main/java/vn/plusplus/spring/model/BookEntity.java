package vn.plusplus.spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookEntity {
    private String title;
    private String author;
    private String avatar;

    public BookEntity(String title, String author, String avatar) {
        this.title = title;
        this.author = author;
        this.avatar = avatar;
    }
}
