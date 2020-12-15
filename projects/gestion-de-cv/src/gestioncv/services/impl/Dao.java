package gestioncv.services.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import gestioncv.services.IDao;

public abstract class Dao<T> implements IDao<T> {

	@PersistenceContext(unitName = "myData")
	protected EntityManager em;

	@Override
	public T find(Class<T> clazz, Object id) {
		return em.find(clazz, id);
	}

	@Override
	public Collection<T> findAll(Class<T> clazz) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(clazz);

		Root<T> root = cq.from(clazz);
		cq.select(root);

		return em.createQuery(cq).getResultList();
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
