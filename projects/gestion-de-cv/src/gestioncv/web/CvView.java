package gestioncv.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import gestioncv.model.Activity;
import gestioncv.model.Nature;
import gestioncv.model.Person;
import gestioncv.services.IPersonDao;
import lombok.Getter;

@Named("cvView")
@SessionScoped
public class CvView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IPersonDao personDao;

	/* Donn�es utilis�es dans les vues */
	private @Getter Collection<Person> persons;
	private @Getter Person selectedPerson;
	private @Getter boolean showEditButtons;

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
					.filter(activity -> nature.equals(activity.getNature()))
					.collect(Collectors.toList());
		}

		return new ArrayList<Activity>();
	}

	@PostConstruct
	public void init() {
		this.persons = personDao.findAll();
	}

	/**
	 * Contrôleur responsable de gérer les accès à la page d'affichage des détails
	 * d'un CV.
	 * 
	 * @param id
	 * @return
	 */
	public String show(int id) {
		updateSelectedPerson(personDao.find(id));
		return "cv-detail?faces-redirect=true";
	}

	public String showEditPage() {
		return "person-form";
	}

	public String savePerson(Person person) {
		if (person.getId() != 0) {
			updateSelectedPerson(personDao.update(person));
		} else {
			updateSelectedPerson(personDao.add(person));
		}
		// TODO: faire en sorte que la mise-�-jour soit visible au sein de la collection
		return "cv-detail";
	}

	/**
	 * La modification de selectedPerson doit être effectuée uniquement à l'aide de
	 * cette méthode afin de pouvoir appliquer des effets secondaires comme, par
	 * exemple, dicter l'affichage des boutons de modifications de CVs.
	 * 
	 * @param newSelectedPerson
	 */
	private void updateSelectedPerson(Person newSelectedPerson) {
		selectedPerson = newSelectedPerson;

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Person loggedInUser = (Person) externalContext.getSessionMap().get("user");

		// Effets secondaires de la mise à jour de selectedPerson
		if (selectedPerson != null && loggedInUser != null && selectedPerson.getId() == loggedInUser.getId()) {
			showEditButtons = true;
		} else {
			showEditButtons = false;
		}
	}
}