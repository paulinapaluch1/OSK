package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	public List<Course> findByFinished(Integer finished);
}
