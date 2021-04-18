package vn.plusplus.spring.designpattern.builder;

public class UserBuilderConcreate implements UserBuilder {
    private String name;
    private String email;
    private Integer age;

    @Override
    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UserBuilder setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public User build() {
        return new User(name, email, age);
    }
}
