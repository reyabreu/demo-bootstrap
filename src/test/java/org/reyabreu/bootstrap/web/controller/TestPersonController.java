package org.reyabreu.bootstrap.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reyabreu.bootstrap.persistence.model.Person;
import org.reyabreu.bootstrap.persistence.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class, excludeAutoConfiguration = {LiquibaseAutoConfiguration.class})
@AutoConfigureDataJpa
public class TestPersonController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void findAll_ValidCall_ReturnsList() throws Exception {
        given(personRepository.findAll())
                .willReturn(singletonList(Person.builder()
                        .id(100)
                        .firstName(randomAlphabetic(10))
                        .lastName("Michaels")
                        .build()));
        mvc.perform(get("/api/persons").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(100)))
                .andExpect(jsonPath("$[0].lastName", is("Michaels")));
    }

    @Test
    public void findByLastName_ValidLastName_ReturnsList() throws Exception {
        given(personRepository.findByLastName("Alpha"))
                .willReturn(singletonList(Person.builder()
                        .id(120)
                        .firstName(randomAlphabetic(10))
                        .lastName("Alpha")
                        .build()));

        mvc.perform(get("/api/persons/Alpha").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(120)))
                .andExpect(jsonPath("$[0].lastName", is("Alpha")));
    }

    @Test
    public void create_ValidRequest_NewPerson() throws Exception {
        given(personRepository.save(Person.builder()
                .firstName("Chaka")
                .lastName("Zulu")
                .build()))
                .willReturn(Person.builder()
                        .id(130)
                        .firstName("Chaka")
                        .lastName("Zulu")
                        .build());

        mvc.perform(post("/api/persons")
                .content("{ \"firstName\" : \"Chaka\", \"lastName\" : \"Zulu\" }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(130)))
                .andExpect(jsonPath("$.firstName", is("Chaka")))
                .andExpect(jsonPath("$.lastName", is("Zulu")));
    }

}