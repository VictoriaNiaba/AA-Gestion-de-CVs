package gestioncv.services;

import java.util.Collection;

public interface IDao<T> {

	T find(Class<T> clazz, Object id);

	// TODO: Implémenter une variante qui permet de la pagination
	Collection<T> findAll(String query, Class<T> clazz);

	/**
	 * Ajoute l'entité passée en paramètre en base de données.
	 * 
	 * @param entity
	 * @return
	 */
	T add(T entity);

	T update(T entity);

	void remove(Class<T> clazz, Object pk);

	// TODO: Implémenter une ou plusieurs méthodes permettant de rechercher via
	// nom/prénom/titre (avec pagination) ..

}
