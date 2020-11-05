package gestioncv.services;

import java.util.Collection;

import gestioncv.model.Person;
import gestioncv.utils.PageRequest;

public interface IPersonDao {

	Person find(int id);

	Collection<Person> findAll(PageRequest pageRequest);

	/**
	 * Ajoute l'entité passée en paramètre en base de données.
	 * 
	 * @param entity
	 * @return
	 */
	Person add(Person person);

	Person update(Person person);

	void remove(int id);

	Collection<Person> findPersonsByFirstName(String pattern, PageRequest pageRequest);

	Collection<Person> findPersonsByLastName(String pattern, PageRequest pageRequest);

	Collection<Person> findPersonsByActivityTitle(String title, PageRequest pageRequest);
}
