package pl.pracainz.osk.osk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
