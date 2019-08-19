package pl.pracainz.osk.osk.dao;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.pracainz.osk.osk.entity.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Integer>, TimetableRepositoryCustom{
	
	
	@Query("from Timetable where begin=?1")
	List<Timetable> findByBegin(Date begin);
	
}
