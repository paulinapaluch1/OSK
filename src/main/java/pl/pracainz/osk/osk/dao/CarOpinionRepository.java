package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.CarOpinion;

public interface CarOpinionRepository extends JpaRepository<CarOpinion, Integer>{

	public List<CarOpinion> findByDeleted(int deleted);
	public List<CarOpinion> findByStatus(String status);
}
