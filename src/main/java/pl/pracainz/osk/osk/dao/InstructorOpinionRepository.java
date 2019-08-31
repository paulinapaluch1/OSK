package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.InstructorOpinion;

public interface InstructorOpinionRepository extends JpaRepository<InstructorOpinion, Integer> {

	public List<InstructorOpinion> findByStatus(String status);
	public List<InstructorOpinion> findByDeleted(int deleted);
}
