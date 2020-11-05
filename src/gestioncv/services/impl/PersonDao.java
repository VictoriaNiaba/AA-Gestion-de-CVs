package gestioncv.services.impl;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.apache.commons.lang3.NotImplementedException;

import gestioncv.model.Person;
import gestioncv.services.IPersonDao;
import gestioncv.utils.PageRequest;

@Stateless
public class PersonDao extends Dao<Person> implements IPersonDao {

	@Override
	public Person find(int id) {
		return super.find(Person.class, id);
	}

	@Override
	public Collection<Person> findAll(PageRequest pageRequest) {
		return super.findAll(Person.class, pageRequest);
	}

	@Override
	public void remove(int id) {
		super.remove(Person.class, id);
	}

	@Override
	public Collection<Person> findPersonsByFirstName(String pattern, @Valid PageRequest pageRequest) {
		String query = "SELECT p FROM Person p WHERE firstName LIKE :pattern"
				+ "ORDER BY id ASC LIMIT :limit OFFSET :offset";

		TypedQuery<Person> typedQuery = em.createQuery(query, Person.class);
		typedQuery.setParameter("pattern", "%" + pattern + "%");
		typedQuery.setParameter("limit", pageRequest.getMaxResultsPerPage());
		typedQuery.setParameter("offset", pageRequest.getFirstResult());
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Person> findPersonsByLastName(String pattern, @Valid PageRequest pageRequest) {
		String query = "SELECT p FROM Person p WHERE lastName LIKE :pattern"
				+ "ORDER BY id ASC LIMIT :limit OFFSET :offset";

		TypedQuery<Person> typedQuery = em.createQuery(query, Person.class);
		typedQuery.setParameter("pattern", "%" + pattern + "%");
		typedQuery.setParameter("limit", pageRequest.getMaxResultsPerPage());
		typedQuery.setParameter("offset", pageRequest.getFirstResult());
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Person> findPersonsByActivityTitle(String title, @Valid PageRequest pageRequest) {
		throw new NotImplementedException("La méthode n'est pas encore implémentée !");
	}

}
