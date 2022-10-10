package com.example.demo.entities;

import com.example.demo.Controller;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {
    @Override
    public EntityModel<Person> toModel(Person entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(Controller.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(Controller.class).all()).withRel("persons"));
    }
}
