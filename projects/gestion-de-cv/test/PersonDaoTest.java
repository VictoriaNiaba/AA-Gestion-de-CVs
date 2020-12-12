import javax.ejb.EJB;

import org.junit.jupiter.api.Test;

import gestioncv.services.IPersonDao;

public class PersonDaoTest extends BaseJunit5 {

	@EJB
	IPersonDao personDao;

	@Test
	public void testFindAllSuccess() {

	}

}