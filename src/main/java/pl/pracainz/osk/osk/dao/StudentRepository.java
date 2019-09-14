package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Driving;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.InternalExam;
import pl.pracainz.osk.osk.entity.Participant;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.Timetable;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	public List<Student> findByDeleted(Integer deleted);

	// lista instruktorów
	@Query("SELECT distinct i FROM Instructor i " + "JOIN Timetable t ON t.instructor = i.id "
			+ "JOIN Driving d ON d.timetable = t.id " + "JOIN Student s ON s.id = d.student " + "WHERE s.id = :id")
	List<Instructor> queryFindInstructors(@Param("id") int id);

	// lista samochodów
	@Query("SELECT c FROM Car c " + "JOIN Timetable t ON t.car = c.id " + "JOIN Driving d ON d.timetable = t.id "
			+ "JOIN Student s ON s.id = d.student " + "WHERE s.id = :id")
	List<Car> queryFindCars(@Param("id") int id);

	// lista - grafik
	@Query("SELECT t FROM Timetable t " + "JOIN Driving d ON d.timetable = t.id "
			+ "JOIN Student s ON s.id = d.student " + "WHERE s.id = :id")
	List<Timetable> queryFindTimetable(@Param("id") int id);

	// lista jazd
	@Query("SELECT d FROM Driving d " + "JOIN Student s ON s.id = d.student " + "WHERE s.id = :id") // AND d.cancelled = 0")
	List<Driving> queryFindDrivings(@Param("id") int id);

	// lista egzaminow
	@Query("SELECT ie FROM InternalExam ie " + "JOIN Student s ON s.id = ie.student " + "WHERE s.id = :id")
	List<InternalExam> queryFindExams(@Param("id") int id);

	// lista wykładów

	// ------------------------TU JEST PROBLEM--------------------------------
	/*
	 * // lista kursów
	 * 
	 * @Query("SELECT c FROM Course c " +
	 * "JOIN ParticipantId pid ON pid.course = c.id " +
	 * "JOIN Participant p ON p.primaryKey = pid " +
	 * "JOIN Student s ON s.id = pid.student " + "WHERE s.id = :id") List<Course>
	 * queryFindCourses(@Param("id") int id);
	 */
//	@Query("SELECT i FROM Instructor i WHERE i.id = :id")
//	List<Instructor> queryFindInstructors(@Param("id") int id);
}
