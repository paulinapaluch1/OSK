package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Student;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	public List<Course> findByFinished(Integer finished);

	// uczestnicy kursu // admin
	@Query("SELECT DISTINCT s FROM Student s " + "JOIN Participant p ON p.primaryKey.student = s.id "
			+ "JOIN Course c ON c.id = p.primaryKey.course " + "WHERE c.id = :id")
	List<Student> findParticipants(@Param("id") int id);

	// kursy kursanta // admin
	@Query("SELECT DISTINCT c FROM Course c " + "JOIN Participant p ON p.primaryKey.course = c.id "
			+ "JOIN Student s ON s.id = p.primaryKey.student " + "WHERE s.id = :id")
	List<Course> findCourses(@Param("id") int id);

	// usun uczestnika kursu
	@Query("DELETE FROM Participant p " + "WHERE p.primaryKey.student = :id_student AND p.primaryKey.course = :id_course")
	List<Student> deleteParticipant(@Param("id_course") int id_course, @Param("id_student") int id_student);
}
