package pl.pracainz.osk.osk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pracainz.osk.osk.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

	

	@Query("SELECT distinct p.numberHoursPaid from Participant p "+"WHERE p.primaryKey.student.id=:id_student and p.primaryKey.course.id=:id_course")
	public int getNumberHoursPaid(@Param("id_student")int id, @Param("id_course")int id_course);

	

	

}
