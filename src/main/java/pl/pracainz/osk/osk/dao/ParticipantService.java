package pl.pracainz.osk.osk.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Participant;

@Service
public class ParticipantService {

	@Autowired
	ParticipantRepository participantRepository;

	@Autowired
	CourseRepository courseRepository;
	
	
	public ParticipantService() {
		
	}
	public void deleteParticipant(int id_student, int id_course) {
		List<Participant> participants = participantRepository.findAll();
		List<Participant> participant=participants.stream()
				.filter(p -> p.getPrimaryKey().getCourse().getId() == id_course)
				.filter(p -> p.getPrimaryKey().getStudent().getId() == id_student)
				.collect(Collectors.toList());
		
		participantRepository.delete(participant.get(0));
		
	}
	
	
	public int getNumberHoursPaidForParticipant(int id_student, int id_course) {
	
	List<Participant> participants = participantRepository.findAll();
	List<Participant> participant=participants.stream()
			.filter(p -> p.getPrimaryKey().getCourse().getId() == id_course)
			.filter(p -> p.getPrimaryKey().getStudent().getId() == id_student)
			.collect(Collectors.toList());
	Participant parti = participant.get(0);
if(participant!=null) return parti.getNumberHoursPaid(); else return 8;
	
}

	
	public int getNumberHoursUsedForParticipant(int id_student, int id_course) {
		
		List<Participant> participants = participantRepository.findAll();
		List<Participant> participant=participants.stream()
				.filter(p -> p.getPrimaryKey().getCourse().getId() == id_course)
				.filter(p -> p.getPrimaryKey().getStudent().getId() == id_student)
				.collect(Collectors.toList());
		Participant parti = participant.get(0);
	if(participant!=null) return parti.getNumberHoursUsed(); else return 8;
		
	}

	
	

	
	public ParticipantRepository getParticipantRepository() {
		return participantRepository;
	}
	public void setParticipantRepository(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}
	public CourseRepository getCourseRepository() {
		return courseRepository;
	}
	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	public Participant getParticipant(int id_student, int id_course) {
		List<Participant> participants = participantRepository.findAll();
		List<Participant> participant=participants.stream()
				.filter(p -> p.getPrimaryKey().getCourse().getId() == id_course)
				.filter(p -> p.getPrimaryKey().getStudent().getId() == id_student)
				.collect(Collectors.toList());
		
		return participant.get(0);
	}

	
	
	
	
	
	
}
