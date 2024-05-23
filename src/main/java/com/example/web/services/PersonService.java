package com.example.web.services;

import com.example.web.models.Category;
import com.example.web.models.Person;
import com.example.web.repositories.CategoryRepository;
import com.example.web.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class  PersonService {
    private final PersonRepository personRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    public void admin() {
        Person person = new Person();
        person.setId(1);
        person.setLogin("admin@gmail.com");
        person.setPassword(passwordEncoder.encode("admin"));
        person.setRole("ROLE_ADMIN");
        personRepository.save(person);
    }

    @PostConstruct
    public void category() {
        Category category1 = new Category();
        category1.setName("Food");

        Category category2 = new Category();
        category2.setName("Toys");

        Category category3 = new Category();
        category3.setName("Accessories");

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
    }

    public Person getPersonFindByLogin(Person person){
        Optional<Person> person_db = personRepository.findByLogin(person.getLogin());
        return person_db.orElse(null);
    }

    @Transactional
    public void register(Person person){

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }
}
