package gestioncv.services.impl;

import java.util.Collection;

import javax.ejb.Stateless;

import gestioncv.model.Activity;
import gestioncv.services.IActivityDao;

@Stateless
public class ActivityDao extends Dao<Activity> implements IActivityDao {

	@Override
	public Activity find(int id) {
		return super.find(Activity.class, id);
	}

	@Override
	public Collection<Activity> findAll() {
		return super.findAll(Activity.class);
	}

	@Override
	public void remove(int id) {
		super.remove(Activity.class, id);
	}

}
