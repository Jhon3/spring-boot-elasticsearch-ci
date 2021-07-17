package ciproject.com.example.controller;

import ciproject.com.example.model.Person;
import ciproject.com.example.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private PersonRepository repository;

    @Before
    public void setUp() {
        Iterator<Person> iterablePerson = repository.findAll().iterator();
        while(iterablePerson.hasNext()){
            repository.deleteById(iterablePerson.next().getId());
        }
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setName("cabral"); person.setEmail("cabral@teste.com"); person.setAge(25);
        persons.add(person);
        repository.saveAll(persons);
    }

    @Test
    public void test01() {
        Person found = repository.findByName("cabral").get(0);
        assertThat(found.getAge()).isEqualTo(25);
    }

    @Test
    public void test02() {
        List<Person> founds = repository.findByName("barbosa");
        assertThat(founds.size()).isEqualTo(0);
    }
}
