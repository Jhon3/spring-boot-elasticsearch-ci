package ciproject.com.example.controller;

import ciproject.com.example.model.Person;
import ciproject.com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @PostMapping(path = "/savePerson", consumes = "application/json", produces = "application/json")
    public  int savePerson(@RequestBody List<Person> persons) {
        repository.saveAll(persons);
        return persons.size();
    }

    @GetMapping("/findAll")
    public Iterable<Person> findAllPersons(){
        return repository.findAll();
    }

    @GetMapping("/findByName/{name}")
    public List<Person> findByName(@PathVariable String name){
        return repository.findByName(name);
    }

    @PostMapping(path = "/removeById/{id}")
    public void removeById(@PathVariable String id){repository.deleteById(id);}
}
