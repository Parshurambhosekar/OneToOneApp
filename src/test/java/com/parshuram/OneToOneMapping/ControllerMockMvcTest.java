package com.parshuram.OneToOneMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parshuram.OneToOneMapping.controller.PersonController;
import com.parshuram.OneToOneMapping.entity.Person;
import com.parshuram.OneToOneMapping.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = {ControllerMockMvcTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.parshuram.OneToOneMapping")
public class ControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc; //predefined class for checking the URL

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;


    @BeforeEach
    public void setUp(){//this method execute before test method started..

        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void test_getAllPerson() throws Exception{
        //we can call here the request url and it will choose the particular method..

        List<Person> personList=new ArrayList<Person>();
        personList.add(new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com"));
        personList.add(new Person(2,"Sunita","Sangli","Fe-Male",9623771775l,"s@gmail.com"));

        when(personService.getAllPerson()).thenReturn(personList);//mocking.

        //so we don't call the controller method and here we can pass url......to identify the method..

        this.mockMvc.perform(get("/person/"))//its executes types of request...
                .andExpect(status().isOk())//status code we give
                .andDo(print());
    }

    @Test
    public void test_getPersonById() throws Exception{

        Integer id=1;

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personService.getPersonById(id)).thenReturn(person);

        this.mockMvc.perform(get("/person/{personId}",id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".name").value("Parshuram"))
                .andExpect(MockMvcResultMatchers.jsonPath(".city").value("Sangli"))
                .andExpect(MockMvcResultMatchers.jsonPath(".gender").value("Male"))
                .andExpect(MockMvcResultMatchers.jsonPath(".mobileNumber").value(9623771726l))
                .andExpect(MockMvcResultMatchers.jsonPath(".email").value("p@gmail.com"))
                .andDo(print());
    }

}
