package gestioncv.services.impl;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import org.apache.commons.lang3.NotImplementedException;

import gestioncv.services.IUser;

@Stateful
@SessionScoped
public class UserBean implements IUser {

	private String login = null;

	@Override
	public void login(String login, String password) {
		throw new NotImplementedException("La méthode n'est pas encore implémentée !");
	}

	@Remove
	@Override
	public void logout() {
		throw new NotImplementedException("La méthode n'est pas encore implémentée !");
	}

	@Override
	public String getLogin() {
		return login;
	}

}
