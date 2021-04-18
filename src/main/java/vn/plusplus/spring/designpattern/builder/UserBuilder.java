package vn.plusplus.spring.designpattern.builder;

public interface UserBuilder {
    UserBuilder setName(String name);
    UserBuilder setEmail(String email);
    UserBuilder setAge(Integer age);
    User build();
}
