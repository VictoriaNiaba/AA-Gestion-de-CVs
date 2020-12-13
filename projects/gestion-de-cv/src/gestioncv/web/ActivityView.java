package gestioncv.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import gestioncv.model.Activity;
import gestioncv.model.Nature;
import gestioncv.model.Person;
import gestioncv.services.IActivityDao;
import lombok.Getter;

@Named("activityView")
@SessionScoped
public class ActivityView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IActivityDao activityDao;

	/* Données utilisées dans les vues */
	private @Getter Activity selectedActivity;

	public String showActivityEditPage(int id) {
		selectedActivity = activityDao.find(id);
		System.out.println(selectedActivity);
		return "activity-form";
	}
	
	public String showActivityCreatePage(Nature nature, Person person) {
		selectedActivity = new Activity();
		selectedActivity.setNature(nature);
		selectedActivity.setOwner(person);
		return "activity-form";
	}
	
	public void deleteActivity(int id) {
		// TODO: afficher une pop-up de confirmation avant de supprimer
		this.activityDao.remove(id);
	}
	
	public String saveActivity(Activity activity) {
		if(activity.getId() != 0) {
			selectedActivity = activityDao.update(activity);
		}
		else {
			selectedActivity = activityDao.add(activity);
		}
		// TODO: faire en sorte que la mise-�-jour soit visible au sein de la collection
		return "cv-detail";
	}
}