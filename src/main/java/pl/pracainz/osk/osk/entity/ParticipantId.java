package pl.pracainz.osk.osk.entity;

import javax.persistence.Embeddable;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

@Embeddable
public class ParticipantId implements Serializable {

	private Student student;
	private Course course;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	@ManyToOne(cascade=CascadeType.DETACH)

	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
	
}
