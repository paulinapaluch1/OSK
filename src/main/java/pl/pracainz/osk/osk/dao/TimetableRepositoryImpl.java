package pl.pracainz.osk.osk.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.pracainz.osk.osk.entity.Timetable;

//@Repository
//@Transactional(readOnly=true)
public class TimetableRepositoryImpl  implements TimetableRepositoryCustom{

	
	
	@Autowired
	EntityManager entityManager;
	
	
	public List<Timetable> getTimetablesByDate(Date begin) {
		
		 Query query = entityManager.createNativeQuery("from timetable as t"
				+"where t.begin=",Timetable.class);
		
		query.setParameter(1, begin); 
		
		//Query query=entityManager.createNativeQuery("select * from timetable");
		
		return query.getResultList();	
	}

	
	
	
	
	
	
}
