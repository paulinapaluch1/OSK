package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.DrivingType;

public interface DrivingTypeRepository extends JpaRepository<DrivingType, Integer>{

	List<DrivingType> findAllByOrderByIdAsc();

}
