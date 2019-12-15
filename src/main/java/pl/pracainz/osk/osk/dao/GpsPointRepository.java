package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pracainz.osk.osk.entity.GpsPoint;
import pl.pracainz.osk.osk.entity.Instructor;

public interface GpsPointRepository extends JpaRepository<GpsPoint, Integer> {

//	public List<GpsPoint> findById_Driving(Integer id_driving);
	
	@Query("SELECT p FROM GpsPoint p " + "JOIN Driving d ON d.id = p.id_driving " +  "WHERE p.id_driving = :id")
	List<GpsPoint> findPoints(@Param("id") int id);

}
