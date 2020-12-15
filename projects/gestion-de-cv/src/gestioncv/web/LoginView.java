package gestioncv.web;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import gestioncv.model.Person;
import gestioncv.services.IPersonDao;
import lombok.Getter;
import lombok.Setter;

@Named("loginView")
@ViewScoped
public class LoginView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IPersonDao personDao;

	/* Données utilisées dans les vues */
	private @Getter Person loggedInUser;
	private @Getter @Setter String login;
	private @Getter @Setter String password;
    private String originalURL;

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		originalURL = (String) externalContext.getRequestMap()
				.get(RequestDispatcher.FORWARD_REQUEST_URI);

		if (originalURL == null) {
			originalURL = externalContext.getRequestContextPath() + "/cv-list.xhtml";
		} else {
			String originalQuery = (String) externalContext.getRequestMap()
					.get(RequestDispatcher.FORWARD_QUERY_STRING);

			if (originalQuery != null) {
				originalURL += "?" + originalQuery;
			}
		}
	}

	public String login() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		try {
			request.login(login, password);
			Person user = personDao.findByEmail(login);
			externalContext.getSessionMap().put("user", user);
			externalContext.redirect(originalURL);
		} catch (ServletException e) {
			e.printStackTrace();
			// Handle unknown username/password in request.login().
			context.addMessage(null, new FacesMessage("Unknown login"));
			return "login";
		}
		
		return "cv-list";
	}

	public void logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.invalidateSession();
		externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
	}
}