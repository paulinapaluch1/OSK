package pl.pracainz.osk.osk.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="instructors")
public class Instructor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_instructor")
	private int id_instructor;
	
	@Column(name="name")
	private String name;
	
	@Column
	private String surname;
	
	@Column 
	private String login;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@Column
	private Integer deleted;

	@OneToMany()
	List<InternalExam> exams;
	
	@OneToMany()
	List<Course> courses;
	
	public Instructor() {}
	
	public Instructor(String name, String surname, String login, String password, String email, String phoneNumber,
			int deleted) {
	
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.deleted = deleted;
	}

	public int getId_instructor() {
		return id_instructor;
	}

	public void setId_instructor(int id_instructor) {
		this.id_instructor = id_instructor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	

	public List<InternalExam> getExams() {
		return exams;
	}

	public void setExams(List<InternalExam> exams) {
		this.exams = exams;
	}
	
	

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Instructor [id_instructor=" + id_instructor + ", name=" + name + ", surname=" + surname + ", login="
				+ login + ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber + ", deleted="
				+ deleted + "]";
	}
	
	
	
	
	

}
