package pl.pracainz.osk.osk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.GpsPoint;

public interface GpsPointRepository extends JpaRepository<GpsPoint, Integer> {

}
