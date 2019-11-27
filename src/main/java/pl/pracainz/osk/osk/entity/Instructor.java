package pl.pracainz.osk.osk.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "instructors")
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_instructor")
	private int id;

	@Column(name = "name")
	@NotEmpty(message = "Pole email nie może być puste")
	@Pattern(regexp = "^[A-ZŁŚ]{1}+[a-ząęółśżźćń]+$", message = "Wprowadż poprawne imię")
	private String name;
	
	@Column
	@Pattern(regexp = "	^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$\r\n", message = "Wprowadź poprawne nazwisko")
	@NotEmpty(message = "Pole email nie może być puste")
	private String surname;

	@Column
	@NotEmpty(message = "Pole login nie może być puste")
	private String login;

	@Column
	@Email(message = "Wprowadź poprawny email")
	@NotEmpty(message = "Pole email nie może być puste")
	private String email;
	
	@Column(name = "phoneNumber")
	@NotEmpty(message = "Pole numer telefonu nie może być puste")
	@Pattern(regexp = "	^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$\r\n", message = "Wprowadź poprawny numer telefon")
	private String phoneNumber;

	@Column
	private Integer deleted;

	@Column(name = "id_user")
	private int userId;

	@OneToMany(mappedBy = "instructor")
	List<InternalExam> exams;

	@OneToMany(mappedBy = "instructor")
	List<Course> courses;

	@OneToMany(mappedBy = "instructor")
	List<InstructorOpinion> instructorOpinions;

	@OneToMany(mappedBy = "instructor")
	List<Timetable> timetables;

	public Instructor() {
	}

	public Instructor(int id, String name, String surname, String login, String email, String phoneNumber,
			int deleted) {

		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id_instructor) {
		this.id = id_instructor;
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

	public List<InstructorOpinion> getInstructorOpinions() {
		return instructorOpinions;
	}

	public void setInstructorOpinions(List<InstructorOpinion> instructorOpinions) {
		this.instructorOpinions = instructorOpinions;
	}

	public List<Timetable> getTimetables() {
		return timetables;
	}

	public void setTimetables(List<Timetable> timetables) {
		this.timetables = timetables;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getMarkAverage() {
		if(getInstructorOpinions()!=null && !getInstructorOpinions().isEmpty()) {
		double sum = 0;
		double quantity = 0;			
		for (InstructorOpinion opinion : instructorOpinions) {
			if(opinion.getStatus().equals("zatwierdzona")) {
				sum += opinion.getInstructorMark();
				quantity++;
			}
		}
		return round(sum / quantity, 2);
		}
		else return 0;
	}
	
	private double round(double value, int placesAfterComma) {
	    if (placesAfterComma < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, placesAfterComma);
	    value = value * factor;
	    long roundedNumber = Math.round(value);
	    return (double) roundedNumber / factor;
	}
	
	

}
