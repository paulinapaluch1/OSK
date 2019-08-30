package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.InternalExam;

public interface InternalExamRepository extends JpaRepository<InternalExam, Integer> {
	public List<InternalExam> findByDeleted(int deleted);
	public List<InternalExam> findByResult(int result);
}
