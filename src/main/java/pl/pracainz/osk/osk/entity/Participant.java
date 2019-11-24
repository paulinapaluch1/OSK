package pl.pracainz.osk.osk.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="participants")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.student",
        joinColumns = @JoinColumn(name = "id_student")),
    @AssociationOverride(name = "primaryKey.course",
        joinColumns = @JoinColumn(name = "id_course")) })
public class Participant {
	
	private ParticipantId primaryKey;
	
	@Column
	private int numberHoursPaid;
	
	@Column
	private int numberHoursUsed;

	
	
	public Participant() {
		primaryKey = new ParticipantId();
	}
	@EmbeddedId
	public ParticipantId getPrimaryKey() {
		return primaryKey;
	}

	@Transient
	private Student getStudent() {
		return getPrimaryKey().getStudent();
	}
	
	private void setStudent(Student student) {
		getPrimaryKey().setStudent(student);
	}
	
	@Transient public Course getCourse() {
		return getPrimaryKey().getCourse();
	}
	
	private void setCourse(Course course) {
		getPrimaryKey().setCourse(course);
	}
	
	
	
	public void setPrimaryKey(ParticipantId primaryKey) {
		this.primaryKey = primaryKey;
	}

	public int getNumberHoursPaid() {
		return numberHoursPaid;
	}

	public void setNumberHoursPaid(int numberHoursPaid) {
		this.numberHoursPaid = numberHoursPaid;
	}

	public int getNumberHoursUsed() {
		return numberHoursUsed;
	}

	public void setNumberHoursUsed(int numberHoursUsed) {
		this.numberHoursUsed = numberHoursUsed;
	}
	
	
}
