package gestioncv.services;

import java.util.Collection;

public interface IDao<T> {

	T find(Class<T> clazz, Object id);

	Collection<T> findAll(Class<T> clazz);

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
