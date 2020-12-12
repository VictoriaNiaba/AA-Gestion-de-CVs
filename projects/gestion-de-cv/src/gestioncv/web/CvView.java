package gestioncv.web;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

	@PostConstruct
	public void init() {
		// TODO: supprimer la pagination, Primefaces le fait déjà
		this.persons = personDao.findAll(new PageRequest(0, 100));
	}

	/**
	 * Contrôleur responsable de gérer les accès à la page d'affichage des détails
	 * d'un CV.
	 * 
	 * @param id
	 * @return
	 */
	public String show(int id) {
		selectedPerson = personDao.find(id);
		return "cv-detail";
	}

	public String showEditPage() {
		return "cv-form";
	}
}