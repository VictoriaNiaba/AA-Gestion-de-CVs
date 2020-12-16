package gestioncv.services.impl;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.NotImplementedException;

import gestioncv.model.Person;
import gestioncv.services.IPersonDao;

@Stateless
@Dependent
public class PersonDao extends Dao<Person> implements IPersonDao {

	@Override
	public Person find(int id) {
		Person p = super.find(Person.class, id);
		p.getCv().size(); // Trigger LazyLoading
		return p;
	}

	@Override
	public Person findByEmail(String email) {
		return this.em.createQuery("SELECT p FROM Person p WHERE p.email = :email", Person.class)
				.setParameter("email", email)
				.getResultList().stream()
				.findFirst()
				.orElse(null);
	}

	@Override
	public Collection<Person> findAll() {
		return super.findAll(Person.class);
	}

	@RolesAllowed("Authenticated")
	@Override
	public Person add(Person person) {
		return super.add(person);
	}

	@RolesAllowed("Authenticated")
	@Override
	public Person update(Person person) {
		return super.update(person);
	}

	@Override
	public void remove(int id) {
		super.remove(Person.class, id);
	}

	@Override
	public Collection<Person> findPersonsByFirstName(String pattern) {
		String query = "SELECT p FROM Person p WHERE firstName LIKE :pattern"
				+ "ORDER BY id ASC";

		TypedQuery<Person> typedQuery = em.createQuery(query, Person.class);
		typedQuery.setParameter("pattern", "%" + pattern + "%");
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Person> findPersonsByLastName(String pattern) {
		String query = "SELECT p FROM Person p WHERE lastName LIKE :pattern"
				+ "ORDER BY id ASC";

		TypedQuery<Person> typedQuery = em.createQuery(query, Person.class);
		typedQuery.setParameter("pattern", "%" + pattern + "%");
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Person> findPersonsByActivityTitle(String title) {
		throw new NotImplementedException("La méthode n'est pas encore implémentée !");
	}

}
