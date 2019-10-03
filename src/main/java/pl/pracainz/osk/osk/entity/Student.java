package pl.pracainz.osk.osk.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_student")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column
	private String login;

	@Column
	private String password;
	
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Past
	private Date birthdate;

	@Column
	private String street;

	@Column
	private String buildingNumber;

	@Column
	private String apartmentNumber;

	@Column
	private String city;

	@Column
	private String postCode;

	@Column
	private String phoneNumber;

	
	@Column
	//@Email(message = "Niepoprawny email")
	private String email;

	@Column(name="PKK")
	@Nullable
	private String pkk;

	@Column
	private Integer deleted;
	
	
	@Column
	private int userId;

	@OneToMany(mappedBy = "id")
	List<InternalExam> exams;

	@OneToMany(mappedBy = "id")
	List<CarOpinion> carOpinions;

	@OneToMany(mappedBy = "id")
	List<InstructorOpinion> instructorOpinions;

	@OneToMany(mappedBy = "id")
	List<Driving> drivings;
	

	@OneToMany(targetEntity = Participant.class)
	private Set<Participant> participants = new HashSet<Participant>();
	
	@Transient
	private String username;
	
	public void addParticipant(Participant participant) {
		this.participants.add(participant);
	}
	
	
	public Student() {
	}

	public Student(String name, String surname, String login, Date birthdate, String street, String buildingNumber,
			String apartmentNumber, String city, String postcode, String phoneNumber, String email, String pkk,
			Integer deleted) {
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.street = street;
		this.login = login;
		this.buildingNumber = buildingNumber;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.postCode = postcode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.pkk = pkk;
		this.deleted = deleted;

	}

	public Student(Integer id, String name, String surname, String login, Date birthdate, String street,
			String buildingNumber, String apartmentNumber, String city, String postcode, String phoneNumber,
			String email, String pKK, Integer deleted) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.birthdate = birthdate;
		this.street = street;
		this.buildingNumber = buildingNumber;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.postCode = postcode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		pkk = pKK;
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postcode) {
		this.postCode = postcode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPkk() {
		return pkk;
	}

	public void setPkk(String pkk) {
		this.pkk = pkk;
	}

	public Integer getDeleted() {
		return deleted;

	}

	public List<InternalExam> getExams() {
		return exams;
	}

	public void setExams(List<InternalExam> exams) {
		this.exams = exams;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public List<CarOpinion> getCarOpinions() {
		return carOpinions;
	}

	public void setCarOpinions(List<CarOpinion> carOpinions) {
		this.carOpinions = carOpinions;
	}

	public List<InstructorOpinion> getInstructorOpinions() {
		return instructorOpinions;
	}

	public void setInstructorOpinions(List<InstructorOpinion> instructorOpinions) {
		this.instructorOpinions = instructorOpinions;
	}

	public List<Driving> getDrivings() {
		return drivings;
	}

	public void setDrivings(List<Driving> drivings) {
		this.drivings = drivings;
	}


	@OneToMany(mappedBy="primaryKey.student",cascade=CascadeType.ALL)
	public Set<Participant> getParticipants() {
		return participants;
	}


	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	

	
	
	
}
