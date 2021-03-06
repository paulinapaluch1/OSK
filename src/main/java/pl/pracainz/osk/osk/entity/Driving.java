package pl.pracainz.osk.osk.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drivings")
public class Driving {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_driving")
	private int id;

	@ManyToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(name = "id_student")
	private Student student;

	@Column
	private int done;

	@Column
	private Integer cancelled;

	@Column
	private int deleted;

	@ManyToOne(cascade = { CascadeType.ALL }, optional = false)
	@JoinColumn(name = "id_timetable") 
	private Timetable timetable;

	public Driving() {
	}

	public Driving(Student student, int done, int cancelled, int deleted) {
		super();
		this.student = student;
		this.done = done;
		this.cancelled = cancelled;
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public Integer getCancelled() {
		return cancelled;
	}

	public void setCancelled(Integer cancelled) {
		this.cancelled = cancelled;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Timetable getTimetable() {
		return timetable;
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}

	public boolean isDrivingDateInThePast() {
		if (timetable.getBegin().isAfter(LocalDateTime.now()))
			return false;
		else
			return true;
	}

}
