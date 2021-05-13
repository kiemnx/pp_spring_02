package vn.plusplus.spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "category_id")
    private Integer categoryId;

    /*public BookEntity(String title, String author, String avatar) {
        this.title = title;
        this.author = author;
        this.avatar = avatar;
    }*/
}
