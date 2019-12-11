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
import pl.pracainz.osk.osk.entity.Lecture;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.Timetable;
import pl.pracainz.osk.osk.entity.User;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	public List<Student> findByDeleted(Integer deleted);

	// lista instruktorów
	@Query("SELECT distinct i FROM Instructor i " + "JOIN Timetable t ON t.instructor = i.id "
			+ "JOIN Driving d ON d.timetable = t.id " + "JOIN Student s ON s.id = d.student " + "WHERE s.id = :id")
	List<Instructor> findInstructorsForOneStudent(@Param("id") int id);

	// lista samochodów
	@Query("SELECT distinct c FROM Car c " + "JOIN Timetable t ON t.car = c.id "
			+ "JOIN Driving d ON d.timetable = t.id " + "JOIN Student s ON s.id = d.student " + "WHERE s.id = :id")
	List<Car> findCarsForOneStudentById(@Param("id") int id);

	// lista - grafik
	@Query("SELECT t FROM Timetable t " + "JOIN Driving d ON d.timetable = t.id "
			+ "JOIN Student s ON s.id = d.student " + "WHERE s.id = :id")
	List<Timetable> findTimetableForStudent(@Param("id") int id);

	// lista jazd

	@Query("SELECT d FROM Driving d " + "JOIN Student s ON s.id = d.student " + "JOIN Timetable t ON t.id = d.timetable "
			+ "WHERE s.id = :id " + "order by t.begin") // AND d.cancelled = 0")

	List<Driving> findDrivingsForStudent(@Param("id") int id);

	@Query("SELECT d FROM Driving d " + "JOIN Student s ON s.id = d.student " + "WHERE s.id = :id AND d.cancelled = 1")
	List<Driving> findCancelledDrivings(@Param("id") int id);

	@Query("SELECT d FROM Driving d " + "JOIN Student s ON s.id = d.student " + "WHERE s.id = :id AND d.done = 1")
	List<Driving> findDoneDrivingsForStudentById(@Param("id") int id);

	// lista egzaminow
	@Query("SELECT ie FROM InternalExam ie " + "JOIN Student s ON s.id = ie.student " + "WHERE s.id = :id")
	List<InternalExam> findStudentExams(@Param("id") int id);

	// lista wykładów
	@Query("SELECT DISTINCT l FROM Lecture l " + "JOIN Course c ON c.id = l.course " + "JOIN Participant p ON p.primaryKey.course = c.id "
			+ "JOIN Student s ON s.id = p.primaryKey.student " + "WHERE s.id = :id " + "ORDER BY l.date ")
	List<Lecture> findLectures(@Param("id") int id);

	// lista kursów

	@Query("SELECT DISTINCT c FROM Course c " + "JOIN Participant p ON p.primaryKey.course = c.id "
			+ "JOIN Student s ON s.id = p.primaryKey.student " + "WHERE s.id = :id")
	List<Course> findCoursesForStudent(@Param("id") int id);

	// znajdz haslo
	@Query("SELECT u FROM User u " + "JOIN Student s ON s.userId = u.id " + "WHERE s.id = :id")
	User findPassword(@Param("id") int id);

	public Student findByUserId(int userId);

	public Student findByPkk(String pkk);


}
