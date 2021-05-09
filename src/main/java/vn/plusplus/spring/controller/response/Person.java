package vn.plusplus.spring.controller.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;

    public Person(Integer id, String firstName, String lastName, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }
}
