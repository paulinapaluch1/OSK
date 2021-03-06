package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Integer>{
	public List<Lecture> findByDeleted(int deleted);
}
