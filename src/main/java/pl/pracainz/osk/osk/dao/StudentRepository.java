package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	public List<Student> findByDeleted(Integer deleted);
}
