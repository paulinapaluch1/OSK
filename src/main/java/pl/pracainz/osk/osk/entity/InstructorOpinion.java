package pl.pracainz.osk.osk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column
	private int id_instructor;
	
	@Column
	private int instructorMark;
	
	@Column
	private String instructorOpinion;
	
	@Column
	private int deleted;

	public InstructorOpinion(int id_student, int id_instructor, int instructorMark, String instructorOpinion, int deleted) {
		super();
		this.id_student = id_student;
		this.id_instructor = id_instructor;
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



	public void setId_instructor(int id_instructor) {
		this.id_instructor = id_instructor;
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

	public int getId_instructor() {
		return id_instructor;
	}
	int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
	
}
