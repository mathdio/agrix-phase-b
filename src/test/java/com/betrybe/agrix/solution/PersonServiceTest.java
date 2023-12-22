package com.betrybe.agrix.solution;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PersonServiceTest {

  @MockBean
  private PersonRepository personRepository;

  @Test
  void testGetPersonById() {
    Person mockedPerson = new Person();

    Mockito.when(personRepository.findById(1L))
        .thenReturn(Optional.of(mockedPerson));

    PersonService personService = new PersonService(personRepository);
    Assertions.assertTrue(new ReflectionEquals(new Person())
        .matches(personService.getPersonById(1L)));
  }

  @Test
  void testPersonNotFoundById() {
    Mockito.when(personRepository.findById(999L))
        .thenReturn(Optional.empty());

    PersonService personService = new PersonService(personRepository);
    Assertions.assertThrows(PersonNotFoundException.class, () -> {
      personService.getPersonById(999L);
    });
  }

  @Test
  void testGetPersonByUsername() {
    Person mockedPerson = new Person();

    Mockito.when(personRepository.findByUsername("joaosilva"))
        .thenReturn(Optional.of(mockedPerson));

    PersonService personService = new PersonService(personRepository);
    Assertions.assertTrue(new ReflectionEquals(new Person())
        .matches(personService.getPersonByUsername("joaosilva")));
  }

  @Test
  void testCreatePerson() {
    Person mockedPerson = new Person();
    Person personToCreate = new Person();

    Mockito.when(personRepository.save(personToCreate))
        .thenReturn(mockedPerson);

    PersonService personService = new PersonService(personRepository);
    Assertions.assertEquals(mockedPerson, personService.create(personToCreate));
  }

}
