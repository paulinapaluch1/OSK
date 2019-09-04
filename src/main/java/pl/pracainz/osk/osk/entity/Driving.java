package pl.pracainz.osk.osk.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "drivings")
public class Driving {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_driving")
	private int id;

	@ManyToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(name = "id_type")
	private DrivingType drivingType;

	@ManyToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(name = "id_student")
	private Student student;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date startHour;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date finishHour;

	@Column
	private int done;

	@Column
	private Integer cancelled;
	
	@Column
	private int deleted;

	@OneToMany(mappedBy = "id")
	private List<Timetable> timetables;

	public Driving() {
	}

	public Driving(DrivingType type, Student student, Date startHour, Date finishHour, int done, int cancelled, int deleted) {
		super();
		this.drivingType = type;
		this.student = student;
		this.startHour = startHour;
		this.finishHour = finishHour;
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

	public DrivingType getDrivingType() {
		return drivingType;
	}

	public void setDrivingType(DrivingType drivingType) {
		this.drivingType = drivingType;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getStartHour() {
		return startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	public Date getFinishHour() {
		return finishHour;
	}

	public void setFinishHour(Date finishHour) {
		this.finishHour = finishHour;
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

	public List<Timetable> getTimetables() {
		return timetables;
	}

	public void setTimetables(List<Timetable> timetables) {
		this.timetables = timetables;
	}
	
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}


}
