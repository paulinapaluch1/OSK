package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	public List<Instructor> findByDeleted(Integer deleted);

}
