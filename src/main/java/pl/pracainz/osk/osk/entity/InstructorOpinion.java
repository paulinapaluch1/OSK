package pl.pracainz.osk.osk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructorOpinions")
public class InstructorOpinion{


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_instructorOpinion")
	private int id;
	
	@Column
	private int id_student;
	
	@ManyToOne
	@JoinColumn(name="id_instructor")
	private Instructor instructor;
	
	@Column
	private int instructorMark;
	
	@Column
	private String instructorOpinion;
	
	@Column
	private int deleted;

	public InstructorOpinion(int id_student, Instructor instructor, int instructorMark, String instructorOpinion, int deleted) {
		super();
		this.id_student = id_student;
		this.instructor = instructor;
		this.instructorMark = instructorMark;
		this.instructorOpinion = instructorOpinion;
		this.deleted = deleted;
	}

	
	
	public int getInstructorMark() {
		return instructorMark;
	}



	public void setInstructorMark(int instructorMark) {
		this.instructorMark = instructorMark;
	}



	public String getInstructorOpinion() {
		return instructorOpinion;
	}



	public void setInstructorOpinion(String instructorOpinion) {
		this.instructorOpinion = instructorOpinion;
	}




	public Instructor getInstructor() {
		return instructor;
	}



	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_student() {
		return id_student;
	}

	public void setId_student(int id_student) {
		this.id_student = id_student;
	}

	
	int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
	
}
