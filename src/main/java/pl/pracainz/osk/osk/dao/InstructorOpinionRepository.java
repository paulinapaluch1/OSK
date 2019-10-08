package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pracainz.osk.osk.entity.InstructorOpinion;

public interface InstructorOpinionRepository extends JpaRepository<InstructorOpinion, Integer> {

	public List<InstructorOpinion> findByStatus(String status);
	public List<InstructorOpinion> findByDeleted(int deleted);
	
	@Query("SELECT io FROM InstructorOpinion io " + "JOIN Instructor i ON i.id = io.instructor " + "JOIN Student s ON s.id = io.student "
			+ "WHERE s.id = :id")
	List<InstructorOpinion> checkIfRated(@Param("id") int id);
}
