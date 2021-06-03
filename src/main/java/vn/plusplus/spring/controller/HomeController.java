package vn.plusplus.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.plusplus.spring.controller.response.Person;
import vn.plusplus.spring.controller.response.PersonForm;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private static List<Person> persons = new ArrayList<>();
    static {
        persons.add(new Person(1, "Bill", "Gates", "M"));
        persons.add(new Person(2, "Steve", "Jobs", "M"));
    }

    int currentId = 2;

//    @GetMapping(value = {"/", "/index"})
    public String index(Model model){
        model.addAttribute("message", "Xin chao cac ban");
        return "index";
    }

    @GetMapping(value = "/personList")
    public String personPage(Model model){
        //Truy van database lay lis person
        /*List<Book>
        * List<User>
          List<FavouriteBook>*/
        model.addAttribute("persons", persons);
        return "personList";
    }
    @GetMapping(value = "/addPerson")
    public String showAddPersonPage(Model model){
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return "addPerson";
    }

    @PostMapping(value = "/addPerson")
    public String savePerson(Model model,
                             @ModelAttribute("personForm") PersonForm request){
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String gender = request.getGender();
        currentId ++;
        persons.add(new Person(currentId, firstName, lastName, gender));
        return "redirect:/personList";
    }

    @GetMapping(value = "/person")
    public String getPersonDetail(@RequestParam("id") Integer id){
        Person person = persons.get(id);
        return "index";
    }
}
