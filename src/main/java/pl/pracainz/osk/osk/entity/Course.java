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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="courses")
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_course")
	private int id;
	
	@ManyToOne(cascade = {CascadeType.DETACH})
	@JoinColumn(name="id_category")
	private Category category;
	
	@ManyToOne(cascade= {CascadeType.DETACH})
	@JoinColumn(name="id_instructor")
	private Instructor instructor;
	
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
    @NotNull
	private Date startDate;
	
	@Column
	private int finished;
	
	
	@OneToMany(mappedBy="id")
	List<Lecture> lectures;
	
	public Course() {
	}

	public Course(Category id_category, Instructor instructor, Date startDate, int finished) {
		this.category = id_category;
		this.instructor = instructor;
		this.startDate = startDate;
		this.finished = finished;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
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

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	

	
}

