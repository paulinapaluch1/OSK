package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Driving;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.InstructorOpinion;
import pl.pracainz.osk.osk.entity.InternalExam;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.Timetable;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	public List<Instructor> findByDeleted(Integer deleted);

	// lista kursów
	@Query("SELECT c FROM Course c " + "JOIN Instructor i ON i.id = c.instructor " + "WHERE i.id = :id")
	List<Course> queryFindCourses(@Param("id") int id);

	// lista egzaminow
	@Query("SELECT ie FROM InternalExam ie " + "JOIN Instructor i ON i.id = ie.instructor " + "WHERE i.id = :id")
	List<InternalExam> queryFindExams(@Param("id") int id);

	// lista instruktorów
	@Query("SELECT s FROM Student s " + "JOIN Driving d ON d.student = s.id " + "JOIN Timetable t ON d.timetable = t.id "
			+ "JOIN Instructor i ON i.id = t.instructor " + "WHERE i.id = :id")
	List<Student> queryFindStudents(@Param("id") int id);
	
	// lista opinii
	@Query("SELECT io FROM InstructorOpinion io " + "JOIN Instructor i ON i.id = io.instructor " + "WHERE i.id = :id AND io.status='zatwierdzona'")
	List<InstructorOpinion> queryFindOpinions(@Param("id") int id);

	// lista jazd
	@Query("SELECT d FROM Driving d " + "JOIN Timetable t ON t.id = d.timetable "
			+ "JOIN Instructor i ON i.id = t.instructor " + "WHERE i.id = :id")
	List<Driving> queryFindDrivings(@Param("id") int id);
	
	// grafik
//	@Query("SELECT t FROM timetable t " + "JOIN Instructor i on i.id = t.instructor " + "WHERE i.id= :id")
//	List<Timetable> queryFindTimetable(@Param("id") int id);
//	
}