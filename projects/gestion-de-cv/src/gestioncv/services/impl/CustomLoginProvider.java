package gestioncv.services.impl;

import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.FailedLoginException;

import org.apache.openejb.core.security.jaas.LoginProvider;

import gestioncv.model.Person;
import gestioncv.services.IPersonDao;

/**
 * Cette impl�mentation de LoginProvider est utilis�e par le
 * ServiceProviderLoginModule de OpenEJB + Jaas pour authentifier les
 * utilisateurs.
 * 
 * Elle n'est pas compatible avec les technologies EJB/CDI et les injections de
 * d�pendances doivent donc �tre r�alis�es manuellement � l'aide de la m�thode
 * lookup().
 */
public class CustomLoginProvider implements LoginProvider {

	private IPersonDao personDao;

	public static final String BASE = "java:global/AA_Gestion_de_CV";

	private Context lookupForDependencies() throws NamingException {
		Context initialContext = new InitialContext();
		personDao = (IPersonDao) initialContext.lookup(BASE + "/PersonDao");
		return initialContext;
	}

	@Override
	public List<String> authenticate(String user, String password) throws FailedLoginException {
		try {
			lookupForDependencies();

			Person person = personDao.findByEmail(user);
			if (person != null && PasswordHash.validatePassword(password, person.getPassword())) {
				return Arrays.asList("Authenticated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new FailedLoginException("Bad user or password!");
	}

}
