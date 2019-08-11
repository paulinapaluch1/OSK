package pl.pracainz.osk.osk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="timetable")
public class Timetable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_instructor")
	private Instructor instructor;

	
	@Column
	private Date begin;
	
	@Column
	private Date end;
	
	@Column
	private int id_car;
	
	@Column
	private int id_driving;
	
	@Column
	private int archived;

	public Timetable(Instructor instructor, Date begin, Date end, int id_car, int id_driving, int archived) {
		super();
		this.instructor = instructor;
		this.begin = begin;
		this.end = end;
		this.id_car = id_car;
		this.id_driving = id_driving;
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

	public int getId_car() {
		return id_car;
	}

	public void setId_car(int id_car) {
		this.id_car = id_car;
	}

	public int getId_driving() {
		return id_driving;
	}

	public void setId_driving(int id_driving) {
		this.id_driving = id_driving;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	
	
	
	
	
}
