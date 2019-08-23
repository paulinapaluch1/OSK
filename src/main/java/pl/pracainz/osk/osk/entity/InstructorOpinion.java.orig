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
@Table(name="instructoropinions")
public class InstructorOpinion{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_instructorOpinion")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_student")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "id_instructor")
	private Instructor instructor;

<<<<<<< HEAD
=======
	
>>>>>>> 17fb6d693d85ca73d87ae8483a7829b3b2cbbe05
	@Column(name = "instructorMark")
	private int instructorMark;
	
	@Column(name = "instructorOpinion")
	private String instructorOpinion;
	
	@Column(name = "status")
	private String status;
<<<<<<< HEAD

=======
>>>>>>> 17fb6d693d85ca73d87ae8483a7829b3b2cbbe05

	public InstructorOpinion () {}
	public InstructorOpinion(Student id_student, Instructor instructor, int instructorMark, String instructorOpinion, String deleted) {

		super();
		this.student = id_student;
		this.instructor = instructor;
		this.instructorMark = instructorMark;
		this.instructorOpinion = instructorOpinion;
		this.status = deleted;
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

<<<<<<< HEAD

	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
=======
	public String getStatus() {
		return status;

	}
	public void setStatus(String status) {
		this.status = status;
>>>>>>> 17fb6d693d85ca73d87ae8483a7829b3b2cbbe05
	}
	

}
