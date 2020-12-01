package gestioncv.services;

import java.util.Collection;

import gestioncv.utils.PageRequest;

public interface IDao<T> {

	T find(Class<T> clazz, Object id);

	Collection<T> findAll(Class<T> clazz, PageRequest pageRequest);

	/**
	 * Ajoute l'entité passée en paramètre en base de données.
	 * 
	 * @param entity
	 * @return
	 */
	T add(T entity);

	T update(T entity);

	void remove(Class<T> clazz, Object pk);
}
