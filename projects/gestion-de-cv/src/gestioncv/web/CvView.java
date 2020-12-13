package gestioncv.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import gestioncv.model.Activity;
import gestioncv.model.Nature;
import gestioncv.model.Person;
import gestioncv.services.IPersonDao;
import gestioncv.utils.PageRequest;
import lombok.Getter;

@Named("cvView")
@SessionScoped
public class CvView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IPersonDao personDao;

	/* Données utilisées dans les vues */
	private @Getter Collection<Person> persons;
	private @Getter Person selectedPerson;

	public Collection<Activity> getSelectedPersonEducations() {
		return filterActivitiesByNature(selectedPerson, Nature.EDUCATION);
	}
	public Collection<Activity> getSelectedPersonProfessionalExperiences() {
		return filterActivitiesByNature(selectedPerson, Nature.PROFESSIONAL_EXPERIENCE);
	}
	public Collection<Activity> getSelectedPersonProjects() {
		return filterActivitiesByNature(selectedPerson, Nature.PROJECT);
	}
	public Collection<Activity> getSelectedPersonOtherActivities() {
		return filterActivitiesByNature(selectedPerson, Nature.OTHER);
	}
	
	private Collection<Activity> filterActivitiesByNature(Person person, Nature nature) {
		if (person != null) {
			return person.getCv().stream()
					.filter(activity -> activity.getNature().equals(nature))
					.collect(Collectors.toList());
		}
		
		return new ArrayList<Activity>();
	}

	@PostConstruct
	public void init() {
		// TODO: supprimer la pagination, Primefaces le fait déjà
		this.persons = personDao.findAll(new PageRequest(0, 100));
	}

	/**
	 * Contrôleur responsable de gérer les accès à la page d'affichage des
	 * détails d'un CV.
	 * 
	 * @param id
	 * @return
	 */
	public String show(int id) {
		selectedPerson = personDao.find(id);
		return "cv-detail";
	}

	public String showEditPage() {
		return "person-form";
	}

	public String savePerson(Person person) {
		if(person.getId() != 0) {
			selectedPerson = personDao.update(person);
		}
		else {
			selectedPerson = personDao.add(person);
		}
		// TODO: faire en sorte que la mise-�-jour soit visible au sein de la collection
		return "cv-detail";
	}
}