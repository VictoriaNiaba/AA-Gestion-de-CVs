package gestioncv.services;

import java.util.Collection;

import gestioncv.model.Person;

public interface IPersonDao {

	Person find(int id);
	
	Person findByEmail(String email);

	Collection<Person> findAll();

	/**
	 * Ajoute l'entité passée en paramètre en base de données.
	 * 
	 * @param entity
	 * @return
	 */
	
	Person add(Person person);

	Person update(Person person);

	void remove(int id);

	Collection<Person> findPersonsByFirstName(String pattern);

	Collection<Person> findPersonsByLastName(String pattern);

	Collection<Person> findPersonsByActivityTitle(String pattern);
}
