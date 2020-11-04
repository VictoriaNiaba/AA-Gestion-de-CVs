package gestioncv.services.impl;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.NotImplementedException;

import gestioncv.services.IDao;

@Stateless
public class Dao<T> implements IDao<T> {

	@PersistenceContext(name = "myBase")
	EntityManager em;

	@Override
	public T find(Class<T> clazz, Object id) {
		return em.find(clazz, id);
	}

	@Override
	public Collection<T> findAll(String query, Class<T> clazz) {
		throw new NotImplementedException("La méthode n'est pas encore implémentée !");
	}

	@Override
	public T add(T entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		return em.merge(entity);
	}

	@Override
	public void remove(Class<T> clazz, Object pk) {
		T entity = em.find(clazz, pk);
		if (entity != null) {
			em.remove(entity);
		}
	}
}
