package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pracainz.osk.osk.entity.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Integer>{
	
	
	@Query("select t from Timetable t where EXTRACT(YEAR from t.begin)=:year "
			+ "and EXTRACT(DAY from t.begin)=:day"
			+ " and EXTRACT(MONTH from t.begin)=:month")
	List<Timetable> queryByDayAndMonthAndYear(@Param("day") int day, @Param("month") int month, @Param("year") int year);

	
}
