package gestioncv.services;

import java.util.Collection;

import gestioncv.model.Activity;
import gestioncv.utils.PageRequest;

public interface IActivityDao {

	Activity find(int id);

	Collection<Activity> findAll(PageRequest pageRequest);

	/**
	 * Ajoute l'entité passée en paramètre en base de données.
	 * 
	 * @param entity
	 * @return
	 */
	Activity add(Activity activity);

	Activity update(Activity activity);

	void remove(int id);
}
