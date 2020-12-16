package gestioncv.servicesTests;
import static org.junit.Assert.assertEquals;
import org.apache.commons.lang3.NotImplementedException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doNothing;

import gestioncv.model.Person;
import gestioncv.services.IPersonDao;
import gestioncv.services.impl.Dao;
import gestioncv.services.impl.PersonDao;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonDaoTest{

	@InjectMocks
    @Spy
	PersonDao personDao;
	

	@Test
	public void testFindAllSuccess() {
		Collection<Person> persons = Collections.singletonList(Person.builder().id(10).build());
		doReturn(persons).when(personDao).findAll(Person.class);
		personDao.findAll();
		verify(personDao).findAll(Person.class);
	}
	
	@Test
	public void testFind() {
		int id = 10 ;
		Person person = Person.builder().id(id).build();
		doReturn(person).when(personDao).find(id);
		Person foundedPerson = personDao.find(id);
		verify(personDao).find(id);
		assertEquals(id, foundedPerson.getId());
	}
	
	@Test
	public void testRemove() {
		int id = 10 ;
		doNothing().when(personDao).remove(Person.class,id);
		personDao.remove(id);
		verify(personDao).remove(Person.class,id);

	}
	
	@Test
	public void testFindPersonsByActivityTitle() {
		String title = "activity title";
		assertThrows(NotImplementedException.class, () -> personDao.findPersonsByActivityTitle(title));
	}

}