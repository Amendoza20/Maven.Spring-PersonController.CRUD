package io.zipcoder.crudapp.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.zipcoder.crudapp.model.Person;
import io.zipcoder.crudapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    @Autowired
    //but also we dont need this?
    private PersonService service;
    public PersonController (PersonService service){
        this.service = service;
    }
    @PostMapping("/person")
    public ResponseEntity<Person> create(@Valid @RequestBody Person person){
        return new ResponseEntity<Person>(service.create(person), HttpStatus.CREATED);
    }

    @PostMapping("/person/{id}")
    public @ResponseBody ResponseEntity<Person> getPerson(@PathVariable Long id){
        return new ResponseEntity<Person>(service.show(id), HttpStatus.FOUND);
    }

    // create List method
    @PutMapping("/person/update")
    public ResponseEntity<Person> updatePerson(Long id, Person person){
        return new ResponseEntity<Person>(service.update(id, person), HttpStatus.OK);
    }
    @GetMapping("/person")
    public ResponseEntity<Iterable<Person>> index(){
        return new ResponseEntity<>(service.index(), HttpStatus.OK);
    }
    @DeleteMapping("/person/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}
