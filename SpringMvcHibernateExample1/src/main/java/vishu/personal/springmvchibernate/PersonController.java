package vishu.personal.springmvchibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vishu.personal.springmvchibernate.model.Person;
import vishu.personal.springmvchibernate.service.PersonService;

@Controller
public class PersonController {
    private PersonService personService;

    @Autowired(required = true)
    @Qualifier(value = "personService")
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String getAllPersons(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", this.personService.getAllPersons());
        return "person";
    }

    //method for both Adding & Updating Person
    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p){
        if(p.getId() == 0){
            // new Person, so add it
            this.personService.addPerson(p);
        }
        else{
            //existing person, so update it
            this.personService.updatePerson(p);
        }
        return "redirect:/persons";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
        this.personService.removePersonById(id);
        return "redirect:/persons";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.getAllPersons());
        return "person";
    }
}
