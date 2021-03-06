package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{

	public List<Car> findByDeleted(Integer deleted);
	
}
