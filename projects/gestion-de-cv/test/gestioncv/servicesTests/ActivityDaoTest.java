package gestioncv.servicesTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import gestioncv.model.Activity;
import gestioncv.services.impl.ActivityDao;

@ExtendWith(MockitoExtension.class)
public class ActivityDaoTest {
	
	@InjectMocks
    @Spy
    ActivityDao activityDao;
	
	@Test
	public void testFindAllSuccess() {
		Collection<Activity> activities = Collections.singletonList(Activity.builder().id(10).build());
		doReturn(activities).when(activityDao).findAll(Activity.class);
		activityDao.findAll();
		verify(activityDao).findAll(Activity.class);
	}
	
	@Test
	public void testFind() {
		int id = 10 ;
		Activity activity = Activity.builder().id(id).build();
		doReturn(activity).when(activityDao).find(id);
		Activity foundedActivity = activityDao.find(id);
		verify(activityDao).find(id);
		assertEquals(id, foundedActivity.getId());
	}
	
	@Test
	public void testRemove() {
		int id = 10 ;
		doNothing().when(activityDao).remove(Activity.class,id);
		activityDao.remove(id);
		verify(activityDao).remove(Activity.class,id);

	}


}
