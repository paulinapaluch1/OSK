package pl.pracainz.osk.osk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.Driving;

public interface DrivingRepository extends JpaRepository<Driving, Integer>{

	public List<Driving> findByDeleted(int deleted);
	public List<Driving> findByCancelled(int cancelled);
	public List<Driving> findByDone(int done);

}
