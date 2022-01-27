package com.example.emrestserver.Service;

import com.example.emrestserver.dao.PersonDao;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    
    @Mock
    private PersonDao personDao;
    
    @InjectMocks
    private PersonService personService;

    private Person person1;
    private Person person2;

    static List<Person> list;
    static{
        list = new ArrayList();
        list.add(Person.builder().id(1).firstname("Jiexi").lastname("Mei").email("meiyating051@gmail.com").build());
        list.add(Person.builder().id(2).firstname("Yating").lastname("Mei").email("jcmei89@gmail.com").build());
        list.add(Person.builder().id(3).firstname("Dongyi").lastname("Cao").email("dongyicao123@gmail.com").build());
        list.add(Person.builder().id(4).firstname("Andi").lastname("Cao").email("andycao123@gmail.com").build());
    }


    @BeforeEach
    public void setup() throws NullPointerException {

        person1 = Person.builder().id(1).lastname("Mei").email("meiyating051@gmail.com").firstname("Jiexi").build();
        person2 = Person.builder().id(2).lastname("NANI").firstname("MASAGA").email("jcmei89@gmail.com").build();
        lenient().when(personDao.findAll()).thenReturn(list);

        lenient().when(personDao.getPersonByEmail("andycao123@gmail.com")).thenReturn(list.get(2));

        lenient().when(personDao.getPersonByEmail("meiyating051@gmail.com")).thenReturn(list.get(0));
        lenient().when(personDao.getPersonByEmail1("meiyating051@gmail.com")).thenReturn(list.get(0));

    }

    @Test
    public void getPersonByEmailTest(){
        assertEquals(list.get(0),personDao.getPersonByEmail("meiyating051@gmail.com") );
        verify(personDao).getPersonByEmail("meiyating051@gmail.com");
    }

    @Test
    @Disabled
    public void updatePersonWithPersonTest(){
        assertEquals(person2,personDao.updatePersonWithPerson(person2));
        verify(personDao).updatePersonWithPerson(person2);
    }

}
