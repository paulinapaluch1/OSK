package pl.pracainz.osk.osk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_course")
	private int id;
	
	@Column(name="id_category")
	private int id_category;
	
	@Column(name="id_instructor")
	private int id_instructor;
	
	@Column(name="startDate")
	private Date startDate;
	
	@Column(name="finished")
	private Boolean finished;
	
	
	public Course() {
	}

	public Course(int id_category, int id_instructor, Date startDate, Boolean finished) {
		super();
		this.id_category = id_category;
		this.id_instructor = id_instructor;
		this.startDate = startDate;
		this.finished = finished;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_category() {
		return id_category;
	}


	public void setId_category(int id_category) {
		this.id_category = id_category;
	}


	public int getId_instructor() {
		return id_instructor;
	}


	public void setId_instructor(int id_instructor) {
		this.id_instructor = id_instructor;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Boolean getFinished() {
		return finished;
	}


	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	
	
	
	
	
	
}

