package com.example.demo;

import com.example.demo.entities.Person;
import com.example.demo.entities.PersonRepository;
import com.example.demo.exceptions.PersonNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class Controller {

    private final PersonRepository repository;

    Controller(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/person/get/{id}")
    public EntityModel<Person> one(@PathVariable Long id) {
        Person person = repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return EntityModel.of(person,
                linkTo(methodOn(Controller.class).one(id)).withSelfRel(),
                linkTo(methodOn(Controller.class).all()).withRel("persons"));
    }

    @GetMapping("/persons")
    public CollectionModel<EntityModel<Person>> all() {
        List<EntityModel<Person>> persons = repository.findAll().stream().map(
                person -> EntityModel.of(person, linkTo(methodOn(Controller.class).all())
                        .withRel("persons"))).collect(Collectors.toList());
        return CollectionModel.of(persons, linkTo(methodOn(Controller.class).all()).withSelfRel());
    }

    @PutMapping("/person/put")
    public Person update(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }
}
