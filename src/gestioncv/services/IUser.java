package gestioncv.services;

import javax.ejb.Local;

@Local
public interface IUser {

	void login(String login, String password);

	void logout();

	String getLogin();
}
