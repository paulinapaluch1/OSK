package pl.pracainz.osk.osk.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "timetable")
public class Timetable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@ManyToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(name = "id_instructor")
	@Nullable
	private Instructor instructor;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date begin;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date end;

	@ManyToOne
	@JoinColumn(name = "id_car")
	private Car car;

	@ManyToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(name = "id_driving")
	private Driving driving;

	@Column
	private int archived;

	public Timetable() {
	}

	public Timetable(Instructor instructor, Date begin, Date end, Car car, Driving driving, int archived) {
		super();
		this.instructor = instructor;
		this.begin = begin;
		this.end = end;
		this.car = car;
		this.driving = driving;
		this.archived = archived;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Driving getDriving() {
		return driving;
	}

	public void setDriving(Driving driving) {
		this.driving = driving;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

}
