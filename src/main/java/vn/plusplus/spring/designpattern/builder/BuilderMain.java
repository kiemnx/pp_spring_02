package vn.plusplus.spring.designpattern.builder;

public class BuilderMain {
    public static void main(String[] args) {
//        User u = new User("A", "email", 20);

        User user = new UserBuilderConcreate()
                    .setName("A")
                    .build();

//        u.setName("A");
//        u.setEmail("a@gmail.com");
//        u.setAge(20);
    }
}
