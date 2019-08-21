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

import org.springframework.lang.Nullable;

@Entity
@Table(name = "internalexams")
public class InternalExam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_internalExam")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_instructor")
	private Instructor instructor;

	@ManyToOne
	@JoinColumn(name = "id_student")
	private Student student;

	@Column
	private Date dateHour;

	@Column
	private int result;

	@Column
	private String type;

	@Nullable
	@Column
	private int deleted;

	public InternalExam() {
	}

	public InternalExam(Instructor instructor, Student student, Date dateHour, int result, String type, int deleted) {

		this.instructor = instructor;
		this.student = student;
		this.dateHour = dateHour;
		this.result = result;
		this.type = type;
		this.deleted = deleted;
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

	public void setinstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getDateHour() {
		return dateHour;
	}

	public void setDateHour(Date dateHour) {
		this.dateHour = dateHour;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "InternalExam [id=" + id + ", id_instructor=" + instructor.getName() + ", id_student="
				+ student.getName() + ", dateHour=" + dateHour + ", result=" + result + ", type=" + type + ", deleted="
				+ deleted + "]";
	}

}
