package com.example.demo;

import com.example.demo.entities.Person;
import com.example.demo.entities.PersonModelAssembler;
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
    private final PersonModelAssembler assembler;

    Controller(PersonRepository repository, PersonModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/person/get/{id}")
    public EntityModel<Person> one(@PathVariable Long id) {
        Person person = repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return assembler.toModel(person);
    }

    @GetMapping("/persons")
    public CollectionModel<EntityModel<Person>> all() {
        List<EntityModel<Person>> persons = repository.findAll().stream().map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(persons, linkTo(methodOn(Controller.class).all()).withSelfRel());
    }

    @PutMapping("/person/put")
    public Person update(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }
}
