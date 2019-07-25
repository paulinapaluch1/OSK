package pl.pracainz.osk.osk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="internalexam")
public class InternalExam {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_internalExam")
	private int id;
	
	
	@Column
	private int id_instructor;
	
	@Column
	private int id_student;
	
	@Column
	private Date dateHour;
	
	@Column
	private int result;
	
	@Column 
	private String type;
	
	@Column
	private int deleted;

	public InternalExam(int id_instructor, int id_student, Date dateHour, int result, String type, int deleted) {
		super();
		this.id_instructor = id_instructor;
		this.id_student = id_student;
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

	public int getId_instructor() {
		return id_instructor;
	}

	public void setId_instructor(int id_instructor) {
		this.id_instructor = id_instructor;
	}

	public int getId_student() {
		return id_student;
	}

	public void setId_student(int id_student) {
		this.id_student = id_student;
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
		return "InternalExam [id=" + id + ", id_instructor=" + id_instructor + ", id_student=" + id_student
				+ ", dateHour=" + dateHour + ", result=" + result + ", type=" + type + ", deleted=" + deleted + "]";
	}
	
	
	
	
	


}
