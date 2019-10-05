package pl.pracainz.osk.osk.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Integer>{
	
	
	@Query("select t from Timetable t where EXTRACT(YEAR from t.begin)=:year "
			+ "and EXTRACT(DAY from t.begin)=:day"
			+ " and EXTRACT(MONTH from t.begin)=:month")
	List<Timetable> queryByDayAndMonthAndYear(@Param("day") int day, @Param("month") int month, @Param("year") int year);

	
	@Query("select t from Timetable t where EXTRACT(YEAR from t.begin)=:year "
			+ "and EXTRACT(DAY from t.begin)=:day"
			+ " and EXTRACT(MONTH from t.begin)=:month and t.car.id=:id" )
	List<Timetable> queryByDayAndMonthAndYearAndCar(@Param("day") int day, 
			@Param("month") int month, @Param("year") int year,
			 @Param("id") int id);

	@Query("select t from Timetable t where EXTRACT(YEAR from t.begin)=:year "
			+ "and EXTRACT(DAY from t.begin)=:day"
			+ " and EXTRACT(MONTH from t.begin)=:month and t.instructor.id=:id" )
	List<Timetable> queryByDayAndMonthAndYearAndInstructor( @Param("day") int day,
			@Param("month") int month, @Param("year")  int year, @Param("id") int id);


	@Query("select t from Timetable t where t.instructor=:instructor"
			+" and t.begin between :monday and :sunday")
	List<Timetable> queryByInstructorAndWeek(@Param("instructor")Instructor instructor, 
			@Param("monday")LocalDateTime monday, @Param("sunday")LocalDateTime sunday);
	
}
