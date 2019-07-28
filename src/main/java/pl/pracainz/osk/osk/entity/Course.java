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
	private int idCategory;
	
	@Column(name="id_instructor")
	private int idInstructor;
	
	@Column
	private Date startDate;
	
	@Column
	private int finished;
	
	
	public Course() {
	}

	public Course(int id_category, int id_instructor, Date startDate, int finished) {
		this.idCategory = id_category;
		this.idInstructor = id_instructor;
		this.startDate = startDate;
		this.finished = finished;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdCategory() {
		return idCategory;
	}


	public void setIdCategory(int id_category) {
		this.idCategory = id_category;
	}


	public int getIdInstructor() {
		return idInstructor;
	}


	public void setIdInstructor(int id_instructor) {
		this.idInstructor = id_instructor;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public int getFinished() {
		return finished;
	}


	public void setFinished(int finished) {
		this.finished = finished;
	}

	

	
	
	
}

